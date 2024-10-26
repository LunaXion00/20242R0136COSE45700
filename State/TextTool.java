package State;

import Factory.ShapeFactory;
import Factory.TextBoxFactory;
import model.ShapeModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import Object.ShapeObject;
import Object.TextBoxObject;

public class TextTool implements Tool, KeyListener {
    private TextBoxObject currentTextBox;
    private Color textColor;
    private ShapeFactory factory;
    private ShapeModel model;
    private boolean isEditing = false;
    public TextTool(ShapeFactory factory, Color textColor){
        this.factory = factory;
        this.textColor = textColor;
    }
    @Override
    public void HandleMousePress(MouseEvent e, ShapeModel model, Point startPoint) {
        this.model = model;
        if (isEditing && currentTextBox != null) {
            finishEditing(e.getComponent());
        }
    }
    @Override
    public void HandleMouseDrag(MouseEvent e, ShapeModel model, Point startPoint) {
//        텍스트박스는 드래그가 없음
    }

    @Override
    public void HandleMouseRelease(MouseEvent e, ShapeModel model, Point startPoint) {
        if (!isEditing) {
            currentTextBox = (TextBoxObject) factory.createShape(startPoint, textColor, null);
            model.addShape(currentTextBox);
            isEditing = true;

            if (!e.getComponent().isFocusOwner()) {
                e.getComponent().requestFocusInWindow();
            }
            e.getComponent().addKeyListener(this); // KeyListener 등록
            currentTextBox.startEditing();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (isEditing && currentTextBox != null) {
            currentTextBox.setText(currentTextBox.getText() + e.getKeyChar());
            model.notifyObservers();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void finishEditing(Component component) {
        if (currentTextBox != null) {
            if (currentTextBox.getText().isEmpty()) {
                component.removeKeyListener(this);
                model.deleteShape(currentTextBox); // 모델에서 텍스트 박스 삭제
            }
            else {
                currentTextBox.resizeToFitText();
            }
            isEditing = false;
            currentTextBox.stopEditing();
            currentTextBox = null;

            component.removeKeyListener(this); // KeyListener 제거
            model.notifyObservers();
        }
    }

    @Override
    public void onDeactivate(Component component) {
            finishEditing(component);
    }

    @Override
    public void setCurrentColor(Color color) {
        this.textColor = color;
    }
}
