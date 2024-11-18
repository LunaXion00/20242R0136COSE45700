package Handle;

import java.awt.*;
import Object.ShapeObject;

public class TopMidHandle extends ResizeHandle {
    @Override
    public void resize(ShapeObject shape, Point startPoint, Point endPoint) {
        int newWidth = shape.getWidth();
        int newHeight = Math.abs(shape.getHeight() + (startPoint.y - endPoint.y));
        int newX = shape.getPosition().x;
        int newY = Math.min(shape.getPosition().y + shape.getHeight(), endPoint.y);

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
