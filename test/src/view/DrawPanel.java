package test.src.view;
import test.src.model.ShapeModel;
import test.src.model.ShapeObject;
import test.src.observer.Observer;
import test.src.controller.VectorGraphicsController;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel implements Observer{
    private ShapeModel model;
    private VectorGraphicsController controller;

    public DrawPanel(ShapeModel model, VectorGraphicsController controller) {
        this.model = model;
        this.controller = controller;
        setBackground(Color.WHITE);
        model.addObserver(this);
    }
    @Override
    public void update() {
        repaint();  // 모델이 변경되면 다시 그리기
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 모든 도형에 대해 draw() 메서드를 호출하여 화면에 그리기
        for (ShapeObject shape : model.getShapes()) {
            shape.draw(g2d); // 각 도형의 draw() 호출
        }
        if (controller.getCurrentPoint() != null) {
            Point startPoint = controller.getStartPoint();
            Point currentPoint = controller.getCurrentPoint();

            // 미리보기 도형 그리기 (예: 직사각형)
            g2d.setColor(Color.GRAY);  // 미리보기 색상
            g2d.drawRect(Math.min(startPoint.x, currentPoint.x),
                    Math.min(startPoint.y, currentPoint.y),
                    Math.abs(currentPoint.x - startPoint.x),
                    Math.abs(currentPoint.y - startPoint.y));
        }
    }
}