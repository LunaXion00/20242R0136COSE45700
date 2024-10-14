package test.src.command;

import test.src.model.ShapeModel;
import test.src.model.ShapeObject;

public class AddShapeCommand implements Command {
    private ShapeModel model;
    private ShapeObject shape;

    public AddShapeCommand(ShapeModel model, ShapeObject shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.addShape(shape);
    }

    @Override
    public void undo() {
        model.deleteShape(shape);
    }
}