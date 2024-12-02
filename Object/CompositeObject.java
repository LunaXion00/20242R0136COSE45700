package Object;

import Handle.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeObject extends ShapeObject{
    private List<ShapeObject> components = new ArrayList<ShapeObject>();

    public CompositeObject(Point position, int width, int height, Color fillColor, Color strokeColor) {
        super(position, width, height, fillColor, strokeColor);
    }

    public List<ShapeObject> getComponents() {
        return components;
    }

    @Override
    public List<ResizeHandle> getResizeHandleList() {
        List<ResizeHandle> handles = new ArrayList<>();
        if (components.isEmpty()) {
            return handles;
        }

        for (ShapeObject shape : components) {
            handles.addAll(shape.getResizeHandleList());
        }
        return handles;
    }

    @Override
    public void setEndPoint(Point endPoint) {

    }

    public void add(ShapeObject shape) {
        if (shape == this) {
            throw new IllegalArgumentException("Cannot add self to components");
        }
        if (!components.contains(shape) && shape != null) {
            components.add(shape); // 중복 방지
            updateBounds();
        }
    }
    public void remove(ShapeObject shape){
        components.remove(shape);
    }

    public void clear(){
        components.clear();
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
    @Override
    public void move(int dx, int dy){
        for (ShapeObject shape : components) {
            shape.move(dx, dy);
        }
        setPosition(new Point(getPosition().x + dx, getPosition().y + dy));
        updateBounds();
    }
    @Override
    public boolean contains(Point point){
        if (components.isEmpty()) return false;
        // composite 안에 하나라도 hit test를 만족하면 true
        for(ShapeObject shape:components){
            if(shape.contains(point)) return true;
        }
        return false;
    }
    public void updateBounds() {
        if (components.isEmpty()) {
            setPosition(new Point(0, 0));
            setwidth(0);
            setheight(0);
            return;
        }

        Rectangle unionBounds = null;

        for (ShapeObject shape : components) {
            Rectangle shapeBounds = shape.getBounds();
            if (unionBounds == null) {
                unionBounds = new Rectangle(shapeBounds);
            } else {
                unionBounds = unionBounds.union(shapeBounds);
            }
        }

        // 그룹의 Bounds를 구성 요소들의 합으로 설정
        setPosition(unionBounds.getLocation());
        setwidth(unionBounds.width);
        setheight(unionBounds.height);
    }

}
