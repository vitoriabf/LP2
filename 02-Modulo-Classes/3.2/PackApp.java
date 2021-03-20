import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1;
    Ellipse e1;
    Ellipse e2;
    Ellipse e3;
    Arc a1;
    Arc a2;
    Arc a3;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.r1 = new Rect(58,10, 100,30, 117,219,250, 11,59,141);
        this.e1 = new Ellipse(50,50, 100,30, 117,219,250, 11,59,141);
        this.e2 = new Ellipse(200,100, 100,30, 255,245,84, 84,255,130);
        this.e3 = new Ellipse(150,200, 100,30, 255,187,84, 210,20,71);
        this.a1 = new Arc(125,200, 100,30, 50,30, 117,219,250, 11,59,141);
        this.a2 = new Arc(100,100, 100,30, 80,20, 255,245,84, 84,255,130);
        this.a3 = new Arc(110,150, 100,30, 40,20, 255,187,84, 210,20,71);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
        this.a1.paint(g);
        this.a2.paint(g);
        this.a3.paint(g);
    }
}