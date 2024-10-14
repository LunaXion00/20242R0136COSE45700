package test.src;

import javax.swing.*;

import org.w3c.dom.css.Rect;
import test.src.model.*;
import test.src.singleton.*;
import test.src.controller.*;
import test.src.factory.*;
import test.src.state.*;
import test.src.command.*;
import test.src.view.*;

import java.awt.*;

public class VectorGraphicsEditor extends JFrame {
    private ShapeModel model;
    private DrawPanel drawPanel;
    private PropertiesPanel propertiesPanel;
    private VectorGraphicsController controller;

    public VectorGraphicsEditor() {
        setTitle("Vector Graphics Editor");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Singleton을 통해 ShapeModel 생성
        model = ShapeModelSingleton.getInstance();

        // 뷰와 컨트롤러 설정
        controller = new VectorGraphicsController(model);
        drawPanel = new DrawPanel(model, controller);
        propertiesPanel = new PropertiesPanel(model);


        // UI 구성 - 도구 버튼들
        JPanel toolsPanel = new JPanel();
        JButton rectButton = new JButton("Rectangle");
        JButton pencilButton = new JButton("Pencil");
        toolsPanel.add(rectButton);
        toolsPanel.add(pencilButton);

        // 각 버튼에 해당하는 도구의 상태 설정
        rectButton.addActionListener(e -> controller.setToolState(new RectangleState()));
        pencilButton.addActionListener(e -> controller.setToolState(new PencilState()));

        controller.setToolState(new RectangleState());

        // 프레임에 패널 배치
        add(toolsPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        add(propertiesPanel, BorderLayout.EAST);

        // MouseListener 설정
        drawPanel.addMouseListener(controller);
        drawPanel.addMouseMotionListener(controller);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VectorGraphicsEditor().setVisible(true);
        });
    }
}