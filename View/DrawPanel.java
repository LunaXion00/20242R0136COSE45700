// View/DrawPanel.java
package View;

import Controller.VectorGraphicController;
import Object.*;
import Singleton.SelectionManagerSingleton;
import State.SelectionTool;
import State.TextTool;
import State.Tool;
import model.SelectionManager;
import model.ShapeModel;


import javax.swing.*;
import java.awt.*;
import Observer.Observer;

public class DrawPanel extends JPanel implements Observer{
    private ShapeModel model;
    private VectorGraphicController controller;
    private SelectionManager selectionManager = SelectionManagerSingleton.getInstance();
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
        ShapeObject drawingObject = controller.getCurrentTool().getDrawingObject();
        if (drawingObject != null){
            drawingObject.draw(g2d);
        }
        for (ShapeObject shape : model.getShapes()) {
            ShapeObject drawableShape = shape;
            if (selectionManager.getSelectedObject().getComponents().contains(shape)) {
                drawableShape = new HighlightDecorator(drawableShape);
            }
            drawableShape.draw(g2d);
        }
        Rectangle selectionBox = selectionManager.getSelectionBox();
        if (selectionBox != null) {
            Stroke originalStroke = g2d.getStroke();
            float[] dashPattern = {5, 5};
            Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0);
            g2d.setStroke(dashedStroke);
            g2d.setColor(Color.GRAY);
            g2d.draw(selectionBox);
            g2d.setStroke(originalStroke);
        }
    }
}
