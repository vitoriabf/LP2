package figures;

import java.awt.*;

public class Arc extends Figure {
    int startAngle, endAngle;
    
    public Arc (int x, int y, int w, int h, int startAngle, int endAngle, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw){
        super(x,y,w,h,rDraw,gDraw,bDraw);

        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.rFill = rFill;
        this.gFill = gFill;
        this.bFill = bFill;

    }

    public void print () {
        System.out.format("Arc na posição (%d,%d), tamanho (%d) e radius(%d,%d).\n",
            this.x, this.y, this.w ,this.h, this.startAngle, this.endAngle);
    }
    public String typeOf() {
        return "Arc";
    }


    public void paint (Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g;

        if (focused) {
            g2d.setPaint(Color.RED);
            g2d.drawArc(this.x-2,this.y-2, this.w+4,this.h+4, this.startAngle, this.endAngle);
        }
        
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillArc(this.x, this.y, this.w ,this.h, this.startAngle, this.endAngle);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawArc(this.x, this.y, this.w ,this.h, this.startAngle, this.endAngle);
    }
}