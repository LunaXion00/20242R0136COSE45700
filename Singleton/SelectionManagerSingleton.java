package Singleton;

import model.SelectionManager;

public class SelectionManagerSingleton {
    private static SelectionManager instance;

    private SelectionManagerSingleton() {}

    public static SelectionManager getInstance() {
        if (instance == null){
            instance = new SelectionManager();
        }
        return instance;
    }
}
