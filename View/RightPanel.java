package View;

import Controller.VectorGraphicController;
import model.ShapeModel;
import Observer.Observer;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    public RightPanel(ShapeModel model, VectorGraphicController controller) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 패널 간 여백 설정

        // PropertiesPanel 추가
        PropertiesPanel propertiesPanel = new PropertiesPanel(model, controller);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(propertiesPanel, gbc);

        // OrderPanel 추가
        OrderPanel orderPanel = new OrderPanel(controller);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(orderPanel, gbc);

        // Observer 등록
        model.addObserver(propertiesPanel);
    }
}