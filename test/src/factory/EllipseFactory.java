package test.src.factory;

import test.src.model.EllipseObject;
import test.src.model.ShapeObject;

import java.awt.*;

public class EllipseFactory implements ShapeFactory{
    @Override
    public ShapeObject createShape(Point startPoint, Color fillColor, Color strokeColor) {
        return new EllipseObject(startPoint, 0, 0, fillColor, strokeColor);
    }
}
