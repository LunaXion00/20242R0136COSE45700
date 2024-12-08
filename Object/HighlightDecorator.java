package Object;

import Handle.ResizeHandle;
import Object.ShapeObject;
import java.awt.*;
import java.util.List;

public class HighlightDecorator extends ShapeDecorator{

    public HighlightDecorator(ShapeObject shape) {
        super(shape);
    }

    @Override
    protected void additionalDraw(Graphics2D g2d) {
        Color originalColor = g2d.getColor();
        g2d.setColor(Color.BLUE);
        g2d.draw(decoratedShape.getBounds());
        for(ResizeHandle handle: decoratedShape.getResizeHandleList()){
            g2d.setColor(Color.BLACK);
            g2d.draw(handle.getBounds());
            g2d.setColor(originalColor);
        }
    }

    @Override
    public List<ResizeHandle> getResizeHandleList() {
        return null;
    }

    @Override
    public void setEndPoint(Point endPoint) {

    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void resize(int dx, int dy, int dw, int dh) {

    }
}
