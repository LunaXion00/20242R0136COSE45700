package Handle;

import java.awt.*;
import Object.ShapeObject;

public class BotMidHandle extends ResizeHandle {
    @Override
    public void resize(ShapeObject shape, Point startPoint, Point endPoint) {
        int newWidth = shape.getWidth();
        int newHeight = Math.abs(shape.getHeight() + (endPoint.y - startPoint.y));
        int newX = shape.getPosition().x;
        int newY = Math.min(shape.getPosition().y, endPoint.y);

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
