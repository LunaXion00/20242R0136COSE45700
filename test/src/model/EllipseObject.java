package test.src.model;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseObject extends ShapeObject{
    public EllipseObject(Point position, int width, int height, Color fillColor, Color strokeColor) {
        super(position, width, height, fillColor, strokeColor);
    }

    @Override
    public Shape getShape() {
        return new Ellipse2D.Double(position.x, position.y, width, height) {
        };
    }
    @Override
    public void draw(Graphics2D g2d) {
        // 도형 채우기
        if (fillColor != null) {
            g2d.setColor(fillColor);
            g2d.fill(getShape());
        }
        // 외곽선 그리기
        g2d.setColor(strokeColor);
        g2d.draw(getShape());
    }
}
