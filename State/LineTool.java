package State;

import Factory.ShapeFactory;
import model.ShapeModel;
import Object.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool implements Tool {
    private ShapeObject currentShape;
    private Color strokeColor;
    private Color fillColor;
    private ShapeFactory factory;

    public LineTool(ShapeFactory factory,  Color fillColor, Color strokeColor){
        this.factory = factory;
        this.strokeColor = strokeColor;
        this.fillColor = null;
    }
    @Override
    public void HandleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {
        currentShape = factory.createShape(startPoint, strokeColor, fillColor);
        model.addShape(currentShape);
    }

    @Override
    public void HandleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
        LineObject line = (LineObject) model.getShapes().get(model.getShapes().size() - 1);
        line.setEndPoint(e.getPoint());
    }

    @Override
    public void HandleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {

    }

    @Override
    public void onDeactivate(Component component) {

    }

    @Override
    public void setCurrentColor(Color color) {

    }
}
