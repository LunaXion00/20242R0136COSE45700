package Handle;

import java.awt.*;
import Object.ShapeObject;

public class EndPointHandle extends ResizeHandle {
    @Override
    public void resize(ShapeObject shape, Point startPoint, Point endPoint) {
        shape.setEndPoint(new Point(endPoint.x, endPoint.y));
    }
}
