package commands;

import managers.CollectionManager;
import qwe.HumanBeing;
import qwe.WeaponType;
import utility.Ask;
import utility.Console;

public class FilterLessThanWeaponType extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public FilterLessThanWeaponType(Console console, CollectionManager collectionManager) {
        super("filter_less_than_weapon_type", "выводит элементы, значение поля weaponType которых меньше заданного");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execution(String[] args){
        if (args.length != 2){
            console.printError("Неправильное количество аргументов");
            return false;
        }
        try {
            WeaponType weaponType = WeaponType.valueOf(args[1].toUpperCase());
            for (HumanBeing hb : collectionManager.getCollection()) {
                if (hb.getWeaponType().isLessTo(weaponType))
                    console.println(hb.toString());
            }
        }catch (IllegalArgumentException e){
            console.printError("Некорректный тип оружия!!!!");
        }
        return true;
    }
}
