package Handle;

import java.awt.*;

import Interface.Selectable;
import Object.ShapeObject;

public abstract class ResizeHandle implements Selectable {
    protected ShapeObject shape;
    public ResizeHandle(ShapeObject shape){
        this.shape = shape;
    }
    public abstract Rectangle getBounds();
    public abstract void resize(Point startPoint, Point endPoint);
    public abstract boolean contains(Point point);
}
