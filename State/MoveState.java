package State;

import Command.Command;
import Singleton.CommandManagerSingleton;
import model.CommandManager;
import model.ShapeModel;
import Object.ShapeObject;
import Command.MoveCommand;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState implements SelectionToolState{
    private SelectionTool tool;
    private final ShapeObject activeshape;
    private final Point initialPoint;
    private Point dragPoint;
    private CommandManager manager = CommandManagerSingleton.getInstance();

    public MoveState(SelectionTool tool, ShapeObject activeshape, Point startPoint){
        this.tool = tool;
        this.activeshape = activeshape;
        this.initialPoint = activeshape.getPosition();
        this.dragPoint = startPoint;
    }
    @Override
    public void handleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {

    }

    @Override
    public void handleMouseDrag(MouseEvent e, ShapeModel model, Point movePoint) {
        Point currentPoint = e.getPoint();
        int dx = currentPoint.x - dragPoint.x;
        int dy = currentPoint.y - dragPoint.y;
        activeshape.move(dx,dy);
        dragPoint = currentPoint;
    }

    @Override
    public void handleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        Point finalPosition = activeshape.getPosition();
        if(!initialPoint.equals(finalPosition)) {
            MoveCommand moveCommand = new MoveCommand(activeshape, initialPoint, finalPosition);
            manager.executeCommand(moveCommand);
        }
        tool.setState(new IdleState(tool));
    }
}
