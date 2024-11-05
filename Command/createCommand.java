package Command;

import Factory.ShapeFactory;

import java.awt.*;

public class createCommand implements Command {
    private ShapeFactory factory;
    private Point startPoint;
    private Color fillColor;
    private Color strokeColor;

    public createCommand(ShapeFactory factory, Point startPoint,Color fillColor, Color strokeColor){
        this.factory = factory;
        this.startPoint = startPoint;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }

    @Override
    public void execute() {
        factory.createShape(startPoint, fillColor, strokeColor);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
