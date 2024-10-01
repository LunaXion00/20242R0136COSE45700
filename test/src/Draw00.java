import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class Draw00 extends JFrame{
    Graphics g;
    int x, y, ox, oy;
    public Draw00(String title){
        super(title);
        setSize(200, 200);
        setVisible(true);
        g = this.getGraphics();
        g.setColor(Color.red);

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                x = e.getX();
                y = e.getY();
                g.drawLine(ox, oy, x, y);

                ox = x;
                oy = y;
            }
        });
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                ox = e.getX();
                oy = e.getY();
            }
        });
    }
    
        public static void main(String[] args) {
            Frame f = new Draw00("도형 그리기");
       }   
    }
