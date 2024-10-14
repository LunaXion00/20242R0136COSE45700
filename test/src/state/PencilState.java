package test.src.state;
import test.src.model.ShapeModel;
import test.src.model.PencilObject;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class PencilState implements ToolState {
    @Override
    public void mousePressed(MouseEvent e, ShapeModel model, Point startPoint) {
        model.addShape(new PencilObject(startPoint, java.awt.Color.BLACK));
    }

    @Override
    public void mouseDragged(MouseEvent e, ShapeModel model, Point startPoint) {
        PencilObject pencil = (PencilObject) model.getShapes().get(model.getShapes().size() - 1);
        pencil.addPoint(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e, ShapeModel model, Point startPoint) {
        // 마우스 릴리즈 시 별도의 작업 필요 없음
    }
}