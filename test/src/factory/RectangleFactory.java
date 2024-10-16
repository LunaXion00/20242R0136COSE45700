package test.src.factory;


import test.src.model.RectangleObject;
import test.src.model.ShapeObject;

import java.awt.*;

public class RectangleFactory implements ShapeFactory {

    @Override
    public ShapeObject createShape(Point startPoint, Color fillColor, Color strokeColor) {
        return new RectangleObject(startPoint, 0, 0, fillColor, strokeColor);
    }
}
