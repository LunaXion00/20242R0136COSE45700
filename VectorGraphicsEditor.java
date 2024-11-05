import Controller.VectorGraphicController;
import Factory.*;
import Singleton.ShapeModelSingleton;
import State.*;
import View.ButtonPanel;
import View.ColorPanel;
import View.DrawPanel;
import View.PropertiesPanel;
import model.ShapeModel;

import javax.swing.*;
import java.awt.*;

public class VectorGraphicsEditor extends JFrame {
    private ShapeModel model;
    private DrawPanel drawPanel;
    private VectorGraphicController controller;
    private ColorPanel colorPanel;
    private PropertiesPanel propertiesPanel;

    public VectorGraphicsEditor() {
        setTitle("Vector Graphics Editor");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Singleton을 통해 ShapeModel 생성
        model = ShapeModelSingleton.getInstance();

        // 뷰와 컨트롤러 설정
        controller = new VectorGraphicController(model);
        drawPanel = new DrawPanel(model, controller);
        colorPanel = new ColorPanel(controller);
        propertiesPanel = new PropertiesPanel(model, controller);
        model.addObserver(propertiesPanel);
        // UI 구성 - 도구 버튼들
        ButtonPanel toolsPanel = new ButtonPanel(controller);

        controller.setCurrentTool(new SelectionTool());

        // 프레임에 패널 배치
        add(toolsPanel, BorderLayout.WEST);
        add(drawPanel, BorderLayout.CENTER);
        add(colorPanel, BorderLayout.SOUTH);
        add(propertiesPanel, BorderLayout.EAST);
        // MouseListener 설정
        drawPanel.addMouseListener(controller);
        drawPanel.addMouseMotionListener(controller);
        colorPanel.addMouseListener(controller);
        propertiesPanel.addMouseListener(controller);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VectorGraphicsEditor().setVisible(true);
        });
    }
}