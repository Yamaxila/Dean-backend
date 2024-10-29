package by.vstu.dean.telegram.commands;

import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

public class StopCommand extends BaseCommand {
    private ConfigurableApplicationContext context;

    public StopCommand(ConfigurableApplicationContext context) {
        super("stop", "Останавливает сервис", new ArrayList<>());
        this.context = context;
    }

    @Override
    public Object execute(Update update, String... args) {

        this.context.close();
        System.exit(0);

        return new SendMessage(update.getMessage().getChatId().toString(), "Stopping...");
    }
}
