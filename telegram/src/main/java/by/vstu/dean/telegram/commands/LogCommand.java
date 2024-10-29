package by.vstu.dean.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.util.ArrayList;

public class LogCommand extends BaseCommand {

    public LogCommand() {
        super("log", "Присылает текущий .log-файл.", new ArrayList<>());
    }

    @Override
    public SendDocument execute(Update update, String... args) {
        return SendDocument.builder()
                .chatId(update.getMessage().getChatId())
                .document(new InputFile(new File("./logs/.dean.log"), "dean.log"))
                .build();
    }
}
