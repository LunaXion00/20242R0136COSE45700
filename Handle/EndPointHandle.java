package Handle;

import java.awt.*;
import Object.ShapeObject;

public class EndPointHandle extends ResizeHandle {
    private static final int HANDLE_SIZE = 4;
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
}
