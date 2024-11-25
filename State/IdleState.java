package State;

import model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class IdleState implements SelectionToolState {
    private SelectionTool tool;

    public IdleState(SelectionTool tool){
        this.tool = tool;
    }

    @Override
    public void handleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {

    }

    @Override
    public void handleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {

    }

    @Override
    public void handleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {

    }
}
