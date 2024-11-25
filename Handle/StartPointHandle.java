package Handle;

import java.awt.*;
import Object.ShapeObject;

public class StartPointHandle extends ResizeHandle {
    private static final int HANDLE_SIZE = 4;
    public StartPointHandle(ShapeObject shape) {
        super(shape);
    }

    @Override
    public Rectangle getBounds() {
        Point startPoint =  shape.getPosition();
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
}
