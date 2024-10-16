package test.src.state;

import test.src.model.EllipseObject;
import test.src.model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EllipseDraw implements DrawState{
    private EllipseObject currentEllipse;
    @Override
    public void mousePressed(MouseEvent e, ShapeModel model, Point startPoint) {
        currentEllipse = new EllipseObject(startPoint, 0, 0, Color.WHITE, Color.BLACK);
        model.addShape(currentEllipse);
    }

    @Override
    public void mouseDragged(MouseEvent e, ShapeModel model, Point startPoint) {
        Point currentPoint = e.getPoint();

        // 좌표 계산: 항상 양수 크기가 되도록 처리
        int x = Math.min(startPoint.x, currentPoint.x);
        int y = Math.min(startPoint.y, currentPoint.y);
        int width = Math.abs(currentPoint.x - startPoint.x);
        int height = Math.abs(currentPoint.y - startPoint.y);

        // 현재 타원의 위치와 크기를 업데이트
        currentEllipse.setPosition(new Point(x, y));
        currentEllipse.setWidth(width);
        currentEllipse.setHeight(height);
    }

    @Override
    public void mouseReleased(MouseEvent e, ShapeModel model, Point startPoint) {

    }
}
