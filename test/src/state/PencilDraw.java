package test.src.state;

import test.src.model.PencilObject;
import test.src.model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PencilDraw implements DrawState{
    // Pencil을 고르고 마우스를 누르는 순간 객체 생성.
    @Override
    public void mousePressed(MouseEvent e, ShapeModel model, Point startPoint) {
        model.addShape(new PencilObject(startPoint, Color.WHITE, Color.BLACK));
    }
    // 드래그 하는 모양대로 그림 생성.
    @Override
    public void mouseDragged(MouseEvent e, ShapeModel model, Point startPoint) {
        PencilObject pencil = (PencilObject) model.getShapes().get(model.getShapes().size() - 1);
        pencil.addPoint(e.getPoint());
    }
    // 뗄 때는 별 다른 동작 X
    @Override
    public void mouseReleased(MouseEvent e, ShapeModel model, Point startPoint) {

    }
}
