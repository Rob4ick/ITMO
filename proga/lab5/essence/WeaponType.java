package qwe;



public enum WeaponType {
    SHOTGUN (70, 25, 30),
    RIFLE (60, 85, 15),
    KNIFE (100, 100, 20),
    MACHINE_GUN (50, 10, 100);
    private final int damage;
    private final int accuracy;
    private final int rate_of_fire;
    WeaponType(int damage, int accuracy, int rate_of_fire) {
        this.damage = damage;
        this.accuracy = accuracy;
        this.rate_of_fire = rate_of_fire;
    }
    public static String names(){
        StringBuilder weapons = new StringBuilder();
        for(var obj : WeaponType.values()){
            weapons.append(obj).append(", ");
        }
        return weapons.substring(0, weapons.length() - 2);
    }

    public int getProperties() {
        return this.accuracy + this.damage + this.rate_of_fire;
    }

    public boolean isLessTo(WeaponType other){
        return (this.accuracy + this.damage + this.rate_of_fire) < (other.accuracy + other.damage + other.rate_of_fire);
    }
};