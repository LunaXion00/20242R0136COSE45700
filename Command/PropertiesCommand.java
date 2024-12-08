package Command;

import Object.ShapeObject;

import java.awt.*;

public class PropertiesCommand implements Command{
    private final ShapeObject shape;
    private final String property;
    private final int oldValue;
    private final int newValue;

    public PropertiesCommand(ShapeObject shape, String property, int oldValue, int newValue) {
        this.shape = shape;
        this.property = property;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        applyValue(newValue);
    }

    @Override
    public void undo() {
        applyValue(oldValue);
    }

    @Override
    public void redo() {
        execute();
    }

    private void applyValue(int value) {
        switch (property) {
            case "X" -> shape.setPosition(new Point(value, shape.getPosition().y));
            case "Y" -> shape.setPosition(new Point(shape.getPosition().x, value));
            case "Width" -> shape.setwidth(Math.max(1, value));
            case "Height" -> shape.setheight(Math.max(1, value));
        }
    }
}
