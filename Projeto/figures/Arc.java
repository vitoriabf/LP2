package figures;

import java.awt.*;

public class Arc extends Figure {
    int startAngle, endAngle;
    
    public Arc (int x, int y, int w, int h, int startAngle, int endAngle, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw){
        super(x,y,w,h,rFill,gFill,bFill,rDraw,gDraw,bDraw);

        this.startAngle = startAngle;
        this.endAngle = endAngle;

    }

    public void print () {
        System.out.format("Arc na posição (%d,%d), tamanho (%d) e radius(%d,%d).\n",
            this.x, this.y, this.w ,this.h, this.startAngle, this.endAngle);
    }
    public String typeOf() {
        return "Arc";
    }


    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillArc(this.x, this.y, this.w ,this.h, this.startAngle, this.endAngle);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawArc(this.x, this.y, this.w ,this.h, this.startAngle, this.endAngle);
    }
}