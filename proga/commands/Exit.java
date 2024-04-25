package commands;

import managers.CollectionManager;
import utility.Console;
import utility.StandartConsole;

public class Exit extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public Exit(Console console, CollectionManager collectionManager) {
        super("exit", "завершает программу");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args){
        if (args.length != 1){
            console.printError("Неправильное количество аргументов");
            return false;
        }
        console.printMessage("Завершение программы...");
        return true;
    }
}
