package Handle;

import java.awt.*;
import Object.ShapeObject;

public class StartPointHandle extends ResizeHandle {
    public StartPointHandle(ShapeObject shape) {
        super(shape);
    }
    @Override
    public void resize(Point startPoint, Point endPoint) {
        shape.setPosition(new Point(endPoint.x, endPoint.y));
    }
}
