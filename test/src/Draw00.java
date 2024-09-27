import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Draw00 extends JFrame{
    public Draw00() {
        super("Draw Ver1.0");
        setSize(800,300);      
        setVisible(true);      
        MyDraw draw = new MyDraw();      
        add(draw);      
       }
      
       class MyDraw extends Canvas{
        @Override      
        public void paint(Graphics g) {      
            super.paint(g);      
            g.drawString("안녕하세요.", 10, 20);      
            g.setColor(Color.RED);      
            g.drawLine(10, 30, 100, 200);      
            g.setColor(Color.GREEN);      
            g.fillRect(300, 50, 200, 200);
            g.fillRect(300, 50, 200, 200);
            g.fillRect(300, 50, 200, 200);

        }
    }
        public static void main(String[] args) {
        new Draw00();     
       }   
    }
