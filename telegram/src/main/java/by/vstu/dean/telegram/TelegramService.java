package by.vstu.dean.telegram;

import by.vstu.dean.telegram.commands.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CallToPrintStackTrace")
@Service
@Slf4j
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
        TelegramService.commands.add(new ExceptionMessage());
        TelegramService.commands.add(new RestartCommand(this.context));

        Thread.setDefaultUncaughtExceptionHandler(TelegramService.this::sendException);
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

    public void sendException(Thread t, Throwable ex) {
        ex.printStackTrace();
        try {

            StringBuilder sb = new StringBuilder();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            String formattedDateTime = LocalDateTime.now().format(formatter);
            sb.append("EXCEPTION").append("\n");
            sb.append("```Time").append(formattedDateTime).append("```").append("\n");
            sb.append("```Thread").append(t.getName()).append("```").append("\n");
            sb.append("```message").append(ex.getMessage()).append("```").append("\n");
            sb.append("Stacktrace").append("\n").append("```log").append("\n");

            Arrays.stream(ex.getStackTrace()).forEach(elm -> sb.append(elm.toString()).append("\n"));

            sb.append("```");

            SendMessage message = SendMessage.builder().chatId(527440937L).parseMode(ParseMode.MARKDOWN).text(sb.toString()).build();
            this.telegramClient.execute(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
