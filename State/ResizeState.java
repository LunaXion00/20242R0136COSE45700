package State;

import Handle.ResizeHandle;
import Singleton.SelectionManagerSingleton;
import model.SelectionManager;
import model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeState implements SelectionToolState{
    private final SelectionTool tool;
    private final ResizeHandle handle;
    private Point startPoint;
    public ResizeState(SelectionTool tool, ResizeHandle handle, Point startPoint) {
        this.tool = tool;
        this.handle = handle;
        this.startPoint = startPoint;
    }
    @Override
    public void handleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {

    }

    @Override
    public void handleMouseDrag(MouseEvent e, ShapeModel model, Point movePoint) {
        System.out.println("handle: " + handle.getClass().getSimpleName());
        Point currentPoint = e.getPoint();
        handle.resize(startPoint, currentPoint);
        this.startPoint = currentPoint;
    }

    @Override
    public void handleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        tool.setState(new IdleState(tool));
    }
}
