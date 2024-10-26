// View/ButtonPanel.java
package View;

import Controller.VectorGraphicController;
import Factory.*;
import State.*;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel(VectorGraphicController controller) {
        setLayout(new GridLayout(0, 1));

        JButton selectButton = new JButton("선택");
        JButton textButton = new JButton("텍스트");
        JButton rectButton = new JButton("사각형");
        JButton lineButton = new JButton("직선");
        JButton pencilButton = new JButton("연필");
        JButton ellipseButton = new JButton("원");

        add(selectButton);
        add(textButton);
        add(rectButton);
        add(lineButton);
        add(pencilButton);
        add(ellipseButton);

        // 각 버튼에 해당하는 도구의 상태 설정
        selectButton.addActionListener(e -> controller.setCurrentTool(new SelectionTool()));
        textButton.addActionListener(e -> controller.setCurrentTool(new TextTool(new TextBoxFactory(), Color.BLACK)));
        rectButton.addActionListener(e -> controller.setCurrentTool(new CreationTool(new RectangleFactory(), null, Color.BLACK)));
        lineButton.addActionListener(e -> controller.setCurrentTool(new LineTool(new LineFactory(), null, Color.BLACK)));
        pencilButton.addActionListener(e -> controller.setCurrentTool(new PencilTool(new PencilFactory(), null, Color.BLACK)));
        ellipseButton.addActionListener(e -> controller.setCurrentTool(new CreationTool(new EllipseFactory(), null, Color.BLACK)));
    }
}