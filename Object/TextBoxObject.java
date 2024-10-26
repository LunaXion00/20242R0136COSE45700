package Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBoxObject extends ShapeObject {
    private String text;
    private Color textColor;
    private Font font;
    private FontMetrics fontMetrics;
    private int cursorPosition; // 현재 커서 위치
    private boolean cursorVisible = true;
    private boolean isEditing = false;

    private Timer cursorTimer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cursorVisible = !cursorVisible;
        }
    });

    public TextBoxObject(Point position, int width, int height, Color textColor) {
        super(position, width, height, null, null);
        this.text = "";
        this.textColor = textColor;
        this.font = new Font("Arial", Font.PLAIN, 12);
        fontMetrics = new JPanel().getFontMetrics(font);
        this.cursorPosition = 0;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
        cursorPosition = text.length();
        resizeToFitText();
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(textColor);  // 텍스트 색상
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = position.x + 5; // 왼쪽에서 약간의 여백
        int y = position.y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(text, x, y);
        if (cursorVisible && isEditing) { // 입력 중일 때만 커서 표시
            int cursorX = x + metrics.stringWidth(text.substring(0, cursorPosition));
            g2d.drawLine(cursorX, y - metrics.getAscent(), cursorX, y + metrics.getDescent());
        }
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height);
    }
    @Override
    public boolean contains(Point point) {
        return getBounds().contains(point);
    }
    public void resizeToFitText() {
        if (text != null && !text.isEmpty()) {
            int textWidth = fontMetrics.stringWidth(text);
            int textHeight = fontMetrics.getHeight();

            int padding = 10; // 텍스트 주위의 패딩 값 설정
            setwidth(textWidth + padding);
            setheight(textHeight + padding);
        }
    }
    public void startEditing() {
        isEditing = true;
        cursorTimer.start();
        cursorVisible = true;
    }

    // 텍스트 박스 편집 종료 시 커서 애니메이션 중지
    public void stopEditing() {
        isEditing = false;
        cursorTimer.stop();
        cursorVisible = false;
        cursorPosition = 0;
    }

    public boolean isEditing() {
        return isEditing;
    }
}
