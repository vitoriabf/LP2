import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

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
        
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);
                        figs.add(new Rect(x,y, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        repaint();  // outer.repaint()
                    }

                    else if (evt.getKeyChar() == 'e') {
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                        figs.add(new Ellipse(x,y, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        repaint();
                    }

                    else if (evt.getKeyChar() == 'a') {
                    	int centerX = rand.nextInt(350);
                        int centerY = rand.nextInt(350);
                        int radiusX = rand.nextInt(50);
                        int radiusY = rand.nextInt(50);
                        int length = rand.nextInt(50);
                        int startAngle = rand.nextInt(360);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                    	figs.add(new Arc(centerX,centerY, radiusX,radiusY, length,startAngle, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        repaint();
                    }
                }
            }
        );
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}