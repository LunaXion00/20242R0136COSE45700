package Handle;

import java.awt.*;
import Object.ShapeObject;
import State.SelectionTool;
import State.SelectionToolState;
import model.ShapeModel;

public class BotLeftHandle extends ResizeHandle {
    private static final int HANDLE_SIZE = 10;
    public BotLeftHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = shape.getBounds();
        return new Rectangle(
                bounds.x - HANDLE_SIZE / 2,
                bounds.y + bounds.height - HANDLE_SIZE / 2,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
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

    @Override
    public boolean contains(Point point) {
        return getBounds().contains(point);
    }

    @Override
    public SelectionToolState setState(SelectionTool tool, ShapeModel model, Point point) {
        return null;
    }
}
