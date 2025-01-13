package managers;

import commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private final Map<String, Command> commands = new HashMap<>();
    private final ArrayList<String> history = new ArrayList<>();

    public void addCommand(String name, Command command){
        commands.put(name, command);
    }

    public Map<String, Command> getCommands(){
        return commands;
    }

    public void addHistory(String command){
        history.add(command);
    }

    public ArrayList<String> getHistory() {
        return history;
    }
}
