package Handle;

import java.awt.*;
import Object.ShapeObject;

public class MidLeftHandle extends ResizeHandle{
    public MidLeftHandle(ShapeObject shape) {
        super(shape);
    }
    @Override
    public void resize(Point startPoint, Point endPoint) {
        int newWidth = Math.abs(shape.getWidth() + (startPoint.x - endPoint.x));
        int newHeight = shape.getHeight();
        int newX = Math.min(startPoint.x + shape.getWidth(), endPoint.x);
        int newY = shape.getPosition().y;

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
