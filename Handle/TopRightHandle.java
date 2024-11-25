package Handle;

import java.awt.*;
import Object.ShapeObject;

public class TopRightHandle extends ResizeHandle{
    private static final int HANDLE_SIZE = 4;
    public TopRightHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = shape.getBounds();
        return new Rectangle(
                bounds.x + bounds.width - HANDLE_SIZE / 2,
                bounds.y - HANDLE_SIZE / 2,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
    }

    @Override
    public void resize(Point startPoint, Point endPoint) {
        int newWidth = Math.abs(shape.getWidth() + (endPoint.x - startPoint.x));
        int newHeight = Math.abs(shape.getHeight() + (startPoint.y - endPoint.y));
        int newX = Math.min(startPoint.x - shape.getWidth(), endPoint.x);
        int newY = Math.min(startPoint.y + shape.getHeight(), endPoint.y);

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
