package Factory;

import java.awt.*;
import Object.ShapeObject;
import Object.EllipseObject;

public class EllipseFactory implements ShapeFactory{
    @Override
    public ShapeObject createShape(Point startPoint,  Color fillColor, Color strokeColor) {
        return new EllipseObject(startPoint, 0, 0, fillColor, strokeColor);
    }
}