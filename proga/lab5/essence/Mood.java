package qwe;

public enum Mood {
    SADNESS,
    GLOOM,
    FRENZY;

    public static String names(){
        StringBuilder moods = new StringBuilder();
        for(var obj : values()){
            moods.append(obj.name()).append(", ");
        }
        return moods.substring(0, moods.length() - 2);
    }
    public static Mood fromString(String s){
        if(!(s.trim()).equals("null"))
            return Mood.valueOf(s.trim().toUpperCase());
        return null;
    }
}