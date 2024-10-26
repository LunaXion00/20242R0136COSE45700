package View;

import Controller.VectorGraphicController;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {
    private Color[] colors = {null, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.WHITE, Color.BLACK};
    private VectorGraphicController controller;
    public ColorPanel(VectorGraphicController controller){
        this.controller = controller;
        setLayout(new GridLayout(1, colors.length, 5, 5));

        for (Color color : colors) {
            JButton button = new JButton();
            button.setBackground(color);
            button.setPreferredSize(new Dimension(30, 30));
            button.addActionListener(e -> controller.setCurrentColor(color));
            add(button);
        }
    }
}
