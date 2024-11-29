package Handle;

import java.awt.*;
import Object.ShapeObject;
import State.SelectionTool;
import State.SelectionToolState;
import model.ShapeModel;

public class TopMidHandle extends ResizeHandle {
    private static final int HANDLE_SIZE = 10;
    public TopMidHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = shape.getBounds();
        return new Rectangle(
                bounds.x + bounds.width / 2 - HANDLE_SIZE / 2,
                bounds.y - HANDLE_SIZE / 2,
                HANDLE_SIZE,
                HANDLE_SIZE
        );

    }

    @Override
    public void resize(Point startPoint, Point endPoint) {
        int dy = endPoint.y-startPoint.y;
        int dh = -(endPoint.y-startPoint.y);
        shape.resize(0, dy, 0, dh);
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
