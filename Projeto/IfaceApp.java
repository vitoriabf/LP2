import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import figures.*;

class IfaceApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();

    String getChecked = "null";

    int contRect, contArc, contLine, contEllipse, j = 0;

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
        			focus = null;
        			for (Figure fig: figs){
        				int x1 = fig.x + fig.w;
        				int y1 = fig.y + fig.h;
                        
        				if (fig.clicked(evt.getX(), evt.getY())){
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
	            		focus.drag(dx, dy);
	                    
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
                        int x = rand.nextInt(300);
                        int y = rand.nextInt(300); 
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
                        int x = rand.nextInt(300);
                        int y = rand.nextInt(300);
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
                    	int x = rand.nextInt(300);
                        int y = rand.nextInt(300);
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

                    } else if (evt.getKeyChar() == 'l'){
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

                        figs.add(new Line(x,y,w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        contLine +=1;
                    }

                    else if ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) && focus != null) {
	    					getChecked = focus.typeOf();

    						if (getChecked.contains("Rect")){
    							contRect -= 1;
    						} else if (getChecked.contains("Arc")){
    							contArc -= 1;
    						} else if (getChecked.contains("Line")){
    							contLine -= 1;
    						} else if (getChecked.contains("Ellipse")){
    							contEllipse -= 1;
    						}

    						figs.remove(focus);
    						focus = null;
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
                    else if( evt.getKeyChar() == java.awt.event.KeyEvent.VK_TAB ) {
                    	System.out.println("Que legal!");
                    	int length = figs.size();
                    	focus = figs.get(j);
                    	j++;
                    	if (j >= length){ j = 0; }
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
        g.drawString("Line: "+contLine, 170, 50);
        g.drawString("Ellipse: "+contEllipse, 263, 50);

        if (focus != null){
        	focus.paint(g);
        	g.setColor(Color.red);
        	g.drawRect(focus.x, focus.y, focus.w, focus.h);
        }
    }
}