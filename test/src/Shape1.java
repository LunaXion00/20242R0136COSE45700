import java.awt.*;
import java.awt.geom.*;
public class Shape1 extends Frame{
    public Shape1(String title){
        super(title);
    }
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        Line2D line1=new Line2D.Double(50.0, 50.0, 250.0, 50.0);
        Rectangle2D rect1=new Rectangle2D.Double(50.0, 100.0, 100.0, 100.0);
        Ellipse2D ellipse1=new Ellipse2D.Double(200.0, 100.0, 100.0, 100.0);
        g2.draw(line1);
        g2.draw(rect1);
        g2.fill(ellipse1);
    }
    public static void main(String[] args){
        Frame f=new Shape1("평면 도형");
        f.setSize(350,250);
        f.setVisible(true);
    }
}