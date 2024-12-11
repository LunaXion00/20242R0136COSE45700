package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Observer.Observer;
import Object.ShapeObject;
import Object.CompositeObject;

public class ShapeModel {
    private List<ShapeObject> shapes = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();  // Observer 패턴


    public void addShape(ShapeObject shape) {
        shapes.add(shape);
        notifyObservers();
        System.out.println("Shape added: " + shape.getClass().getSimpleName() + " | Total shapes: " + shapes.size());
    }

    public void deleteShape(ShapeObject shape) {
        shapes.remove(shape);
        notifyObservers();
    }

    public List<ShapeObject> getShapes() {
        return shapes;
    }

            // Observer 패턴: 변경 알림
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}