// View/DrawPanel.java
package View;

import Controller.VectorGraphicController;
import Object.*;
import State.SelectionTool;
import State.TextTool;
import State.Tool;
import model.ShapeModel;


import javax.swing.*;
import java.awt.*;
import Observer.Observer;

public class DrawPanel extends JPanel implements Observer{
    private ShapeModel model;
    private VectorGraphicController controller;

    public DrawPanel(ShapeModel model, VectorGraphicController controller) {
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
            if (controller.getCurrentTool() instanceof SelectionTool && model.getSelectedShapes().contains(shape)) {
                Color originalColor = g2d.getColor();
                g2d.setColor(Color.BLUE);  // 강조 색상 설정
                g2d.draw(shape.getBounds());
                g2d.setColor(originalColor);
            }
            if (controller.getCurrentTool() instanceof TextTool && shape instanceof TextBoxObject){
                TextBoxObject textBox = (TextBoxObject) shape;
                if(textBox.isEditing()){
                    Color originalColor = g2d.getColor();
                    g2d.setColor(Color.BLUE);  // 강조 색상 설정
                    g2d.draw(shape.getBounds());
                    g2d.setColor(originalColor);
                }
            }
        }
        Tool currentTool = controller.getCurrentTool();
        if (currentTool instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) currentTool;
            Rectangle selectionRect = selectionTool.getSelectionRect();
            if (selectionRect != null) {
                float[] dashPattern = {5, 5};
                Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0);
                g2d.setStroke(dashedStroke);
                g2d.setColor(Color.GRAY);
                g2d.draw(selectionRect);  // 드래그 중 선택 경계 표시
            }
        }
    }
}
