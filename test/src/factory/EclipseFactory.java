package test.src.factory;

import test.src.model.EclipseObject;
import test.src.model.ShapeObject;

import java.awt.*;

public class EclipseFactory implements ShapeFactory{
    @Override
    public ShapeObject createShape(Point startPoint, Color strokeColor) {
        return new EclipseObject(startPoint, 0, 0, null, strokeColor);
    }
}
