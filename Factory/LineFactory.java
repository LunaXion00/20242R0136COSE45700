package Factory;

import java.awt.*;
import Object.ShapeObject;
import Object.LineObject;

public class LineFactory implements ShapeFactory {
    @Override
    public ShapeObject createShape(Point startPoint, Color fillColor, Color strokeColor) {
        return new LineObject(startPoint, strokeColor);
    }
}
