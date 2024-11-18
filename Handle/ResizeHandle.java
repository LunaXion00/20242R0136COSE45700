package Handle;

import java.awt.*;
import Object.ShapeObject;

public abstract class ResizeHandle {

    public abstract void resize(ShapeObject shape, Point startPoint, Point endPoint);
}
