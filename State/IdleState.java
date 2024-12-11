package State;

import Handle.ResizeHandle;
import Singleton.SelectionManagerSingleton;
import model.SelectionManager;
import model.ShapeModel;
import Object.ShapeObject;

import java.awt.*;
import java.awt.event.MouseEvent;

public class IdleState implements SelectionToolState {
    private SelectionTool tool;
    private final SelectionManager selectionManager;
    public IdleState(SelectionTool tool){
        this.tool = tool;
        this.selectionManager = SelectionManagerSingleton.getInstance();
    }

    @Override
    public void handleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {
        ShapeObject clickedShape = selectionManager.getShapesAt(startPoint);
        if (clickedShape != null) {
            for (ResizeHandle handle : clickedShape.getResizeHandleList()) {
                if (handle.contains(startPoint)) {
                    tool.setState(new ResizeState(tool, handle, startPoint));
                    return;
                }
            }
            tool.setState(new MoveState(tool, selectionManager.getSelectedObject(), startPoint));
        }
        else {
            tool.setState(new SelectionBoxState(tool, startPoint));
        }
        System.out.println("Tool:"+ tool.getState().getClass().getSimpleName());
    }

    @Override
    public void handleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
    }

    @Override
    public void handleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
    }
}
