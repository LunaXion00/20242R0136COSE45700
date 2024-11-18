package Handle;

import java.awt.*;
import Object.ShapeObject;

public class MidLeftHandle extends ResizeHandle{
    @Override
    public void resize(ShapeObject shape, Point startPoint, Point endPoint) {
        int newWidth = Math.abs(shape.getWidth() + (startPoint.x - endPoint.x));
        int newHeight = shape.getHeight();
        int newX = Math.min(startPoint.x + shape.getWidth(), endPoint.x);
        int newY = shape.getPosition().y;

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
