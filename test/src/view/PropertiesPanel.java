package test.src.view;

import test.src.model.ShapeModel;
import test.src.model.ShapeObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PropertiesPanel extends JPanel {
    private ShapeModel model;

    public PropertiesPanel(ShapeModel model) {
        this.model = model;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        updateProperties();
    }

    public void updateProperties() {
        removeAll();
        List<ShapeObject> selectedShapes = model.getSelectedShapes();

        if (selectedShapes.isEmpty()) {
            add(new JLabel("No object selected"));
        } else {
            for (ShapeObject shape : selectedShapes) {
                add(new JLabel("Position: " + shape.getPosition()));
                add(new JLabel("Width: " + shape.getWidth()));
                add(new JLabel("Height: " + shape.getHeight()));
                add(new JLabel("Fill Color: " + shape.getFillColor()));
                add(new JLabel("Stroke Color: " + shape.getStrokeColor()));
            }
        }
        revalidate();
        repaint();
    }
}