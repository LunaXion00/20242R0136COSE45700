package Handle;

import java.awt.*;
import Object.ShapeObject;

public class BotMidHandle extends ResizeHandle {
    private static final int HANDLE_SIZE = 4;
    public BotMidHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = shape.getBounds();
        return new Rectangle(
                bounds.x + bounds.width / 2 - HANDLE_SIZE / 2,
                bounds.y + bounds.height - HANDLE_SIZE / 2,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
    }

    @Override
    public void resize(Point startPoint, Point endPoint) {
        int newWidth = shape.getWidth();
        int newHeight = Math.abs(shape.getHeight() + (endPoint.y - startPoint.y));
        int newX = shape.getPosition().x;
        int newY = Math.min(shape.getPosition().y, endPoint.y);

        shape.setPosition(new Point(newX, newY));
        shape.setwidth(newWidth);
        shape.setheight(newHeight);
    }
}
