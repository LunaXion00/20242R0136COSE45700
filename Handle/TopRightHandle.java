package Handle;

import java.awt.*;
import Object.ShapeObject;

public class TopRightHandle extends ResizeHandle{
    @Override
    public void resize(ShapeObject shape, Point startPoint, Point endPoint) {
        int newWidth = Math.abs(shape.getWidth() + (endPoint.x - startPoint.x));
        int newHeight = Math.abs(shape.getHeight() + (startPoint.y - endPoint.y));
        int newX = Math.min(startPoint.x - shape.getWidth(), endPoint.x);
        int newY = Math.min(startPoint.y + shape.getHeight(), endPoint.y);

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
