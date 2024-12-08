package Object;

import java.awt.*;

public abstract class ShapeDecorator extends ShapeObject{
    protected ShapeObject decoratedShape;
    public ShapeDecorator(ShapeObject shape) {
        super(shape.getPosition(), shape.getWidth(), shape.getHeight(), shape.getFillColor(), shape.getStrokeColor());
        this.decoratedShape = shape;
    }
    @Override
    public void draw(Graphics2D g2d){
        decoratedShape.draw(g2d);
        additionalDraw(g2d);
    }
    protected abstract void additionalDraw(Graphics2D g2d);
}
