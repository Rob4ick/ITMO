package commands;

import managers.CollectionManager;
import qwe.HumanBeing;
import utility.Ask;
import utility.Console;

public class RemoveGreater extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public RemoveGreater(Console console, CollectionManager collectionManager){
        super("remove_greater", "удаляет из коллекции все элементы, превышающий заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args){
        if (args.length != 1){
            console.printError("Неправильное количество аргументов");
            return false;
        }
        HumanBeing hb = Ask.askHumanBeing(console, collectionManager.getFreeId());
        collectionManager.getCollection().removeIf(x -> x.getWeaponType().isLessTo(hb.getWeaponType()));
        return true;
    }
}