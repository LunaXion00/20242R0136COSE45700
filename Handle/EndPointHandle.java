package Handle;

import java.awt.*;
import Object.ShapeObject;
import State.SelectionTool;
import State.SelectionToolState;
import model.ShapeModel;

public class EndPointHandle extends ResizeHandle {
    private static final int HANDLE_SIZE = 6;
    public EndPointHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Point endPoint = shape.getEndPoint();
        return new Rectangle(
                endPoint.x - HANDLE_SIZE / 2,
                endPoint.y - HANDLE_SIZE / 2,
                HANDLE_SIZE,
                HANDLE_SIZE
        );
    }

    @Override
    public void resize(Point startPoint, Point endPoint) {
        shape.setEndPoint(new Point(endPoint.x, endPoint.y));
    }

    @Override
    public boolean contains(Point point) {
        return getBounds().contains(point);
    }

    @Override
    public void endEdit(){

    }

}
