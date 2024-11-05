package Object;

import java.awt.*;

public abstract class ShapeObject {
    protected Point position;
    protected int width, height;
    protected Color fillColor, strokeColor;

    public ShapeObject(Point position, int width, int height, Color fillColor, Color strokeColor) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    public void setwidth(int width) {
        if(width == 0) this.width = 1;
        else this.width = width;
    }
    public void setheight(int height) {
        if(height == 0) this.height = 1;
        else this.height = height;
    }
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        int x = Math.min(position.x, position.x + width);
        int y = Math.min(position.y, position.y + height);
        return new Point(x,y);
    }
    public Rectangle getBounds() {
        int x = Math.min(position.x, position.x + width);
        int y = Math.min(position.y, position.y + height);
        int w = Math.abs(this.width);
        int h = Math.abs(this.height);

        return new Rectangle(x,y,w,h);
    }
    public abstract Shape getShape();
    public int getWidth() {
        return Math.abs(width);
    }
    public int getHeight() {
        return Math.abs(height);
    }

    public void move(int dx, int dy) {
        position.translate(dx, dy);
    }
    public abstract void resize(int dx, int dy, int dw, int dh);
    public boolean contains(Point point) {
        return getBounds().contains(point);
    }
    public abstract void draw(Graphics2D g2d);
}
