import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.Random;

public class Hello2DApp {
    public static void main(String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.black);
    }
}

class Hello2DFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Hello2DFrame() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        Random rnd = new Random();

        for (int line = 0; line < 100; line++) {
            g2d.setPaint(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

            BasicStroke bs = new BasicStroke(10.0f);
            int startX = rnd.nextInt(1025);
            int startY = rnd.nextInt(1025);
            int endX = rnd.nextInt(1025);
            int endY = rnd.nextInt(1025);
            g2d.drawLine(startX, startY, endX, endY);
        }

        int w = getWidth();
        int h = getHeight();
        g2d.drawLine(0, 0, w, h);
        g2d.drawLine(0, h, w, 0);

        g2d.draw(new Ellipse2D.Double(200, 100, 200, 250));

    }

}