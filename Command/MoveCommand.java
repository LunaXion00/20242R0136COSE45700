package Command;

import Object.ShapeObject;

import java.awt.*;

public class MoveCommand implements Command{
    private final ShapeObject shape;
    private final Point initialPosition;
    private final Point finalPosition;

    public MoveCommand(ShapeObject shape, Point initialPosition, Point finalPosition) {
        this.shape = shape;
        this.initialPosition = new Point(initialPosition);
        this.finalPosition = new Point(finalPosition);
    }

    @Override
    public void execute() {
        shape.setPosition(finalPosition);
    }

    @Override
    public void undo() {
        shape.move(initialPosition.x-finalPosition.x, initialPosition.y - finalPosition.y);
    }

    @Override
    public void redo() {
        shape.move(finalPosition.x-initialPosition.x, finalPosition.y-initialPosition.y);
        execute();
    }
}
