package commands;

import managers.CollectionManager;
import qwe.HumanBeing;
import qwe.Mood;
import utility.Ask;
import utility.Console;

public class CountByMood extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public CountByMood(Console console, CollectionManager collectionManager){
        super("count_by_mood", "выводит количество элементов, значение поля mood которых равно заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    public boolean execution(String[] args){
        try {
            if (args.length != 2){
                console.printError("Неправильное количество аргументов");
                throw new IllegalArgumentException();
            }
            Mood mood = Mood.valueOf(args[1].toUpperCase());
            int counter = 0;
            for (HumanBeing hb : collectionManager.getCollection()) {
                if (hb.getMood().equals(mood))
                    counter++;
            }
            console.println(counter);
        }catch (IllegalArgumentException e){
            console.printError("Некорректный ввод!!!");
            return false;
        }
        return true;
    }
}
