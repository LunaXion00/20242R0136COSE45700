package State;

import java.awt.*;
import java.awt.event.MouseEvent;
import model.ShapeModel;

public interface Tool {
    void HandleMousePress(MouseEvent e, ShapeModel model, Point startPoint);
    void HandleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint);
    void HandleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint);
    void onDeactivate(Component component);
    void setCurrentColor(Color color);
}
