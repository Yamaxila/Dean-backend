package by.vstu.dean.telegram.commands;

import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.util.ArrayList;

public class StatusCommand extends BaseCommand {

    private final ConfigurableApplicationContext context;

    public StatusCommand(ConfigurableApplicationContext context) {
        super("status", "Показывает статус деканата", new ArrayList<>());
        this.context = context;
    }

    @Override
    public SendMessage execute(Update update, String... args) {
        SendMessage message = new SendMessage(update.getMessage().getChatId().toString(), "");
        StringBuilder sb = new StringBuilder();

        Runtime rt = Runtime.getRuntime();

        sb.append("Dean-Backend status:").append("\n");

        sb.append("OS:").append(System.getProperty("os.name"))
                .append(" ").append(System.getProperty("os.version"))
                .append(" ").append(System.getProperty("os.arch")).append("\n");
        sb.append("Java version: ").append(System.getProperty("java.version")).append("\n");
        sb.append("Java vendor: ").append(System.getProperty("java.vendor")).append("\n");
        sb.append("Java home: ").append(System.getProperty("java.home")).append("\n");
        sb.append("Memory: ")
                .append((rt.totalMemory()-rt.freeMemory())/1024L/1024L)
                .append("/").append(rt.totalMemory()/1024L/1024L).append("MiB.").append("\n");
        sb.append("Beans count: ").append(this.context.getBeanDefinitionNames().length).append("\n");
        sb.append("Working directory: ").append(new File(".").getAbsolutePath()).append("\n");

        message.setText(sb.toString());

        return message;
    }
}
