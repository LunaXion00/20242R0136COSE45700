package test.src.factory;


import test.src.model.ShapeObject;
import java.awt.Point;

public interface ShapeFactory {
    ShapeObject createShape(Point startPoint);
}