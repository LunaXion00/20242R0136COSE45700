package State;

import java.awt.*;
import java.awt.event.MouseEvent;

import Factory.ShapeFactory;
import Object.ShapeObject;
import model.ShapeModel;

public class CreationTool implements Tool{
    private ShapeObject currentShape;
    private Color strokeColor;
    private Color fillColor;
    private ShapeFactory factory;

    public CreationTool(ShapeFactory factory,  Color fillColor, Color strokeColor){
        this.factory = factory;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }
    @Override
    public void HandleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {
        currentShape = factory.createShape(startPoint, fillColor, strokeColor);
        model.addShape(currentShape);
    }

    @Override
    public void HandleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
        Point currentPoint = e.getPoint();
        currentShape.setwidth(currentPoint.x - startPoint.x);
        currentShape.setheight(currentPoint.y - startPoint.y);
    }

    @Override
    public void HandleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        currentShape.setPosition(new Point(Math.min(e.getX(), startPoint.x), Math.min(e.getY(), startPoint.y)));
        currentShape.setwidth(Math.abs(e.getX() - startPoint.x));
        currentShape.setheight(Math.abs(e.getY() - startPoint.y));
        currentShape = null;
    }

    @Override
    public void onDeactivate(Component component) {

    }

    @Override
    public void setCurrentColor(Color color) {
        this.fillColor = color;
    }
}
