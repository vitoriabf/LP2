package figures;

import java.awt.*;

public class Arc {
    int centerX, centerY;
    int length, startAngle;
    int radiusX, radiusY;
    int rFill, gFill, bFill;
    int rDraw, gDraw, bDraw;

    public Arc (int centerX, int centerY, int length, int startAngle, int radiusX, int radiusY, int rFill, int gFill, int bFill, int rDraw, int gDraw, int bDraw){
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

    public void print () {
        System.out.format("Arc de center (%d,%d), length (%d) e radius(%d,%d).\n",
            this.centerX, this.centerY, this.length,this.radiusX , this.radiusY);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rFill,this.gFill,this.bFill));
        g2d.fillArc(this.centerX, this.centerY, this.radiusX ,this.radiusY, this.startAngle, this.length);
        g2d.setColor(new Color(this.rDraw,this.gDraw,this.bDraw));
        g2d.drawArc(this.centerX, this.centerY, this.radiusX ,this.radiusY, this.startAngle, this.length);
    }
}