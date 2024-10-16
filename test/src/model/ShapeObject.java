package test.src.model;

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

    public abstract Shape getShape();

    public Point getPosition() { return position; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getFillColor() { return fillColor; }
    public Color getStrokeColor() { return strokeColor; }

    public void setPosition(Point position) { this.position = position; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public void setFillColor(Color fillColor) { this.fillColor = fillColor; }
    public void setStrokeColor(Color strokeColor) { this.strokeColor = strokeColor; }

    // 연필 도구는 내부적으로 Path2D로 구성되므로 그릴 때는 Stroke만 필요
    public abstract void draw(Graphics2D g2d);
}
