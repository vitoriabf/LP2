package figures;

import ivisible.IVisible;
import java.io.Serializable;
import java.awt.Graphics;

public abstract class Figure implements IVisible, Serializable {
	public int x, y;
    public int w, h;

    public int rFill, gFill, bFill;
    public int rDraw, gDraw, bDraw;

    protected Figure (int x, int y, int w, int h, int rDraw, int gDraw, int bDraw) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rDraw = rDraw;
        this.gDraw = gDraw;
        this.bDraw = bDraw;
    }

    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public abstract void print();
    public abstract String typeOf();

    public boolean clicked (int x, int y) {
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }

}