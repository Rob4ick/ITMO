package commands;

import managers.CollectionManager;
import utility.Console;

public class Clear extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public Clear(Console console, CollectionManager collectionManager) {
        super("name", "description");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args) {
        if (args.length != 1){
            console.printError("Неправильное количество аргументов");
            return false;
        }

        collectionManager.clearCollection();
        console.println("Коллекция очищена");
        return true;
    }
}
