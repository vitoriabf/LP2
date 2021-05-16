package figures;


import java.awt.geom.Line2D;
import java.awt.*;

public class Line extends Figure {

    public Line (int x, int y, int w, int h, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw) {
        super(x,y,w,h,rFill,gFill,bFill,rDraw,gDraw,bDraw);
    }

    public void print () {
        System.out.format("Line de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    public String typeOf() {
        return "Line";
    }


    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Line2D line = new Line2D.Float(this.x,this.y, this.w,this.h);
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fill(line);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.draw(line);
    }
}