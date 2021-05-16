package figures;

import java.awt.*;
import java.awt.geom.*;

public class Ellipse extends Figure {

    public Ellipse (int x, int y, int w, int h, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw) {
        super(x,y,w,h,rFill,gFill,bFill,rDraw,gDraw,bDraw);
    }

    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    public String typeOf() {
        return "Ellipse";
    }


    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillOval(this.x,this.y, this.w,this.h);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawOval(this.x,this.y, this.w,this.h);
    }
}