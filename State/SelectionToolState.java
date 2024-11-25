package State;

import model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface SelectionToolState {
    void handleMousePress(MouseEvent e, ShapeModel model, Point startPoint);
    void handleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint);
    void handleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint);
}
