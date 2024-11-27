package State;

import Singleton.SelectionManagerSingleton;
import model.SelectionManager;
import model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectionBoxState implements SelectionToolState{
    private final SelectionTool tool;
    private final SelectionManager selectionManager = SelectionManagerSingleton.getInstance();
    private final Rectangle selectionBox;

    public SelectionBoxState(SelectionTool tool, Point startPoint) {
        this.tool = tool;
        this.selectionBox = new Rectangle(startPoint);
    }

    @Override
    public void handleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {

    }

    @Override
    public void handleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
        Point currentPoint = e.getPoint();
        selectionBox.setBounds(
                Math.min(startPoint.x, currentPoint.x),
                Math.min(startPoint.y, currentPoint.y),
                Math.abs(currentPoint.x - startPoint.x),
                Math.abs(currentPoint.y - startPoint.y)
        );
    }

    @Override
    public void handleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        selectionManager.updateSelectedObject(model, selectionBox);
        tool.setState(new IdleState(tool));
    }
    public Rectangle getSelectionBox(){
        return selectionBox;
    }
}
