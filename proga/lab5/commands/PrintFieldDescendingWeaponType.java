package commands;

import managers.CollectionManager;
import qwe.HumanBeing;
import utility.Console;

public class PrintFieldDescendingWeaponType extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public PrintFieldDescendingWeaponType(Console console, CollectionManager collectionManager) {
        super("print_field_descending_weapon_type", "выводит значение поля weaponType всех элементов в порядке убывания id");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args){
        if (args.length != 1){
            console.printError("Неправильное количество аргументов");
            return false;
        }
        for(HumanBeing hb : collectionManager.getCollection())
            console.print(hb.getWeaponType() + " ");
        console.println("");
        return true;
    }
}
