package Object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeObject extends ShapeObject{
    private List<ShapeObject> components = new ArrayList<ShapeObject>();

    public CompositeObject(Point position, int width, int height, Color fillColor, Color strokeColor) {
        super(position, width, height, fillColor, strokeColor);
    }

    public void add(ShapeObject shape){
        components.add(shape);
    }

    public void remove(ShapeObject shape){
        components.remove(shape);
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void draw(Graphics2D g2d) {
        for(ShapeObject shape:components){
            shape.draw(g2d);
        }
    }
    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        for(ShapeObject shape:components){
            shape.resize(dx, dy, dw, dh);
        }
    }
}
