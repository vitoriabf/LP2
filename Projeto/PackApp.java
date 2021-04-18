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
    boolean mouseClicked = false;
    boolean mousePressed = false;

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

        this.addMouseListener (
        	new MouseAdapter() {
        		public void mousePressed (MouseEvent evt){
        			focus = null;
        			// mousePressed = false;
        			// mouseClicked = true;
        			for (Figure fig: figs){
                        int x1 = fig.x + fig.w;
                        int y1 = fig.y + fig.h;
                        int x = (int)(Math.pow(evt.getX(), 2));
                        int y = (int)(Math.pow(evt.getY(), 2));
                        int a = (int)(Math.pow((fig.w/2),2));
                        int b = (int)(Math.pow((fig.h/2),2));

        				if ((fig.x<=evt.getX()) && (x1>=evt.getX()) && (fig.y<=evt.getY()) && (y1>=evt.getY())){
                            focus = fig;
                        }
                    }
                    prevPt = evt.getPoint();
                    repaint();
                }
        	}
        );


        this.addMouseMotionListener (
        	new MouseMotionAdapter() {
        		public void mouseDragged (MouseEvent evt) {
        			if (focus != null){
                        // mousePressed = true;
        				Point currentPt = evt.getPoint();

	                    int dx = (int)(currentPt.getX() - prevPt.getX());
	                    int dy = (int)(currentPt.getY() - prevPt.getY());
	            		focus.x = (int)((focus.x) + (dx));
	         			focus.y = (int)((focus.y) + (dy));
	                    
	                    prevPt = currentPt;
	                    repaint();
        			}
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
	    						focus = null;
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
        if (focus != null) /*&& (mouseClicked == true))*/{
        	g.setColor(Color.red);
        	g.drawRect(focus.x, focus.y, focus.w, focus.h);
        	
        }
        /*else if ((focus != null) && (mousePressed == true)){
        	g.setColor(Color.red);
        	g.drawRect(focus.x, focus.y, focus.w, focus.h);
        	focus.paint(g);
        }*/
        
    }
}