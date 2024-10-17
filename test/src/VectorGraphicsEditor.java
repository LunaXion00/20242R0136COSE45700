package test.src;

import test.src.model.ShapeModel;
import test.src.view.DrawPanel;

import javax.swing.*;

public class VectorGraphicsEditor extends JFrame {
    private ShapeModel model;
    private DrawPanel drawPanel;
    public VectorGraphicsEditor(){
        setTitle("Vector Graphics Editor");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {

    }
}