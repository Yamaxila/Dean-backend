package by.vstu.dean.telegram.commands;


import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Getter
public abstract class BaseCommand {

    private final String name;
    private final String description;
    private final String[] arguments;

    public BaseCommand(String name, String description, List<String> arguments) {
        this.name = name;
        this.description = description;
        this.arguments = arguments.toArray(new String[0]);
    }

    public abstract Object execute(Update update, String... args);
}
