package View;

import Command.*;
import Controller.VectorGraphicController;
import Object.*;
import Observer.Observer;
import Singleton.CommandManagerSingleton;
import Singleton.SelectionManagerSingleton;
import model.SelectionManager;
import model.ShapeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


// 속성창은 선택된 객체의 속성을 표시 및 수정을 하는 패널을 제공.
public class PropertiesPanel extends JPanel implements Observer {
    private final VectorGraphicController controller;
    private final ShapeModel model;
    private final SelectionManager selectionManager;
    private JTextField xField, yField, width, height;

    public PropertiesPanel(ShapeModel model, VectorGraphicController controller) {
        this.controller = controller;
        this.model = model;
        this.selectionManager = SelectionManagerSingleton.getInstance();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Dimension fieldSize = new Dimension(60, 30);
        gbc.insets = new Insets(5, 5, 5, 5);

        addLabelAndField("X", gbc, 0, 0, fieldSize);
        addLabelAndField("Y", gbc, 1, 0, fieldSize);
        addLabelAndField("Width", gbc, 0, 1, fieldSize);
        addLabelAndField("Height", gbc, 1, 1, fieldSize);

        loadObjectProperties();
    }

    private void addLabelAndField(String labelText, GridBagConstraints gbc, int gridx, int gridy, Dimension fieldSize) {
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField("0");
        textField.setPreferredSize(fieldSize);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        textField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField.setEditable(true);
            }
        });

        // 엔터키 입력 시 Command 실행
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setEditable(false); // 편집 종료
                    modifyInfo(labelText, textField);
                }
            }
        });

        // 포커스 잃을 시 편집 종료
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField.setEditable(false);
            }
        });

        gbc.gridx = gridx * 2;
        gbc.gridy = gridy;
        add(label, gbc);

        gbc.gridx = gridx * 2 + 1;
        add(textField, gbc);

        switch (labelText) {
            case "X" -> xField = textField;
            case "Y" -> yField = textField;
            case "Width" -> width = textField;
            case "Height" -> height = textField;
        }
    }

    private void modifyInfo(String property, JTextField textField) {
        ShapeObject selectedShape = selectionManager.getSingleSelectedObject();
        if (selectedShape == null) return;
        try {
            int value = Integer.parseInt(textField.getText());
            int oldValue = switch (property) {
                case "X" -> selectedShape.getPosition().x;
                case "Y" -> selectedShape.getPosition().y;
                case "Width" -> selectedShape.getWidth();
                case "Height" -> selectedShape.getHeight();
                default -> throw new IllegalStateException("Unexpected value: " + property);
            };
            Command command = new PropertiesCommand(selectedShape, property, oldValue, value);
            CommandManagerSingleton.getInstance().executeCommand(command);
            model.notifyObservers();
        } catch (NumberFormatException e) {
            textField.setText("0");
        }
    }

    public void loadObjectProperties() {
        CompositeObject selectedShape = selectionManager.getSelectedObject();
        if (selectedShape != null) {
            xField.setText(String.valueOf(selectedShape.getPosition().x));
            yField.setText(String.valueOf(selectedShape.getPosition().y));
            width.setText(String.valueOf(selectedShape.getWidth()));
            height.setText(String.valueOf(selectedShape.getHeight()));
        } else {
            xField.setText("");
            yField.setText("");
            width.setText("");
            height.setText("");
        }
    }

    @Override
    public void update() {
        loadObjectProperties();
    }
}