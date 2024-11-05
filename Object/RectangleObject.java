package Object;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RectangleObject extends ShapeObject {

    public RectangleObject(Point position, int width, int height, Color fillColor, Color strokeColor) {
        super(position, width, height, fillColor, strokeColor);
    }

    @Override
    public Shape getShape() {
        int x = Math.min(position.x, position.x + width);
        int y = Math.min(position.y, position.y + height);
        int w = Math.abs(width);
        int h = Math.abs(height);

        return new Rectangle2D.Double(x, y, w, h);
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
    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        position.translate(dx, dy);
        width += dw;
        height += dh;
    }
}
