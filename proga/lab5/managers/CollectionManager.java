package managers;

import qwe.HumanBeing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.TreeSet;

public class CollectionManager {
    Comparator<HumanBeing> cmp = Comparator.comparing(obj -> (obj.getWeaponType().getProperties() + obj.getCoordinateX() - obj.getCoordinateY()));
    private final TreeSet<HumanBeing> collect = new TreeSet<>(cmp);
    private int freeId;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final DumpManager dumpManager;

    public CollectionManager(DumpManager dumpManager) {
        this.lastSaveTime = null;
        this.lastInitTime = null;
        this.dumpManager = dumpManager;
    }

    public TreeSet<HumanBeing> getCollection() {
        return collect;
    }

    public HumanBeing getMax(){
        return collect.last();
    }

    public int getSize(){
        return collect.size();
    }

    public int getFreeId(){
        if(collect.isEmpty())
            this.freeId = 1;
        else{
            this.freeId += 1;
        }
        return freeId;
    }

    public LocalDateTime getLastInitTime(){
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime(){
        return lastSaveTime;
    }

    public void add(HumanBeing person){
        collect.add(person);
    }

    public void clearCollection(){
        collect.clear();
    }

    public void removeByID(int id){
        collect.removeIf(person->person.getId() == id);
    }

    public void saveCollection() throws IOException {
        dumpManager.writeFile(collect);
        lastSaveTime = LocalDateTime.now();
    }

    public void loadCollection() throws FileNotFoundException {
        collect.clear();
        lastInitTime = LocalDateTime.now();
        dumpManager.readFile(collect);
        freeId = dumpManager.getMaxId();
    }

    @Override
    public String toString() {
        if(collect.isEmpty())
            return "Коллекция пуста\n";
        StringBuilder list = new StringBuilder();
        for(HumanBeing person : collect){
             list.append(person).append("\n");
        }
        return list.toString();
    }

    public HumanBeing ByID(int id) {
        for (HumanBeing humanBeing : collect){
            if(humanBeing.getId() == id){
                return humanBeing;
            }
        }
        return null;
    }
}
