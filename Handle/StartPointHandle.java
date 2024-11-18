package Handle;

import java.awt.*;
import Object.ShapeObject;

public class StartPointHandle extends ResizeHandle {
    @Override
    public void resize(ShapeObject shape, Point startPoint, Point endPoint) {
        shape.setPosition(new Point(endPoint.x, endPoint.y));
    }
}
