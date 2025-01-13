package qwe;

import utility.Validable;

public class Car implements Validable{
    private Boolean cool; //Поле не может быть null

    public Car(Boolean cool){
        this.cool = cool;
    }
    public Car(String s){
        this.cool = Boolean.parseBoolean(s);
    }

    @Override
    public String toString(){
        return String.valueOf(cool);
    }

    @Override
    public boolean validate() {
        return cool != null;
    }
}
