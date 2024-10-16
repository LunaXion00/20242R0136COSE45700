package test.src.factory;

import test.src.model.ShapeObject;
import test.src.model.LineObject;
import java.awt.*;

public class LineFactory implements ShapeFactory{
    @Override
    public ShapeObject createShape(Point startPoint, Color fillColor, Color strokeColor){
        return new LineObject(startPoint, startPoint, strokeColor);
    }
}
