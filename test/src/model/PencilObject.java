package test.src.model;

import java.awt.*;
import java.awt.geom.Path2D;

public class PencilObject extends ShapeObject{
    private Path2D path;

    // 연필 도구의 초기화 - 시작점을 받아서 Path2D 객체를 생성
    public PencilObject(Point startPoint, Color fillColor, Color strokeColor) {
        super(startPoint, 0, 0, fillColor, strokeColor);
        path = new Path2D.Double();
        path.moveTo(startPoint.x, startPoint.y);
    }

    // 마우스가 이동할 때마다 새로운 점을 추가
    public void addPoint(Point point) {
        path.lineTo(point.x, point.y);
    }

    // Path2D 객체를 반환하여 화면에 그릴 때 사용
    @Override
    public Shape getShape() {
        return path;
    }

    // 연필 도구는 내부적으로 Path2D로 구성되므로 그릴 때는 Stroke만 필요
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(strokeColor);
        g2d.draw(path);
    }
}
