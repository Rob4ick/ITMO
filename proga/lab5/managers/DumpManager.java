package managers;

import qwe.HumanBeing;

import utility.Console;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

import static java.lang.Math.max;

public class DumpManager {
    private final String fileName;
    private final Console console;
    private int maxId = 0;

    private LocalDate dataInit;
    public DumpManager(String fileName, Console console){
        this.fileName = fileName;
        this.console = console;
    }

    public LocalDate getDataInit() {
        return dataInit;
    }

    public void updateDateInit(){
        this.dataInit = LocalDate.now();
    }

    public void readFile(Collection<HumanBeing> humanBeings) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        scanner.useDelimiter(System.lineSeparator());
        while (scanner.hasNext()) {
            try {
                String[] line = scanner.nextLine().split(";");
                for (int i = 0; i < line.length; ++i) {
                    line[i] = line[i].trim();
                }
                HumanBeing humanBeing = HumanBeing.fromArray(line);
                if(humanBeing.validate()){
                   humanBeings.add(humanBeing);
                   maxId = max(humanBeing.getId(), maxId);
                }
            } catch (Exception ignore) {
            }
        }
        scanner.close();
        updateDateInit();
    }

    public int getMaxId(){
        return maxId;
    }

    public void writeFile(Collection<HumanBeing> humanBeings) throws IOException {
        File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(toCSV(humanBeings));
            writer.flush();
            writer.close();
    }

    public String toCSV(Collection<HumanBeing> humanBeings){
        StringBuilder s = new StringBuilder();
        for(HumanBeing humanBeing : humanBeings){
            s.append(humanBeing.toCSV()).append("\n");
        }
        return s.toString();
    }
}