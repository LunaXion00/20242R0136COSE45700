package Factory;

import java.awt.*;
import Object.*;

public class TextBoxFactory implements ShapeFactory {
    @Override
    public ShapeObject createShape(Point position,  Color fillColor, Color strokeColor) {
        return new TextBoxObject(position, 0, 0, fillColor);
    }
}
