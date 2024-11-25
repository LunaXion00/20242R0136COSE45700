package Interface;

import State.SelectionTool;
import State.SelectionToolState;
import model.ShapeModel;

import java.awt.*;

public interface Selectable {
    SelectionToolState setState(SelectionTool tool, ShapeModel model, Point point);
}
