package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Observer.Observer;
import Object.ShapeObject;

public class ShapeModel {
    private List<ShapeObject> shapes = new ArrayList<>();
    private List<ShapeObject> selectedShapes = new ArrayList<>();
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

    public void selectShape(ShapeObject shape) {
        selectedShapes.add(shape);
        notifyObservers();
    }

    public List<ShapeObject> getSelectedShapes() {
        return selectedShapes;
    }
    public ShapeObject getSelectedShape() {
        if (selectedShapes.size() == 1) {
            return selectedShapes.getFirst();
        }
        return null;
    }
    public void moveToBottom(ShapeObject shape) {
        shapes.remove(shape);
        shapes.add(0, shape);
        notifyObservers();
    }
    public void moveToTop(ShapeObject shape) {
        shapes.remove(shape);
        shapes.add(shape); // 리스트의 마지막으로 이동
        notifyObservers();
    }

    // 선택된 도형을 한 칸 위로 올리기
    public void moveUp(ShapeObject shape) {
        int index = shapes.indexOf(shape);
        if (index < shapes.size() - 1) { // 마지막이 아닌 경우
            shapes.remove(shape);
            shapes.add(index + 1, shape);
            notifyObservers();
        }
    }

    // 선택된 도형을 한 칸 아래로 내리기
    public void moveDown(ShapeObject shape) {
        int index = shapes.indexOf(shape);
        if (index > 0) { // 첫 번째가 아닌 경우
            shapes.remove(shape);
            shapes.add(index - 1, shape);
            notifyObservers();
        }
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