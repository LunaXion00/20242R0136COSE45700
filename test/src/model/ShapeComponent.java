package test.src.model;

import java.awt.*;
import java.util.ArrayList;

public class ShapeComponent extends ShapeObject{
    private ArrayList<ShapeObject> component = new ArrayList<>();

    public ShapeComponent(Point position, int width, int height, Color fillColor, Color strokeColor) {
        super(position, width, height, fillColor, strokeColor);
    }

    public void addShape(ShapeObject object){
        component.add(object);
        updateBounds();
    }
    public void removeShape(ShapeObject object){
        component.remove(object);
        updateBounds();
    }
    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2d) {

    }
    public void updateBounds(){
        if(component.isEmpty()) return;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (ShapeObject element: component){
            Point elementPosition = element.getPosition();
            minX = Math.min(minX, elementPosition.x);
            minY = Math.min(minY, elementPosition.y);
            maxX = Math.max(maxX, elementPosition.x + element.getWidth());
            maxY = Math.max(maxY, elementPosition.y + element.getHeight());
        }
        this.position = new Point(minX, minY);
        this.width = maxX - minX;
        this.height = maxY - minY;
    }
}
