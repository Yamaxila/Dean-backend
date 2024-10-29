package by.vstu.dean.telegram.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

@Slf4j
public class RestartCommand extends BaseCommand {

    private ConfigurableApplicationContext context;

    public RestartCommand(ConfigurableApplicationContext context) {
        super("restart", "Перезапускает сервис деканата", new ArrayList<>());
        this.context = context;
    }

    @Override
    public SendMessage execute(Update update, String... eArgs) {
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), "Restarting...");
        ApplicationArguments args = this.context.getBean(ApplicationArguments.class);
        Thread thread = new Thread(() -> {
            this.context.close();
            System.gc();
            try {
                this.context = SpringApplication.run(Class.forName("by.vstu.dean.DeanBackendApplication"), args.getSourceArgs());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setDaemon(false);
        thread.start();
        return message;
    }

}
