package commands;

import managers.CollectionManager;
import utility.Ask;
import utility.Console;

public class UpdateID extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public UpdateID(Console console, CollectionManager collectionManager){
        super("Update id {element}", "обновляет значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args){
        if (args.length != 2){
            console.printError("Неправильное количество аргументов");
            return true;
        }
        try{
            int id = Integer.parseInt(args[1].trim());
            if(collectionManager.ByID(id) == null){
                console.printError("Несуществующий ID");
                return true;
            }
            collectionManager.removeByID(id);
            collectionManager.add(Ask.askHumanBeing(console, id));
        }catch(NumberFormatException e){
            console.printError("ID не распознан");
        }

        return true;
    }
}
