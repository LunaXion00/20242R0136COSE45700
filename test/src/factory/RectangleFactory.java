package test.src.factory;

import test.src.model.RectangleObject;
import java.awt.Color;
import java.awt.Point;

public class RectangleFactory implements ShapeFactory {
    @Override
    public RectangleObject createShape(Point startPoint) {
        return new RectangleObject(startPoint, 100, 50, Color.BLUE, Color.BLACK);
    }
}