package Command;

import Singleton.SelectionManagerSingleton;
import Singleton.ShapeModelSingleton;
import model.SelectionManager;
import model.ShapeModel;
import Object.*;

import java.awt.*;

public class DeleteCommand implements Command {
    private final ShapeModel model;
    private final CompositeObject deletedShapes;
    private final SelectionManager selectionManager = SelectionManagerSingleton.getInstance();
    public DeleteCommand() {
        this.model = ShapeModelSingleton.getInstance();
        this.deletedShapes = new CompositeObject(new Point(0, 0), 0, 0, null, null);
        for (ShapeObject shape : ((CompositeObject) selectionManager.getSelectedObject()).getComponents()) {
            deletedShapes.add(shape);
        }
    }

    @Override
    public void execute() {
        for (ShapeObject shape : deletedShapes.getComponents()) {
            model.getShapes().remove(shape); // Model에서 제거
        }
        selectionManager.clearManager();

    }

    @Override
    public void undo() {
        for (ShapeObject shape : deletedShapes.getComponents()) {
            model.addShape(shape); // Model에 복원
        }
    }

    @Override
    public void redo() {
        execute();
    }
}