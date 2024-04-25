package utility;

import managers.CommandProcessor;
import qwe.HumanBeing;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.AccessDeniedException;
import java.util.*;

import static java.lang.Math.max;

public class commandHandler {
    public static class ScriptException extends Exception {}
    private final Console console;
    private final CommandProcessor commandProcessor;
    private final Stack<Scanner> scannerStack = new Stack<>();

    private final TreeSet<String> fileNames = new TreeSet<>();

    public commandHandler(Console console, CommandProcessor commandProcessor) {
        this.console = console;
        this.commandProcessor = commandProcessor;
    }

    public void manualMode(){
        String[] line = {"", ""};
        do{
            console.print("Введите команду: ");
            line = console.readln().trim().split(" ");
            executionCommand(line);
            if(line[0].equals("execute_script"))
                try {
                    script(line[1]);
                }catch (NoSuchElementException e){
                    console.printError("Скрипт содержит некорректные данные!!!!");
                }catch (ScriptException e){
                    console.printError("Скрипт зацикливается!!!");
                }catch (FileNotFoundException e){
                    console.printError("Файл не найден!!!");
                }
        }while(!line[0].equals("exit"));
    }

    private void script(String fileName) throws FileNotFoundException, ScriptException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        scannerStack.add(scanner);
        if (fileNames.contains(fileName)) {
            scannerStack.pop();
            if (scannerStack.isEmpty())
                console.setFileScanner(null);
            else
                console.setFileScanner(scannerStack.peek());
            throw new ScriptException();
        }
        console.setFileScanner(scannerStack.peek());
        fileNames.add(fileName);
        try {
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().trim().split(" ");
                executionCommand(line);
                if (line[0].equals("execute_script"))
                    script(line[1]);
            }
        } catch (NoSuchElementException e) {
            scannerStack.pop();
            if (scannerStack.isEmpty())
                console.setFileScanner(null);
            else
                console.setFileScanner(scannerStack.peek());
            throw new NoSuchElementException();
        }catch (ScriptException e){
            scannerStack.pop();
            if (scannerStack.isEmpty())
                console.setFileScanner(null);
            else
                console.setFileScanner(scannerStack.peek());
            throw new ScriptException();
        }
    }

    private void executionCommand(String[] c) {
        if (c[0].isEmpty())
            return;
        var command = commandProcessor.getCommands().get(c[0]);
        if (command == null) {
            console.printError("Команда " + c[0] + " не найдена, введите help, чтобы узнать доступные команды");
            return;
        }
        if (command.execution(c)) {
            commandProcessor.addHistory(c[0]);
        }
    }
}
