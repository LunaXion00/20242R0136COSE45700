package test.src.state;

import test.src.model.RectangleObject;
import test.src.model.ShapeModel;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class RectangleState implements ToolState {
    private RectangleObject currentRectangle;
    @Override
    public void mousePressed(MouseEvent e, ShapeModel model, Point startPoint) {
        currentRectangle = new RectangleObject(startPoint, 0, 0, java.awt.Color.BLUE, java.awt.Color.BLACK);
        model.addShape(currentRectangle);
    }

    @Override
    public void mouseDragged(MouseEvent e, ShapeModel model, Point startPoint) {
        Point currentPoint = e.getPoint();
        currentRectangle.setWidth(currentPoint.x - startPoint.x);
        currentRectangle.setHeight(currentPoint.y - startPoint.y);;
    }

    @Override
    public void mouseReleased(MouseEvent e, ShapeModel model, Point startPoint) {
        // 마우스 릴리즈 시 별도의 작업 필요 없음
    }
}