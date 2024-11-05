package Controller;

import State.SelectionTool;
import State.Tool;
import model.ShapeModel;
import Object.ShapeObject;

import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VectorGraphicController extends MouseAdapter{
    private ShapeModel model;
    private Tool currentTool;
    private Point startPoint;
    private Point currentPoint;
    private Rectangle selectionRect;
    private Component currentComponent;

    public VectorGraphicController(ShapeModel model) {
       this.model = model;
       addKeyListener();
    }

    public void setCurrentTool(Tool tool) {
        if(currentTool != null & currentComponent != null) {
            currentTool.onDeactivate(currentComponent);
        }
        currentTool = tool;
    }

    public void setCurrentColor(Color color) {
        if (currentTool != null) {
            currentTool.setCurrentColor(color);
        }
        if (model.getSelectedShapes() != null) {
            for (ShapeObject shape: model.getSelectedShapes()) {
                shape.setFillColor(color);
                model.notifyObservers();
            }
        }
    }

    public void setSelectionRect(Rectangle rect) {
        this.selectionRect = rect;
    }

    public void mousePressed(MouseEvent e) {
        currentComponent = e.getComponent();
        if (currentTool != null) {
            startPoint = e.getPoint();
            currentPoint = startPoint;
            currentTool.HandleMousePress(e, model, startPoint);
            model.notifyObservers();
        }
    }

    public void mouseDragged(MouseEvent e) {
        currentComponent = e.getComponent();
        if (currentTool != null) {
            currentPoint = e.getPoint();
            currentTool.HandleMouseDrag(e, model, startPoint);
            if (currentTool instanceof SelectionTool) {
                SelectionTool selectionTool = (SelectionTool) currentTool;
                setSelectionRect(selectionTool.getSelectionRect());
            }
            model.notifyObservers();
        }
    }

    public void mouseReleased(MouseEvent e) {
        currentComponent = e.getComponent();
        if (currentTool != null) {
            currentTool.HandleMouseRelease(e, model, startPoint);
            model.notifyObservers();
        }
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }
    public Tool getCurrentTool() {
        return currentTool;
    }
    public Point getStartPoint() {
        return startPoint;
    }

    public ShapeObject getSelectedShape() {
        return model.getSelectedShape();
    }

    private void addKeyListener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_DELETE) {
                    deleteSelectedShape();
                }
                return false;
            }
        });
    }

    private void deleteSelectedShape() {
        List<ShapeObject> selectedShapes = model.getSelectedShapes();
        if (selectedShapes != null) {
            for(ShapeObject selectedShape: selectedShapes) {
                model.deleteShape(selectedShape);
                model.notifyObservers();
            }
        }
    }
    public void bringToFront() {
        ShapeObject selectedShape = getSelectedShape();
        if (selectedShape != null) {
            model.moveToTop(selectedShape);
        }
    }

    public void bringForward() {
        ShapeObject selectedShape = getSelectedShape();
        if (selectedShape != null) {
            model.moveUp(selectedShape);
        }
    }

    public void sendBackward() {
        ShapeObject selectedShape = getSelectedShape();
        if (selectedShape != null) {
            model.moveDown(selectedShape);
        }
    }

    public void sendToBack() {
        ShapeObject selectedShape = getSelectedShape();
        if (selectedShape != null) {
            model.moveToBottom(selectedShape);
        }
    }
}