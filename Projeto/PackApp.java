import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    Figure focus = null;

    Point figureCorner;
    Point prevPt;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.setTitle("Java Packages");
        this.setSize(350, 350);

        figureCorner = new Point(0,0);

        this.addMouseListener (
        	new MouseAdapter() {
        		public void mousePressed (MouseEvent evt) {
        			focus = null;
                    prevPt = evt.getPoint();
                    System.out.println(prevPt);

        			for (Figure fig: figs){
                        int width = fig.x + fig.w;
                        int height = fig.y + fig.h;
        				if ((evt.getX() >= fig.x && evt.getX() <= width) && (evt.getY() >= fig.y && evt.getY() <= height)){
                            focus = fig;
                            focus.print();
                        }
                    }
        		}

        	}
        );


        this.addMouseMotionListener (
        	new MouseMotionAdapter() {
        		public void mouseDragged (MouseEvent evt) {
                    Point currentPt = evt.getPoint();

                    figureCorner.translate(
                        (int)(currentPt.getX() - prevPt.getX()),
                        (int)(currentPt.getY() - prevPt.getY())
                    );
                    prevPt = currentPt;
                    repaint();

                        
                    
           //          for (Figure fig: figs){

           //              if ((evt.getX() >= fig.x && evt.getX() <= width) && (evt.getY() >= fig.y && evt.getY() <= height)) {
           //                  fig.setLocation(fig.x+(evt.getX()), fig.y+evt.getY());
           //                  repaint();
           //              }
        			// }
        		}
        	}
        );
        
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350); 
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);
                        figs.add(new Rect(x,y, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        repaint();  // outer.repaint()
                    }

                    else if (evt.getKeyChar() == 'e') {
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                        figs.add(new Ellipse(x,y, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        repaint();
                    }

                    else if (evt.getKeyChar() == 'a') {
                    	int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int startAngle = rand.nextInt(50);
                        int endAngle = rand.nextInt(360);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                    	figs.add(new Arc(x,y, w,h, startAngle,endAngle, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        repaint();
                    }

                    else if ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE)) {
	    				System.out.println("Pressionou delete");
	    				for (int i = 0; i < figs.size(); i++) {
	    					if (figs.get(i) == focus) {
	    						figs.remove(figs.get(i));
                                repaint();
	    					}
	    				}
                	}
                }
            }
        );
    }

    public void paint (Graphics g) {
        super.paint(g);

        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        if (focus != null) {
             focus.x = (int)(figureCorner.getX());
             focus.y = (int)(figureCorner.getY());
         }
        
    }
}