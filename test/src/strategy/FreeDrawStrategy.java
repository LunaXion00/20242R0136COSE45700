package test.src.strategy;

import test.src.model.ShapeObject;

import java.awt.*;

public class FreeDrawStrategy implements DrawStrategy{
    @Override
    public void drawShape(Graphics2D g, Point startPoint, Point endPoint, ShapeObject shape) {
        int width = Math.abs(endPoint.x - startPoint.y);
        int height = Math.abs(endPoint.y - startPoint.y);

        shape.setWidth(width);
        shape.setHeight(height);
        shape.setPosition(new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y)));
        shape.draw(g);
    }
}
