package commands;

import managers.CollectionManager;
import utility.Console;

import java.io.IOException;

public class Save extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public Save(Console console, CollectionManager collectionManager) {
        super("Save", "сохраняет коллекцию в файл");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    public boolean execution(String[] args){
        if (args.length != 1){
            console.printError("Неправильное количество аргументов");
            return false;
        }
        try {
            collectionManager.saveCollection();
            console.printMessage("Коллекция сохранена в файл");
            return true;
        }catch (IOException e){
            console.printError("Не удалось записать в файл!!!");
            return false;
        }
    }
}
