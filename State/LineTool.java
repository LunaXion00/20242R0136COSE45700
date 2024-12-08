package State;

import Command.Command;
import Factory.ShapeFactory;
import Singleton.CommandManagerSingleton;
import model.ShapeModel;
import Command.CreateCommand;
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
    }

    @Override
    public void HandleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
        currentShape.setEndPoint(e.getPoint());
    }

    @Override
    public void HandleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        Command createCommand = new CreateCommand(model, currentShape);
        CommandManagerSingleton.getInstance().executeCommand(createCommand);
        currentShape = null;
    }

    @Override
    public void onDeactivate(Component component) {

    }

    @Override
    public void setCurrentColor(Color color) {

    }

    @Override
    public ShapeObject getDrawingObject() {
        return currentShape;
    }
}
