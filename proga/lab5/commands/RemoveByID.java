package commands;

import managers.CollectionManager;
import utility.Console;

public class RemoveByID extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveByID(Console console, CollectionManager collectionManager) {
        super("remove_by_ID", "удалить элемент из коллекции по его ID");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    public boolean execution(String[] args){
        if (args.length != 2){
            console.printError("Неправильное количество аргументов");
            return true;
        }
        int id = -1;
        try{
            id = Integer.parseInt(args[1].trim());
        }catch(NumberFormatException e){
            console.printError("ID не распознан");
        }
        if(collectionManager.ByID(id) == null){
            console.printError("Несуществующий ID");
            return true;
        }
        collectionManager.removeByID(id);
        console.println("Экземпляр удалён");
        return true;
    }
}
