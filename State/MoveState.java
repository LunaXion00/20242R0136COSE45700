package State;

import model.ShapeModel;
import Object.ShapeObject;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState implements SelectionToolState{
    private SelectionTool tool;
    private ShapeObject activeshape;
    private Point startPoint;

    public MoveState(SelectionTool tool, ShapeObject activeshape, Point startPoint){
        this.tool = tool;
        this.activeshape = activeshape;
        this.startPoint = startPoint;
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
