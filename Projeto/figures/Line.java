package figures;


import java.awt.geom.Line2D;
import java.awt.*;

public class Line extends Figure {

    public Line (int x, int y, int w, int h,int rDraw, int gDraw, int bDraw) {
        super(x,y,w,h,rDraw,gDraw,bDraw);
    }

    public void print () {
        System.out.format("Line de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    public String typeOf() {
        return "Line";
    }
    public boolean clicked (int x, int y){
        return ((x >= this.x && this.w >= x && y >= this.y && this.h >= y) || (x <= this.x && this.w <= x && y <= this.y && this.h <= y));
    }

    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
        this.w += dx;
        this.h += dy;
    }

    public void paint (Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g;

        if (focused) {
            
            g2d.setPaint(Color.RED);
            g2d.drawLine(this.x-2,this.y-2, this.w+4,this.h+4);
        }

        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawLine(this.x,this.y, this.w,this.h);
        

    }
}