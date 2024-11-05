package Singleton;

import model.ShapeModel;

public class ShapeModelSingleton {
    private static ShapeModel instance;

    private ShapeModelSingleton() {}

    public static ShapeModel getInstance() {
        if (instance == null) {
            instance = new ShapeModel();
        }
        return instance;
    }
}