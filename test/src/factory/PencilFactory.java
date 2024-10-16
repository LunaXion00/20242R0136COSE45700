package test.src.factory;

import test.src.model.PencilObject;
import test.src.model.ShapeObject;

import java.awt.*;

public abstract class PencilFactory implements ShapeFactory{
    @Override
    public ShapeObject createShape(Point startPoint, Color strokeColor){
        return new PencilObject(startPoint, strokeColor);
    }
}
