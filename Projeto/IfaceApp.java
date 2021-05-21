import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.*;
import figures.*;

class IfaceApp {
    public static void main (String[] args) {
        IfaceFrame frame = new IfaceFrame();
        frame.setVisible(true);
    }
}

class IfaceFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Button> buts = new ArrayList<Button>();
    ArrayList <Integer> list = new ArrayList<Integer>();

    Random rand = new Random();

    String getChecked = "null";

    int contRect, contArc, contLine, contEllipse, j = 0;

    Figure focus = null;
    Button focus_but = null;
    
    Point prevPt;

    IfaceFrame () {
        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            this.list = (ArrayList<Integer>) o.readObject();
            o.close();
        } catch (Exception x) {
            System.out.println("ERRO!");
        }


        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.writeObject(list);
                        o.flush();
                        o.close();
                    } catch (Exception x) {
                        System.out.println("ERRO2!");
                    }
                    System.exit(0);
                }
            }
        );

        this.setTitle("Projeto");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.gray);

        

        
        buts.add(new Button(1, new Rect(300,80,5,30,0,0,0,0,0,0)));
        buts.add(new Button(2, new Ellipse(5,20,5,5,0,0,0,0,0,0)));
        buts.add(new Button(3, new Arc(5,5,5,30,150,180,0,0,0,0,0,0)));
        buts.add(new Button(4, new Line(30, 190, 50, 210,0,0,0)));

        for (int i = 0; i < 4; i++) list.add((int)(0));
        

        this.addMouseListener (
        	new MouseAdapter() {
        		public void mousePressed (MouseEvent evt){
                    if (focus_but != null){
                        if (focus_but.idx == 1){
                            figs.add(new Rect(evt.getX()-15,evt.getY()-15,30,30,0,0,0,0,0,0));
                            list.set(0, (list.get(0))+1);
                        } else if (focus_but.idx == 2){
                            figs.add(new Ellipse(evt.getX()-15,evt.getY()-15,30,30,0,0,0,0,0,0));
                            list.set(3, (list.get(3))+1);
                        } else if (focus_but.idx == 3){
                            figs.add(new Arc(evt.getX()-15,evt.getY()-15,30,30,150,180,0,0,0,0,0,0));
                            list.set(1, (list.get(1))+1);
                        } else if (focus_but.idx == 4){
                            figs.add(new Line(evt.getX(),evt.getY(),rand.nextInt(300),rand.nextInt(300),0,0,0));
                            list.set(2, (list.get(2))+1);
                        }
                    }

        			focus = null;
                    focus_but = null;

        			for (Figure fig: figs){
        				if (fig.clicked(evt.getX(), evt.getY())){
                            focus = fig;
        				}
                    }

                    for (Button but: buts){
                        if (but.clicked(evt.getX(), evt.getY())){
                            focus = null;
                            focus_but = but;
                        }
                    }

                    // if (focus_but != null) focus = null; else if (focus != null) focus_but = null;

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
                        list.set(0, (list.get(0))+1);
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
                        list.set(3, (list.get(3))+1);
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
                        list.set(1, (list.get(1))+1);

                    } else if (evt.getKeyChar() == 'l'){
                    	int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                        figs.add(new Line(x,y,w,h, rDraw,gDraw,bDraw));
                        list.set(2, (int)((list.get(2))+1));
                    }

                    else if ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) && focus != null) {
	    					getChecked = focus.typeOf();

    						if (getChecked.contains("Rect")){
    							list.set(0, (list.get(0))-1);
    						} else if (getChecked.contains("Arc")){
    							list.set(1, (list.get(1))-1);
    						} else if (getChecked.contains("Line")){
    							list.set(2, (list.get(2))-1);
    						} else if (getChecked.contains("Ellipse")){
                                list.set(3, (list.get(3))-1);
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
            fig.paint(g, false);
        }

        if (focus != null) focus.paint(g, true);

         for (Button but: this.buts) {
             but.paint(g, but == focus_but);
        }
        
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, 17));

        g.drawString("Rect: "+list.get(0), 15, 50);
        g.drawString("Arc: "+list.get(1), 97, 50);
        g.drawString("Line: "+list.get(2), 170, 50);
        g.drawString("Ellipse: "+list.get(3), 263, 50);

    }
}