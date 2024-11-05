package Factory;

import Object.ShapeObject;
import Object.PencilObject;
import java.awt.*;

public class PencilFactory implements ShapeFactory {
    @Override
    public ShapeObject createShape(Point startPoint, Color fillColor, Color strokeColor) {
        return new PencilObject(startPoint, fillColor, strokeColor);
    }
}
