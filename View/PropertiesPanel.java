package View;

import Controller.VectorGraphicController;
import Object.ShapeObject;
import Observer.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class PropertiesPanel  extends JPanel implements Observer {
    private VectorGraphicController controller;
    private JTextField xField, yField, width, height;
    public PropertiesPanel(VectorGraphicController controller){
        this.controller = controller;

        JPanel propertiesGrid = new JPanel(new GridLayout(2, 0, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        xField = createPropertyField("X", propertiesGrid);
        yField = createPropertyField("Y", propertiesGrid);
        width = createPropertyField("width", propertiesGrid);
        height = createPropertyField("height", propertiesGrid);
        add(propertiesGrid, BorderLayout.CENTER);
        loadObjectProperties();
    }
    private JTextField createPropertyField(String labelText, JPanel panel) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField("0");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(true);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insertUpdate");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changedUpdate");
            }
        });
        textField.setPreferredSize(new Dimension(0, 0));
        fieldPanel.add(label);
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        return textField;
    }

    public void loadObjectProperties() {
        ShapeObject selectedShape = controller.getSelectedShape();
        if (selectedShape != null) {
            xField.setText(String.valueOf(selectedShape.getPosition().x));
            yField.setText(String.valueOf(selectedShape.getPosition().y));
            width.setText(String.valueOf(selectedShape.getWidth()));
            height.setText(String.valueOf(selectedShape.getHeight()));
        }
    }

    @Override
    public void update() {
        loadObjectProperties();
    }
}
