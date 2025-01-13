package utility;

import exeptions.NotBooleanExeption;

public class Proverka {
    public Proverka() {
    }

    public static boolean proverkaBool(String str) throws NotBooleanExeption {
        if (str.matches("(?i)t(rue)?|y(es)?")) {
            return true;
        } else if (str.matches("(?i)f(alse)?|n(o)?")) {
            return false;
        } else {
            throw new NotBooleanExeption();
        }
    }
}
