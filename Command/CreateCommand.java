package Command;

import Factory.ShapeFactory;
import Object.ShapeObject;
import model.ShapeModel;

import java.awt.*;

public class CreateCommand implements Command {
    private ShapeObject shape;
    private ShapeModel model;

    public CreateCommand(ShapeModel model, ShapeObject shape){
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

    @Override
    public void redo() {
        execute();
    }
}
