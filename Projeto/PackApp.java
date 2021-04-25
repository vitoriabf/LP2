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

    String getChecked = "null";

    int contRect, contArc, contSquare, contEllipse;

    Figure focus = null;
    
    Point prevPt;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.setTitle("Projeto");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.gray);


        this.addMouseListener (
        	new MouseAdapter() {
        		public void mousePressed (MouseEvent evt){

        			for (Figure fig: figs){
                        int x1 = fig.x + fig.w;
                        int y1 = fig.y + fig.h;

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
                        contRect +=1;
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
                        contEllipse +=1;
                    }

                    else if (evt.getKeyChar() == 'a') {
                    	int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int startAngle = rand.nextInt(360);
                        int endAngle = rand.nextInt(360);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                    	figs.add(new Arc(x,y, w,h, startAngle,endAngle, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        contArc +=1;

                    } else if (evt.getKeyChar() == 's'){
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350); 
                        int w = rand.nextInt(50);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                        figs.add(new Square(x,y, w,w, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        contSquare +=1;
                    }

                    else if ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE)) {
	    				System.out.println("Pressionou delete");

	    				for (int i = 0; i < figs.size(); i++) {
	    					if (figs.get(i) == focus) {
	    						getChecked = figs.get(i).detected(getChecked);

	    						if (getChecked.contains("Rect")){
	    							contRect -= 1;
	    						} else if (getChecked.contains("Arc")){
	    							contArc -= 1;
	    						} else if (getChecked.contains("Square")){
	    							contSquare -= 1;
	    						} else if (getChecked.contains("Ellipse")){
	    							contEllipse -= 1;
	    						}

	    						figs.remove(figs.get(i));
	    						focus = null;
	    					}
	    				}
                	}
                	else if ((evt.getKeyChar() == '+') && (focus != null)){
                		focus.w = focus.w + 1;
                		focus.h = focus.h + 1;
                	}
                	else if ((evt.getKeyChar() == '-') && (focus != null)){
                		if ((focus.w != 0) || (focus.h != 0)){
                			focus.w = focus.w - 1;
                			focus.h = focus.h - 1;
                		}
                		
                	}
                    else if ((evt.getKeyChar() == ',') && (focus != null)){
                        focus.rFill = rand.nextInt(255);
                        focus.gFill = rand.nextInt(255);
                        focus.bFill = rand.nextInt(255);
                    }
                    else if ((evt.getKeyChar() == '.') && (focus != null)){
                        focus.rDraw = rand.nextInt(255);
                        focus.gDraw = rand.nextInt(255);
                        focus.bDraw = rand.nextInt(255);
                    }
                    repaint();
                }
            }
        );
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, 17));


        g.drawString("Rect: "+contRect, 15, 50);
        g.drawString("Arc: "+contArc, 97, 50);
        g.drawString("Square: "+contSquare, 170, 50);
        g.drawString("Ellipse: "+contEllipse, 263, 50);

        if (focus != null){
        	focus.paint(g);
        	g.setColor(Color.red);
        	g.drawRect(focus.x, focus.y, focus.w, focus.h);
        }
    }
}