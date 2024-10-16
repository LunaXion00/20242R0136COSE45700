package test.src.state;

import test.src.model.ShapeModel;

import java.awt.Point;
import java.awt.event.MouseEvent;

public interface DrawState {
    void mousePressed(MouseEvent e, ShapeModel model, Point startPoint);
    void mouseDragged(MouseEvent e, ShapeModel model, Point startPoint);
    void mouseReleased(MouseEvent e, ShapeModel model, Point startPoint);
}