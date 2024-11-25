package Handle;

import java.awt.*;
import Object.ShapeObject;

public abstract class ResizeHandle {
    protected ShapeObject shape;
    public ResizeHandle(ShapeObject shape){
        this.shape = shape;
    }
    public abstract Rectangle getBounds();
    public abstract void resize(Point startPoint, Point endPoint);
}
