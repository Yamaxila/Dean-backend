package by.vstu.dean.telegram;

import by.vstu.dean.telegram.commands.BaseCommand;
import by.vstu.dean.telegram.commands.LogCommand;
import by.vstu.dean.telegram.commands.RestartCommand;
import by.vstu.dean.telegram.commands.StatusCommand;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("CallToPrintStackTrace")
@Service
@Slf4j
@RestControllerAdvice
public class TelegramService implements LongPollingSingleThreadUpdateConsumer {

    @Getter
    private TelegramClient telegramClient;
    @Value("${dean.bot.token}")
    private String token;

    @Autowired
    private ConfigurableApplicationContext context;

    private static final List<BaseCommand> commands = new ArrayList<>();
    private Thread telegramThread;
    private TelegramBotsLongPollingApplication botsApplication;

    @PostConstruct
    public void init() {
        this.telegramThread = new Thread(() -> {
            try {
                this.botsApplication = new TelegramBotsLongPollingApplication();
                this.botsApplication.registerBot(this.token, this);
                this.telegramClient = new OkHttpTelegramClient(this.token);
                Thread.currentThread().join();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.telegramThread.start();
        TelegramService.commands.add(new StatusCommand(this.context));
        TelegramService.commands.add(new LogCommand());
        TelegramService.commands.add(new RestartCommand(this.context));

        Thread.setDefaultUncaughtExceptionHandler((t, ex) -> this.sendException(t, ex, null));
    }

    @Override
    public void consume(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            if (!update.getMessage().getText().startsWith("/"))
                return;

            String rawCommand = update.getMessage().getText().substring(1);
            String command = rawCommand.split(" ")[0].toLowerCase();
            String[] args = rawCommand.split(" ").length > 1 ? rawCommand.split(" ")[1].split(" ") : new String[]{};

            Optional<BaseCommand> oCommandObj = TelegramService.commands.stream().filter(p -> p.getName().equals(command)).findFirst();

            if (oCommandObj.isEmpty()) {
                SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(), "Command not found!");
                try {
                    this.telegramClient.execute(sendMessage);
                } catch (TelegramApiException e) {
                    log.error("Send message failed", e);
                }
                return;
            }

            try {
                Object o = oCommandObj.get().execute(update, args);

                if(o instanceof SendMessage message)
                    this.telegramClient.execute(message);
                else if (o instanceof SendDocument document)
                    this.telegramClient.execute(document);
            } catch (TelegramApiException e) {
                log.error("Send message failed", e);
            }
        }

    }

    @PreDestroy
    public void onClose() {
        if(this.telegramThread != null) {
            log.info("Stopping telegram client...");
            try {
                this.botsApplication.unregisterBot(this.token);
                this.botsApplication.stop();
                this.botsApplication.close();
            } catch (Exception e) {
                log.error("Can't stop telegram client!", e);
            }
            log.info("Telegram client stopped!");
        }
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(
            Exception ex, WebRequest req) throws Exception {

        log.error("Request: {} raised exception", req.getContextPath(), ex);

        String errorCode = UUID.randomUUID().toString().split("-")[0];
        log.error("ErrorCode: {}", errorCode);

        Map<String, String> args = new HashMap<>();

        if (req instanceof ServletWebRequest request) {
            args.put("URL", request.getRequest().getRequestURL().toString());
            args.put("Method", request.getRequest().getMethod());
            args.put("RemoteAddress", request.getRequest().getRemoteAddr());
            args.put("UserAgent", request.getRequest().getHeader("user-agent"));
        }

        args.put("ErrorCode", errorCode);
        args.put("Parameters", this.buildParameters(req.getParameterMap()));

        this.sendException(Thread.currentThread(), ex, args);

        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

        if (responseStatus == null)
            return new ResponseEntity<>(String.format("""
                    {
                    "error": "Internal Server Error",
                    "code": 500,
                    "description": "An unexpected error occurred. Contact with CIT-team for more information and say that errorCode.",
                    "errorCode": "%S"
                    }
                    """, errorCode), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(String.format(
                """
                        {
                        "error": "%s",
                        "code": %s,
                        "description": "An unexpected error occurred. Contact with CIT-team for more information and say that errorCode.",
                        "errorCode": "%S"
                        }
                        """
                , responseStatus.reason()
                , responseStatus.value().value()
                , errorCode)

                , responseStatus.value());
    }

    private String buildParameters(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();

        map.forEach((key, value) -> {
            sb.append(key).append(": ").append(Arrays.stream(value).map(String::valueOf).collect(Collectors.joining(", "))).append("\n");
        });

        return sb.toString().trim();
    }


    public void sendException(Thread t, Throwable ex, Map<String, String> args) {
        ex.printStackTrace();
        try {

            StringBuilder sb = new StringBuilder();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            String formattedDateTime = LocalDateTime.now().format(formatter);
            sb.append("EXCEPTION").append("\n");
            sb.append("```Time").append("\n").append(formattedDateTime).append("```").append(" ");

            if (args != null && !args.isEmpty()) {
                args.forEach((key, value) -> sb.append("```").append(key).append(" ").append(value).append("```").append("\n"));
            }

            sb.append("```Thread").append("\n").append(t.getName()).append("```").append(" ");
            sb.append("```message").append("\n").append(ex.getMessage()).append("```").append(" ");
            sb.append("Stacktrace").append("\n").append("```log");

            Arrays.stream(ex.getStackTrace()).forEach(elm -> sb.append(elm.toString()).append("\n"));

            sb.append("```");

            String text = sb.toString();
            boolean add = false;

            while (!text.isEmpty()) {
                String part = text.substring(0, Math.min(text.length(), 4092));

                if (!part.endsWith("```"))
                    part += "```";
                if (add)
                    part = "```log\n" + part;

                SendMessage message = SendMessage.builder().chatId(527440937L).parseMode(ParseMode.MARKDOWN).text(part).build();
                try {
                    this.telegramClient.execute(message);
                    add = true;
                } catch (TelegramApiException e) {
                    log.error("Send message failed", e);
                }
                text = text.substring(part.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
