package Factory;

import java.awt.*;
import Object.ShapeObject;

public interface ShapeFactory {
    ShapeObject createShape(Point startPoint, Color fillColor, Color strokeColor);
}