package Handle;

import java.awt.*;
import Object.ShapeObject;
import State.SelectionTool;
import State.SelectionToolState;
import model.ShapeModel;

public class MidRightHandle extends ResizeHandle{
    private static final int HANDLE_SIZE = 10;
    public MidRightHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = shape.getBounds();
        return new Rectangle(
                bounds.x + bounds.width - HANDLE_SIZE / 2,
                bounds.y + bounds.height / 2 - HANDLE_SIZE / 2,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
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

    @Override
    public boolean contains(Point point) {
        return getBounds().contains(point);    }

    @Override
    public SelectionToolState setState(SelectionTool tool, ShapeModel model, Point point) {
        return null;
    }
}
