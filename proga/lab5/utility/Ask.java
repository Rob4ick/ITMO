package utility;

import exeptions.CoordinatesExeption;
import exeptions.NotBooleanExeption;
import qwe.*;

public class Ask {
    public static HumanBeing askHumanBeing(Console console, int id) {
        String name1;
        do {
            console.printMessag("Введите имя name: ");
            name1 = console.readln().trim();
        } while (name1.isEmpty());

        Coordinates coordinates1 = askCoordinates(console);

        boolean realHero1;
        while(true){
            console.printMessag("Является ли героем: ");
            var line = console.readln().trim();
            if(!line.isEmpty()) {
                try {
                    realHero1 = Proverka.proverkaBool(line);
                    break;
                } catch (NotBooleanExeption e) {
                    console.printError("Некорректный ввод!!!");
                }
            }
        }

        boolean hasToothpick1;
        while(true){
            console.printMessag("Есть ли зубочистка: ");
            var line = console.readln().trim();
            if(!line.isEmpty()){
                try {
                    hasToothpick1 = Proverka.proverkaBool(line);
                    break;
                }
                catch(NotBooleanExeption e){
                    console.printError("Некорректный ввод!!!");
                }
            }
        }

        Integer impactSpeed1;
        while(true){
            console.printMessag("Введите скорость удара: ");
            var line = console.readln().trim();
            if(!line.isEmpty()){
                try{impactSpeed1 = Integer.parseInt(line);break;}
                catch (NumberFormatException e){console.printError("Некорректный ввод!!! Скорость должна быть числом");}
            }
            else{
                impactSpeed1 = null;
                break;
            }
        }

        WeaponType weaponType1 = askWeaponType(console);

        Mood mood1 = askMood(console);

        Car car1 = askCar(console);

        return new HumanBeing(id, name1, coordinates1, realHero1, hasToothpick1, impactSpeed1, weaponType1, mood1, car1);
    }

    public static Coordinates askCoordinates(Console console) {
        Coordinates coordinates;
        while (true) {
            console.printMessag("Введите координаты (в формате X::Y): ");
            var line = console.readln().trim();
            if (!line.isEmpty()) {
                try {
                    coordinates = new Coordinates(line);
                    break;
                } catch (CoordinatesExeption e) {
                    console.printError("Координата Y должна быть не больше 943!!!");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    console.printError("Некорректный ввод!!!");
                }
            }
        }
        return coordinates;
    }

    public static WeaponType askWeaponType(Console console){
        WeaponType a;
        while(true){
            console.printMessag("Введите WeaponType(" + WeaponType.names() + "): ");
            var line = console.readln().trim();
            if(!line.isEmpty()) {
                try {
                    a = WeaponType.valueOf(line.toUpperCase());
                    break;
                } catch (IllegalArgumentException | NullPointerException e) {
                    console.printError("Некорректный ввод!!!");
                }
            }
        }
        return a;
    }

    public static Mood askMood(Console console){
        Mood a;
        while(true){
            console.printMessag("Введите MOOD(" + Mood.names() + "): ");
            var line = console.readln().trim();
            if(!line.isEmpty()){
                try{
                    a = Mood.valueOf(line.toUpperCase());
                    break;
                }
                catch (IllegalArgumentException | NullPointerException e){
                    console.printError("Некорректный ввод!!!");
                }
            }
            else {
                a = null;
                break;
            }
        }
        return a;
    }

/*    public static String askFilePath(Console console){
        String env;
        String filePath;
        while(true){
            console.print("Введите имя переменной окружения: ");
            env = console.readln().trim();
            if(!env.isEmpty()){
                filePath = System.getenv(env);
                if(filePath != null)
                    break;
                else
                    console.printError("Переменной не существует!!!");
            }
        }
        return filePath;
    }
 */
    public static Car askCar(Console console){
        Boolean a;
        while(true){
            console.printMessag("Крутая ли машина: ");
            var line = console.readln().trim();
            if(!line.isEmpty()){
                try {
                    a = Proverka.proverkaBool(line);
                    break;
                }
                catch(NotBooleanExeption e){
                    console.printError("Некорректный ввод!!!");
                }
            }
        }
        return new Car(a);
    }
}