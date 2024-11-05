package State;

import model.ShapeModel;
import Object.ShapeObject;
import Object.LineObject;


import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectionTool implements Tool{
    private Rectangle selectionRect;
    private Point endPoint;
    private boolean isMoving = false;
    private ShapeObject activeShape; // 단일 선택된 도형
    private boolean isMultiSelection = false; // 다중 선택 상태를 추적
    private boolean isResizing = false;
    private Rectangle[] resizeHandles = new Rectangle[8]; // 8개의 크기 조절 핸들
    private int activeHandleIndex = -1;
    @Override
    public void HandleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {
        endPoint = startPoint;
        activeShape = findShapeAtPosition(model, startPoint); // 클릭 위치에서 도형 찾기

        if (activeShape != null && !isMultiSelection) {
            // 단일 도형 선택의 경우, resize 기능이나, 이동 기능 둘 중 하나를 활성화 후, 이후 동작에 적용.
            model.getSelectedShapes().clear();  // 이전 선택 초기화
            model.selectShape(activeShape); // 단일 도형 선택
            if (checkIfResizeHandle(e.getPoint(), activeShape.getBounds())) {
                isResizing = true;  // 크기 조절 모드 활성화
                isMoving = false;   // 이동 모드 비활성화
            } else {
                isMoving = true;    // 이동 모드 활성화
                isResizing = false; // 크기 조절 모드 비활성화
            }
        } else if (activeShape == null) {
            // 클릭 위치가 도형 외부일 경우 다중 선택을 위한 드래그 시작
            model.getSelectedShapes().clear();  // 이전 선택 초기화
            selectionRect = new Rectangle(startPoint);
            isMoving = false;
            isMultiSelection = false; // 다중 선택 상태 해제
        } else {
            // 이미 다중 선택 상태일 때는 이동 상태로 유지
            isMoving = true;
        }
    }

    @Override
    public void HandleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
        Point currentPoint = e.getPoint();
        int dx = currentPoint.x - endPoint.x;
        int dy = currentPoint.y - endPoint.y;
        if (isResizing && activeShape != null) {
            // 크기 조절 핸들이 선택된 경우 크기 조절 수행
            resizeShape(activeShape, dx, dy);
        } else if (isMoving){
            for(ShapeObject shape : model.getSelectedShapes()){
                shape.move(dx, dy);
            }
        }
        else{
            updateSelectionRect(startPoint, currentPoint);
        }
        endPoint = currentPoint;
        model.notifyObservers();
    }

    @Override
    public void HandleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        if (!isMoving && selectionRect != null) {
            // 선택 영역으로 도형들을 선택
            selectShapesWithinRect(model, selectionRect);
            if(model.getSelectedShapes().size() > 1) {
                isMultiSelection = true;
            }
            else isMultiSelection = false;
        }
        if(isResizing && activeShape != null && !(activeShape instanceof LineObject)){
            activeShape.setPosition(new Point(activeShape.getBounds().x, activeShape.getBounds().y));
            activeShape.setwidth(activeShape.getBounds().width);
            activeShape.setheight(activeShape.getBounds().height);
        }
        // 작업 완료 후 상태 초기화
        selectionRect = null;
        isMoving = false;
        activeShape = null;

        isResizing = false;
        activeHandleIndex = -1;
        model.notifyObservers();  // 최종 업데이트
    }

    @Override
    public void onDeactivate(Component component) {
        selectionRect = null;
        activeShape = null;
        isMultiSelection = false;
    }

    @Override
    public void setCurrentColor(Color color) {

    }

    private void updateSelectionRect(Point startPoint, Point currentPoint) {
        int x = Math.min(startPoint.x, currentPoint.x);
        int y = Math.min(startPoint.y, currentPoint.y);
        int width = Math.abs(currentPoint.x - startPoint.x);
        int height = Math.abs(currentPoint.y - startPoint.y);
        selectionRect.setBounds(x, y, width, height);  // 사각형 크기 설정
    }

    private void selectShapesWithinRect(ShapeModel model, Rectangle rect) {
        for (ShapeObject shape : model.getShapes()) {
            if (rect.intersects(shape.getBounds())) {  // 선택 영역에 걸치는 도형들
                model.selectShape(shape);
            }
        }
    }
    private ShapeObject findShapeAtPosition(ShapeModel model, Point point) {
        for (ShapeObject shape : model.getShapes().reversed()) {
            if (shape.contains(point)) {
                return shape;
            }
        }
        return null;
    }
    public Rectangle getSelectionRect() {
        return selectionRect;
    }
    private void updateResizeHandles(Rectangle bounds) {
        int handleSize = 8;
        resizeHandles[0] = new Rectangle(bounds.x - handleSize / 2, bounds.y - handleSize / 2, handleSize, handleSize);  // Top-left
        resizeHandles[1] = new Rectangle(bounds.x + bounds.width / 2 - handleSize / 2, bounds.y - handleSize / 2, handleSize, handleSize); // Top-center
        resizeHandles[2] = new Rectangle(bounds.x + bounds.width - handleSize / 2, bounds.y - handleSize / 2, handleSize, handleSize); // Top-right
        resizeHandles[3] = new Rectangle(bounds.x - handleSize / 2, bounds.y + bounds.height / 2 - handleSize / 2, handleSize, handleSize); // Middle-left
        resizeHandles[4] = new Rectangle(bounds.x + bounds.width - handleSize / 2, bounds.y + bounds.height / 2 - handleSize / 2, handleSize, handleSize); // Middle-right
        resizeHandles[5] = new Rectangle(bounds.x - handleSize / 2, bounds.y + bounds.height - handleSize / 2, handleSize, handleSize); // Bottom-left
        resizeHandles[6] = new Rectangle(bounds.x + bounds.width / 2 - handleSize / 2, bounds.y + bounds.height - handleSize / 2, handleSize, handleSize); // Bottom-center
        resizeHandles[7] = new Rectangle(bounds.x + bounds.width - handleSize / 2, bounds.y + bounds.height - handleSize / 2, handleSize, handleSize); // Bottom-right
    }
    private boolean checkIfResizeHandle(Point point, Rectangle bounds) {
        updateResizeHandles(bounds);
        for (int i = 0; i < resizeHandles.length; i++) {
            if (resizeHandles[i].contains(point)) {
                activeHandleIndex = i;
                return true;
            }
        }
        return false;
    }
    private void resizeShape(ShapeObject shape, int dx, int dy) {
        switch (activeHandleIndex) {
            case 0: // Top-left
                shape.resize(dx, dy, -dx, -dy);
                break;
            case 1: // Top-center
                shape.resize(0, dy, 0, -dy);
                break;
            case 2: // Top-right
                shape.resize(0, dy, dx, -dy);
                break;
            case 3: // Middle-left
                shape.resize(dx, 0, -dx, 0);
                break;
            case 4: // Middle-right
                shape.resize(0, 0, dx, 0);
                break;
            case 5: // Bottom-left
                shape.resize(dx, 0, -dx, dy);
                break;
            case 6: // Bottom-center
                shape.resize(0, 0, 0, dy);
                break;
            case 7: // Bottom-right
                shape.resize(0, 0, dx, dy);
                break;
        }

    }
}
