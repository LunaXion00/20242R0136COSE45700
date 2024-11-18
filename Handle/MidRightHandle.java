package Handle;

import java.awt.*;
import Object.ShapeObject;

public class MidRightHandle extends ResizeHandle{
    public MidRightHandle(ShapeObject shape) {
        super(shape);
    }
    @Override
    public void resize(Point startPoint, Point endPoint) {
        int newWidth = Math.abs(shape.getWidth() + (endPoint.x - startPoint.x));
        int newHeight = shape.getHeight();
        int newX = Math.min(shape.getPosition().x, endPoint.x);
        int newY = shape.getPosition().y;

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
