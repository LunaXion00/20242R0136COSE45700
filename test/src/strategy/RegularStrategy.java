package test.src.strategy;

import test.src.model.ShapeObject;

import java.awt.*;

public class RegularStrategy implements DrawStrategy{
    @Override
    public void drawShape(Graphics2D g, Point startPoint, Point endPoint, ShapeObject shape) {
        int side = Math.max(Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y));
        shape.setWidth(side);
        shape.setHeight(side);
        shape.setPosition(new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y)));
        shape.draw(g);
    }
}