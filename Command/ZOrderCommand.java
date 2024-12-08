package Command;
import Object.ShapeObject;
import Singleton.ShapeModelSingleton;
import model.ShapeModel;

public class ZOrderCommand implements Command{
    private final ShapeObject shape;
    private final ShapeModel model = ShapeModelSingleton.getInstance();
    private final int oldIndex;
    private final int newIndex;
    public enum Destination {
        UP, DOWN, TOP, BOTTOM
    }
    public ZOrderCommand(ShapeObject shape, Destination destination) {
        this.shape = shape;
        this.oldIndex = model.getShapes().indexOf(shape);
        switch (destination) {
            case UP -> this.newIndex = Math.min(oldIndex + 1, model.getShapes().size() - 1);
            case DOWN -> this.newIndex = Math.max(oldIndex - 1, 0);
            case TOP -> this.newIndex = model.getShapes().size() - 1;
            case BOTTOM -> this.newIndex = 0;
            default -> throw new IllegalArgumentException("Invalid ZOrder destination");
        }
    }

    @Override
    public void execute() {
        model.getShapes().remove(oldIndex);
        model.getShapes().add(newIndex, shape);
    }

    @Override
    public void undo() {
        model.getShapes().remove(newIndex);
        model.getShapes().add(oldIndex, shape);
    }

    @Override
    public void redo() {
        execute();
    }
}
