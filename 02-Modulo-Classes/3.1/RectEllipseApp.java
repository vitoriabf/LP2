import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

class RectEllipseApp {
    public static void main (String[] args) {
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}

class RectEllipseFrame extends JFrame {
    Ellipse e1;
    Ellipse e2;
    Ellipse e3;
    Arc a1;
    Arc a2;
    Arc a3;


    RectEllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Rect + Ellipse");
        this.setSize(350, 350);
        this.e1 = new Ellipse(50,50, 100,30, 117,219,250, 11,59,141);
        this.e2 = new Ellipse(200,100, 100,30, 255,245,84, 84,255,130);
        this.e3 = new Ellipse(150,200, 100,30, 255,187,84, 210,20,71);
        this.a1 = new Arc(125,200, 100,30, 50,30, 117,219,250, 11,59,141);
        this.a2 = new Arc(100,100, 100,30, 80,20, 255,245,84, 84,255,130);
        this.a3 = new Arc(110,150, 100,30, 40,20, 255,187,84, 210,20,71);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
        this.a1.paint(g);
        this.a2.paint(g);
        this.a3.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    int rFill, gFill, bFill;
    int rDraw, gDraw, bDraw;

    Rect (int x, int y, int w, int h, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rFill = rFill;
        this.gFill = gFill;
        this.bFill = bFill;
        this.rDraw = rDraw;
        this.gDraw = gDraw;
        this.bDraw = bDraw;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillRect(this.x,this.y, this.w,this.h);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    int rFill, gFill, bFill;
    int rDraw, gDraw, bDraw;

    Ellipse (int x, int y, int w, int h, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rFill = rFill;
        this.gFill = gFill;
        this.bFill = bFill;
        this.rDraw = rDraw;
        this.gDraw = gDraw;
        this.bDraw = bDraw;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillOval(this.x,this.y, this.w,this.h);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawOval(this.x,this.y, this.w,this.h);
    }
}


class Arc {
    int centerX, centerY;
    int length, startAngle;
    int radiusX, radiusY;
    int rFill, gFill, bFill;
    int rDraw, gDraw, bDraw;

    Arc (int centerX, int centerY, int length, int startAngle, int radiusX, int radiusY, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.length = length;
        this.startAngle = startAngle;
        this.rFill = rFill;
        this.gFill = gFill;
        this.bFill = bFill;
        this.rDraw = rDraw;
        this.gDraw = gDraw;
        this.bDraw = bDraw;
    }

    void print () {
        System.out.format("Arc de center (%d,%d), length (%d) e radius(%d,%d).\n",
            this.centerX, this.centerY, this.length,this.radiusX , this.radiusY);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillArc(this.centerX, this.centerY, this.radiusX ,this.radiusY, this.startAngle, this.length);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawArc(this.centerX, this.centerY, this.radiusX ,this.radiusY, this.startAngle, this.length);
    }
}