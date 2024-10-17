package test.src.strategy;

import test.src.model.ShapeObject;

import java.awt.*;

public interface DrawStrategy {
    void drawShape(Graphics2D g, Point startPoint, Point endPoint, ShapeObject shape);
}
