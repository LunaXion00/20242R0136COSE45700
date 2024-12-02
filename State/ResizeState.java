package State;

import Command.MoveCommand;
import Command.ResizeCommand;
import Handle.ResizeHandle;
import Singleton.CommandManagerSingleton;
import Singleton.SelectionManagerSingleton;
import model.SelectionManager;
import model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeState implements SelectionToolState{
    private final SelectionTool tool;
    private final ResizeHandle handle;
    private Point startPoint;
    private final Point initialPoint;
    public ResizeState(SelectionTool tool, ResizeHandle handle, Point startPoint) {
        this.tool = tool;
        this.handle = handle;
        this.startPoint = startPoint;
        this.initialPoint = startPoint;
    }
    @Override
    public void handleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {

    }

    @Override
    public void handleMouseDrag(MouseEvent e, ShapeModel model, Point movePoint) {
        Point currentPoint = e.getPoint();
        handle.resize(startPoint, currentPoint);
        this.startPoint = currentPoint;
    }

    @Override
    public void handleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        ResizeCommand resizeCommand = new ResizeCommand(handle, initialPoint, startPoint);
        CommandManagerSingleton.getInstance().executeCommand(resizeCommand);
        tool.setState(new IdleState(tool));
    }
}
