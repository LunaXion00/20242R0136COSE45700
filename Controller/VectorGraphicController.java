package Controller;

import Command.DeleteCommand;
import Singleton.CommandManagerSingleton;
import Singleton.SelectionManagerSingleton;
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
        if (SelectionManagerSingleton.getInstance().getSelectedObject() != null) {
            SelectionManagerSingleton.getInstance().getSelectedObject().setFillColor(color);
            model.notifyObservers();
        }
    }
    public void mousePressed(MouseEvent e) {
        currentComponent = e.getComponent();
        if (currentTool != null) {
            startPoint = e.getPoint();
            currentTool.HandleMousePress(e, model, startPoint);
            model.notifyObservers();
        }
    }

    public void mouseDragged(MouseEvent e) {
        currentComponent = e.getComponent();
        if (currentTool != null) {
            currentTool.HandleMouseDrag(e, model, startPoint);
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

    public Tool getCurrentTool() {
        return currentTool;
    }
    public Point getStartPoint() {
        return startPoint;
    }

    private void addKeyListener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_DELETE) {
                    DeleteCommand deleteCommand = new DeleteCommand();
                    CommandManagerSingleton.getInstance().executeCommand(deleteCommand);
                }
                return false;
            }
        });
    }
}