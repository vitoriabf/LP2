package figures;

import java.awt.Graphics;

public abstract class Figure {
	public int x, y;
    public int w, h;
    public int rFill, gFill, bFill;
    public int rDraw, gDraw, bDraw;
    public abstract void paint (Graphics g);
    public abstract void print();
    public abstract String detected(String variable);
}