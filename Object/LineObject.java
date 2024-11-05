package Object;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineObject extends ShapeObject{
    private Line2D line;
    private Point endPoint;
    public LineObject(Point position, Color strokeColor) {
        super(position,0, 0, null, strokeColor);
        line = new Line2D.Double(position, position);
        endPoint = position;
    }
    @Override
    public Shape getShape() {
        return line;
    }
    @Override
    public Rectangle getBounds() {
        int x= Math.min(position.x, endPoint.x);
        int y= Math.min(position.y, endPoint.y);
        int width= Math.abs(position.x-endPoint.x);
        int height= Math.abs(position.y-endPoint.y);
        return new Rectangle(x, y, width,height);
    }
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        this.line.setLine(position, endPoint);
    }
    @Override
    public void move(int dx, int dy) {
        position.translate(dx, dy);
        endPoint.translate(dx, dy);
        line.setLine(position, endPoint);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(strokeColor);
        g2d.draw(line);
    }
    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        position.translate(dx, dy);
        endPoint.translate(dw, dh);
        line.setLine(position, endPoint);
    }
}
