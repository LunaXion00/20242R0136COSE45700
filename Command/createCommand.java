package Command;

import Factory.ShapeFactory;
import Object.ShapeObject;
import model.ShapeModel;

import java.awt.*;

public class createCommand implements Command {
    private ShapeFactory factory;
    private ShapeObject shape;
    private ShapeModel model;
    private Point startPoint;
    private Color fillColor;
    private Color strokeColor;

    public createCommand(ShapeModel model, ShapeFactory factory, Point startPoint, Color fillColor, Color strokeColor){
        this.model = model;
        this.factory = factory;
        this.startPoint = startPoint;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }

    @Override
    public void execute() {
        shape = factory.createShape(startPoint, fillColor, strokeColor);
        model.addShape(shape);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
