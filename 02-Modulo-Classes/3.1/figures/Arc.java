package figures;

import java.awt.*;

public class Arc {
    int centerX, centerY;
    int length, startAngle;
    int radiusX, radiusY;

    public Arc (int centerX, int centerY, int length, int startAngle, int radiusX, int radiusY){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.length = length;
        this.startAngle = startAngle;
    }

    public void print () {
        System.out.format("Arc de center (%d,%d), length (%d) e radius(%d,%d).\n",
            this.centerX, this.centerY, this.length,this.radiusX , this.radiusY);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawArc(this.centerX, this.centerY, this.radiusX ,this.radiusY, this.startAngle, this.length);
    }
}