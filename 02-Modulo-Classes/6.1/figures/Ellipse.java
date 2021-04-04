package figures;

import java.awt.*;
import java.awt.geom.*;

public class Ellipse extends Figure {
    int x, y;
    int w, h;
    int rFill, gFill, bFill;
    int rDraw, gDraw, bDraw;

    public Ellipse (int x, int y, int w, int h, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw) {
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

    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillOval(this.x,this.y, this.w,this.h);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawOval(this.x,this.y, this.w,this.h);
    }
}