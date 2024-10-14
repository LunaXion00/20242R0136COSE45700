package test.src.controller;

import test.src.state.ToolState;
import test.src.model.ShapeModel;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VectorGraphicsController extends MouseAdapter {
    private ShapeModel model;
    private ToolState currentState;
    private Point startPoint;
    private Point currentPoint;

    public VectorGraphicsController(ShapeModel model) {
        this.model = model;
    }

    public void setToolState(ToolState state) {
        this.currentState = state;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (currentState == null) {
            System.out.println("ToolState is not set. Please select a tool.");
            return; // 현재 도구 상태가 설정되지 않은 경우 이벤트를 무시
        }
        startPoint = e.getPoint();
        currentPoint = startPoint;
        currentState.mousePressed(e, model, startPoint);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentState == null) {
            System.out.println("ToolState is not set. Please select a tool.");
            return; // 현재 도구 상태가 설정되지 않은 경우 이벤트를 무시
        }
        currentPoint = e.getPoint();
        currentState.mouseDragged(e, model, startPoint);
        model.notifyObservers();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentState == null) {
            System.out.println("ToolState is not set. Please select a tool.");
            return; // 현재 도구 상태가 설정되지 않은 경우 이벤트를 무시
        }
        currentState.mouseReleased(e, model, startPoint);
        currentPoint = null;
    }

    public Point getCurrentPoint(){
        return currentPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }
}