package Handle;

import java.awt.*;
import Object.ShapeObject;

public class BotLeftHandle extends ResizeHandle {
    public BotLeftHandle(ShapeObject shape) {
        super(shape);
    }
    @Override
    public void resize(Point startPoint, Point endPoint) {
        int newWidth = Math.abs(shape.getWidth() + (startPoint.x - endPoint.x));
        int newHeight = Math.abs(shape.getHeight() + (endPoint.y - startPoint.y));
        int newX = Math.min(shape.getPosition().x + shape.getWidth(), endPoint.x);
        int newY = Math.min(shape.getPosition().y, endPoint.y);

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
