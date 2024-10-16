package test.src.state;

import test.src.model.RectangleObject;
import test.src.model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleDraw implements DrawState{
    private RectangleObject currentRectangle;
    @Override
    public void mousePressed(MouseEvent e, ShapeModel model, Point startPoint) {
        // 마우스를 누르는 순간 점 상태의 사각형 객체 생성.
        currentRectangle = new RectangleObject(startPoint, 0, 0, Color.WHITE, Color.BLACK);
        model.addShape(currentRectangle);
    }

    @Override
    public void mouseDragged(MouseEvent e, ShapeModel model, Point startPoint) {
        Point currentPoint = e.getPoint();

        // 좌표 계산: 항상 양수 크기가 되도록 처리
        int x = Math.min(startPoint.x, currentPoint.x);
        int y = Math.min(startPoint.y, currentPoint.y);
        int width = Math.abs(currentPoint.x - startPoint.x);
        int height = Math.abs(currentPoint.y - startPoint.y);

        // 현재 사각형의 위치와 크기를 업데이트
        currentRectangle.setPosition(new Point(x, y));
        currentRectangle.setWidth(width);
        currentRectangle.setHeight(height);
    }

    @Override
    public void mouseReleased(MouseEvent e, ShapeModel model, Point startPoint) {
        // 마우스 릴리즈 시 별도의 작업 필요 없음
    }
}