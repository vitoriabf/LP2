package figures;

import java.awt.Graphics;

public abstract class Figure {
	int x, y;
    int w, h;
    int rFill, gFill, bFill;
    int rDraw, gDraw, bDraw;
    public abstract void paint (Graphics g);
}