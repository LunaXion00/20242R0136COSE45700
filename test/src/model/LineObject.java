package test.src.model;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineObject extends ShapeObject {
    private Point endPoint;  // 끝점
    private Line2D line;     // Line2D 객체 사용

    // 생성자
    public LineObject(Point startPoint, Point endPoint, Color strokeColor) {
        super(startPoint, 0, 0, null, strokeColor);
        this.endPoint = endPoint;
        this.line = new Line2D.Double(startPoint, endPoint);
    }

    // 끝점 설정
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        this.line.setLine(getPosition(), this.endPoint);  // Line2D 업데이트
    }

    @Override
    public Shape getShape() {
        return this.line;
    }

    // 그리기 메서드
    @Override
    public void draw(Graphics2D g) {
        g.setColor(strokeColor);   // 테두리 색 설정
        g.draw(line);              // 선 그리기
    }

    // 끝점 가져오기
    public Point getEndPoint() {
        return endPoint;
    }
}
