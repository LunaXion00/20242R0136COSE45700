package Factory;

import java.awt.*;
import Object.ShapeObject;
import Object.RectangleObject;

public class RectangleFactory implements ShapeFactory{
    @Override
    public ShapeObject createShape(Point startPoint,  Color fillColor, Color strokeColor) {
        return new RectangleObject(startPoint, 0, 0, fillColor, strokeColor);
    }
}
