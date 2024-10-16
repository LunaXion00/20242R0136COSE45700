package test.src.view;

import test.src.observer.Observer;

import javax.swing.*;

public class DrawPanel extends JPanel implements Observer {
    @Override
    public void update() {
        repaint();
    }
}
