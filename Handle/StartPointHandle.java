package Handle;

import java.awt.*;
import Object.ShapeObject;
import State.ResizeState;
import State.SelectionTool;
import State.SelectionToolState;
import model.ShapeModel;

public class StartPointHandle extends ResizeHandle {
    private static final int HANDLE_SIZE = 10;
    public StartPointHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Point startPoint = shape.getPosition();
        return new Rectangle(
                startPoint.x - HANDLE_SIZE / 2,
                startPoint.y - HANDLE_SIZE / 2,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
    }

    @Override
    public void resize(Point startPoint, Point endPoint) {
        shape.setPosition(new Point(endPoint.x, endPoint.y));
    }

    @Override
    public boolean contains(Point point) {
        return getBounds().contains(point);
    }

    @Override
    public SelectionToolState setState(SelectionTool tool, ShapeModel model, Point point) {
//        return new ResizeState(tool, this);
        return null;
    }
}
