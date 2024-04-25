package commands;

import managers.CollectionManager;
import qwe.HumanBeing;
import utility.Ask;
import utility.Console;

import java.util.NoSuchElementException;

public class Add extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public Add(Console console, CollectionManager collectionManager) {
        super("add", "добавляет новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args) {
        if (args.length != 1) {
            console.printError("Неправильное количество аргументов");
            return false;
        }
        console.printMessage("Создание нового экземпляра");
        HumanBeing person = Ask.askHumanBeing(console, collectionManager.getFreeId());
        collectionManager.add(person);
        console.printMessage("Экземпляр успешно добавлен");
        return true;
    }
}
