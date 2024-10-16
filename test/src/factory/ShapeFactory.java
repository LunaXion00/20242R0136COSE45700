package test.src.factory;

import test.src.model.ShapeObject;

import java.awt.*;

public interface ShapeFactory {
    ShapeObject createShape(Point startPoint, Color strokeColor);
}

