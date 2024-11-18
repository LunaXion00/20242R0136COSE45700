package Handle;

import java.awt.*;
import Object.ShapeObject;

public class TopMidHandle extends ResizeHandle {
    public TopMidHandle(ShapeObject shape) {
        super(shape);
    }
    @Override
    public void resize(Point startPoint, Point endPoint) {
        int newWidth = shape.getWidth();
        int newHeight = Math.abs(shape.getHeight() + (startPoint.y - endPoint.y));
        int newX = shape.getPosition().x;
        int newY = Math.min(shape.getPosition().y + shape.getHeight(), endPoint.y);

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
