package Handle;

import java.awt.*;
import Object.ShapeObject;

public abstract class ResizeHandle {
    ShapeObject shape;
    public ResizeHandle(ShapeObject shape){
        this.shape = shape;
    }
    public abstract void resize(Point startPoint, Point endPoint);
}
