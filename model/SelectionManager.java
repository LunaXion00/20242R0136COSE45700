package model;
import Object.*;
import Singleton.SelectionManagerSingleton;
import Singleton.ShapeModelSingleton;
import Observer.Observer;

import java.awt.*;

public class SelectionManager implements Observer{
    private CompositeObject selectedGroup = new CompositeObject(new Point(0, 0), 0, 0, null, null);
    private Rectangle selectionBox;
    public SelectionManager(){
        ShapeModelSingleton.getInstance().addObserver(this);
    }
    public CompositeObject getSelectedObject(){
        return selectedGroup;
    }

    public ShapeObject getSingleSelectedObject(){
        if(selectedGroup.getComponents().size() == 1) return selectedGroup.getComponents().get(0);
        return null;
    }

    public ShapeObject getShapesAt(Point point){
        // 기존 그룹에 포함된 객체 클릭 여부 확인
        if (selectedGroup.contains(point)) {
            return selectedGroup;
        }
        // 새로운 객체 선택
        selectedGroup.clear();
        ShapeObject clickedShape = findShapeAtPosition(point);
        if (clickedShape != null) {
            selectedGroup.add(clickedShape);
            return selectedGroup;
        }

        return null; // 빈 공간 클릭
    }
    public void updateSelectedObject(ShapeModel model, Rectangle selectionBox){
        for (ShapeObject shape : model.getShapes()) {
            if (selectionBox.intersects(shape.getBounds())) {  // 선택 영역에 걸치는 도형들
                selectedGroup.add(shape);
            }
        }
        this.selectionBox = null;
    }
    private ShapeObject findShapeAtPosition(Point point) {
        ShapeModel model = ShapeModelSingleton.getInstance();
        for (ShapeObject shape : model.getShapes().reversed()) {
            if (shape.contains(point)) {
                return shape;
            }
        }
        return null;
    }
    public Rectangle getSelectionBox() {
        return selectionBox;
    }

    public void setSelectionBox(Rectangle selectionBox) {
        this.selectionBox = selectionBox;
    }

    public void clearManager(){
        selectedGroup.clear();
    }

    @Override
    public void update() {
        selectedGroup.updateBounds();
    }
}
