package Singleton;

import model.CommandManager;

public class CommandManagerSingleton {
    private static CommandManager instance;

    private CommandManagerSingleton() {}

    public static CommandManager getInstance() {
        if (instance == null){
            instance = new CommandManager();
        }
        return instance;
    }
}
