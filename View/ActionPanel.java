package View;

import Singleton.CommandManagerSingleton;
import model.CommandManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// action패널의 경우, 생성시 CommandManager와 연결되며, undo/redo버튼에 따라 존재하는 커맨드들을 취소/재실행하는 역할을 한다.
public class ActionPanel extends JPanel {
    private final CommandManager commandManager;
    public ActionPanel(){
        this.commandManager = CommandManagerSingleton.getInstance();
        setLayout(new FlowLayout());
        JButton undoButton = new JButton("undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.undo();
            }
        });
        JButton redoButton = new JButton("redo");
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.redo();
            }
        });
        add(undoButton);
        add(redoButton);
    }
}
