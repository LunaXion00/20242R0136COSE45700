package State;

import Singleton.SelectionManagerSingleton;
import model.SelectionManager;
import model.ShapeModel;
import Object.ShapeObject;
import Object.LineObject;


import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectionTool implements Tool{
    private SelectionToolState currentState;
    private final SelectionManager selectionManager;

    public SelectionTool() {
        this.selectionManager = SelectionManagerSingleton.getInstance();
        this.currentState = new IdleState(this);
    }

    public void setState(SelectionToolState newState) {
        this.currentState = newState;
    }

    @Override
    public void HandleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {
        currentState.handleMousePress(e, model, startPoint);
    }

    @Override
    public void HandleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
        currentState.handleMouseDrag(e, model, startPoint);
        model.notifyObservers();
    }

    @Override
    public void HandleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        currentState.handleMouseRelease(e, model, startPoint);
        model.notifyObservers();  // 최종 업데이트
    }

    @Override
    public void onDeactivate(Component component) {
        currentState = null;
        selectionManager.clearManager();
    }

    @Override
    public void setCurrentColor(Color color) {

    }

    public Rectangle getSelectionRect() {
        if (currentState instanceof SelectionBoxState) return ((SelectionBoxState) currentState).getSelectionBox();
        return null;
    }
    public SelectionToolState getState(){return currentState;}
}
