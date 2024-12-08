package View;

import Command.*;
import Controller.VectorGraphicController;
import Object.ShapeObject;
import Singleton.CommandManagerSingleton;
import Singleton.SelectionManagerSingleton;
import model.CommandManager;
import model.SelectionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class OrderPanel extends JPanel {
    private VectorGraphicController controller;
    private final SelectionManager selectionManager = SelectionManagerSingleton.getInstance();
    private final CommandManager commandManager = CommandManagerSingleton.getInstance();
    public OrderPanel(VectorGraphicController controller) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addButton("맨 위로", e -> executeZOrderCommand(ZOrderCommand.Destination.TOP), gbc, 0);
        addButton("한 칸 위로", e-> executeZOrderCommand(ZOrderCommand.Destination.UP), gbc, 1);
        addButton("한 칸 아래로",e -> executeZOrderCommand(ZOrderCommand.Destination.DOWN), gbc, 2);
        addButton("맨 밑으로", e -> executeZOrderCommand(ZOrderCommand.Destination.BOTTOM), gbc, 3);
    }

    private void addButton(String title, ActionListener action, GridBagConstraints gbc, int y) {
        JButton button = new JButton(title);
        button.addActionListener(action);
        gbc.gridx = 0;
        gbc.gridy = y;
        add(button, gbc);
    }

    private void executeZOrderCommand(ZOrderCommand.Destination destination) {
        ShapeObject selectedShape = selectionManager.getSingleSelectedObject();
        if (selectedShape != null) {
            Command command = new ZOrderCommand(selectedShape, destination);
            CommandManagerSingleton.getInstance().executeCommand(command);
        }
    }

}
