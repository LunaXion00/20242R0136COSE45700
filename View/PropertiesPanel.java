package View;

import Controller.VectorGraphicController;
import Object.ShapeObject;
import Observer.Observer;
import model.ShapeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PropertiesPanel  extends JPanel implements Observer {
    private VectorGraphicController controller;
    private ShapeModel model;
    private JTextField xField, yField, width, height;
    public PropertiesPanel(ShapeModel model, VectorGraphicController controller){
        this.controller = controller;
        this.model = model;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension fieldSize = new Dimension(60, 30);
        gbc.insets = new Insets(5, 5, 5, 5); // 여백 설정
        addLabelAndField("X", gbc, 0, 0, fieldSize);
        addLabelAndField("Y", gbc, 1, 0, fieldSize);
        addLabelAndField("Width", gbc, 0, 1, fieldSize);
        addLabelAndField("Height", gbc, 1, 1, fieldSize);

        // Z-Order 버튼 패널 생성
        JPanel zOrderPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        // 버튼 추가
        JButton toFrontButton = new JButton("맨 위로");
        toFrontButton.addActionListener(e -> controller.bringToFront());

        JButton forwardButton = new JButton("한 칸 위로");
        forwardButton.addActionListener(e -> controller.bringForward());

        JButton backwardButton = new JButton("한 칸 아래로");
        backwardButton.addActionListener(e -> controller.sendBackward());

        JButton toBackButton = new JButton("맨 밑으로");
        toBackButton.addActionListener(e -> controller.sendToBack());

        // 패널에 버튼 추가
        zOrderPanel.add(toFrontButton);
        zOrderPanel.add(forwardButton);
        zOrderPanel.add(backwardButton);
        zOrderPanel.add(toBackButton);

        // Z-Order 버튼 패널을 GridBagLayout 아래쪽에 추가
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4; // 두 열에 걸쳐 배치
        add(zOrderPanel, gbc);

        loadObjectProperties();
    }
    private void addLabelAndField(String labelText, GridBagConstraints gbc, int gridx, int gridy, Dimension fieldSize) {
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField("0"); // 기본값 0
        textField.setPreferredSize(fieldSize);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false); // 시작 시 비활성화

        // 클릭 시 편집 가능하도록 설정
        textField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textField.setEditable(true);
            }
        });

        // Enter 키로 편집 종료
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setEditable(false); // 편집 종료
                    updateInfo(labelText, textField);
                }
            }
        });

        // 포커스가 사라질 때 편집 종료
        textField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent evt) {
                textField.setEditable(false); // 포커스를 잃으면 비활성화
            }
        });

        // 레이블 추가
        gbc.gridx = gridx * 2; // 열 위치 설정
        gbc.gridy = gridy; // 행 위치 설정
        add(label, gbc);

        // 텍스트 필드 추가
        gbc.gridx = gridx * 2 + 1; // 열 위치 설정
        add(textField, gbc);

        // 필드 참조 저장
        switch (labelText) {
            case "X" -> xField = textField;
            case "Y" -> yField = textField;
            case "Width" -> width = textField;
            case "Height" -> height = textField;
        }
    }

    public void loadObjectProperties() {
        ShapeObject selectedShape = controller.getSelectedShape();
        if (selectedShape != null) {
            xField.setText(String.valueOf(selectedShape.getPosition().x));
            yField.setText(String.valueOf(selectedShape.getPosition().y));
            width.setText(String.valueOf(selectedShape.getWidth()));
            height.setText(String.valueOf(selectedShape.getHeight()));
        }
        else{
            xField.setText("");
            yField.setText("");
            width.setText("");
            height.setText("");
        }
    }

    private void updateInfo(String property, JTextField textField){
        ShapeObject selectedShape = controller.getSelectedShape();
        if (controller.getSelectedShape() == null) return;

        try {
            int value = Integer.parseInt(textField.getText());
            switch (property) {
                case "X" -> controller.getSelectedShape().setPosition(new Point(value, selectedShape.getPosition().y));
                case "Y" -> controller.getSelectedShape().setPosition(new Point(selectedShape.getPosition().x, value));
                case "Width" -> controller.getSelectedShape().setwidth(Math.max(1,value));
                case "Height" -> controller.getSelectedShape().setheight(Math.max(1, value));
            }
            System.out.println("Property updated: " + property + " | Value: " + value);
            model.notifyObservers(); // 변경 사항을 반영하고 UI 업데이트
        } catch (NumberFormatException e) {
            // 숫자가 아닐 경우 예외 처리 (필드를 초기값으로 되돌릴 수도 있음)
            textField.setText("0");
        }
    }
    @Override
    public void update() {
        loadObjectProperties();
    }
}
