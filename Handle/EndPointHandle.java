package Handle;

import java.awt.*;
import Object.ShapeObject;

public class EndPointHandle extends ResizeHandle {
    public EndPointHandle(ShapeObject shape) {
        super(shape);
    }
    @Override
    public void resize(Point startPoint, Point endPoint) {
        shape.setEndPoint(new Point(endPoint.x, endPoint.y));
    }
}
