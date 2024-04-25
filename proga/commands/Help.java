package commands;

import managers.CommandProcessor;
import utility.Console;

public class Help extends Command{
    private final Console console;
    private final CommandProcessor commandProcessor;
    public Help(Console console, CommandProcessor commandProcessor) {
        super("help", "выводит справку по доступным командам ");
        this.console = console;
        this.commandProcessor = commandProcessor;
    }

    public boolean execution(String[] args){
        if (args.length != 1){
            console.printError("Неправильное количество аргументов");
            return true;
        }
        commandProcessor.getCommands().values().forEach(command -> {console.println(command.getName() + " " + command.getDescription());});
        return true;
    }
}
