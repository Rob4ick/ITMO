package commands;

import managers.CollectionManager;
import utility.Console;

public class ExecuteScript extends Command {
    private final Console console;
    private final CollectionManager collectionManager   ;
    public ExecuteScript(Console console, CollectionManager collectionManager) {
        super("execute_script", "исполняет скрипт из указанного файла");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args){
        if (args.length != 2){
            console.printError("Неправильное количество аргументов");
            return false;

        }


        return true;
    }
}
