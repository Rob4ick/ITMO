package commands;

import managers.CollectionManager;
import utility.Console;

import java.time.LocalDateTime;

public class Info extends Command {
    private CollectionManager collectionManager;
    private Console console;
    public Info(Console console, CollectionManager collectionManager){
        super("info", "выводит информацию о коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    public boolean execution(String[] args){
        if (args.length != 1){
            console.printError("Неправильное количество аргументов");
            return false;
        }
        String type = collectionManager.getCollection().getClass().toString();
        String size = collectionManager.getSize() + "";
        LocalDateTime lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString;
        String lastSaveTimeString;
        if (lastInitTime == null)
            lastInitTimeString = "Коллекцию ещё не инициализировали";
        else
            lastInitTimeString = "Последнее время инициализации: " + lastInitTime;
        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        if (lastSaveTime == null){
            lastSaveTimeString = "Коллекцию ещё не сохраняли";
        }
        else{
            lastSaveTimeString = "Последнее время сохранения: " + lastSaveTime;
        }
        console.println("Информация о коллекции:");
        console.println("Тип: " + type);
        console.println("Размер коллекции: " + size);
        console.println(lastInitTimeString);
        console.println(lastSaveTimeString);
        return true;
    }
}
