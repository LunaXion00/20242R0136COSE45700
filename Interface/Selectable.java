package Interface;

import State.SelectionTool;
import State.SelectionToolState;
import model.ShapeModel;

import java.awt.*;

public interface Selectable {
    // 지금 이게 구현은 해놓고 정작 state 전환에 사용되진 않고 있음.
    SelectionToolState setState(SelectionTool tool, ShapeModel model, Point point);
}
