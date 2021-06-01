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
    private ArrayList<Figure> figs = new ArrayList<Figure>();
    private ArrayList<Button> buts = new ArrayList<Button>();
    private ArrayList <Integer> list = new ArrayList<Integer>();

    private Random rand = new Random();

    private String getChecked = "null";

    private int j = 0;
<<<<<<< HEAD
    private int x1, y1;
    private Figure focus = null;
    private Button focus_but = null;
    private Figure last_focus = null;
    private Figure buttonMouse = new Ellipse (28,275,10,10,255, 0, 0,0, 0, 0);

=======

    private Figure focus = null;
    private Button focus_but = null;
    private Figure last_focus = null;

>>>>>>> d43031c0a3f3361bc4082ad56690f4a9351c60ea
    private ImageIcon imagem = new ImageIcon(getClass().getResource("img/garbage.png"));
    private Image img = imagem.getImage();
    
    private Point prevPt;
<<<<<<< HEAD

    private boolean positionMouse = false;
=======
>>>>>>> d43031c0a3f3361bc4082ad56690f4a9351c60ea

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

        //as teclas tab e shift tab são consumidas sem serem propagadas para os 
        //KeyListeners
        this.setFocusTraversalKeysEnabled(false);


        
        buts.add(new Button(1, new Rect(300,80,5,30,0,0,0,0,0,0)));
        buts.add(new Button(2, new Ellipse(5,20,5,5,0,0,0,0,0,0)));
        buts.add(new Button(3, new Arc(5,5,5,30,150,180,0,0,0,0,0,0)));
        buts.add(new Button(4, new Line(30, 190, 50, 210,0,0,0)));
        buts.add(new Button(5, new Arc(0,0,0,0,0,0,0,0,0,0,0,0)));

        for (int i = 0; i < 4; i++) list.add((int)(0));
        

        this.addMouseListener (
        	new MouseAdapter() {
        		public void mousePressed (MouseEvent evt){
                    if ((focus_but != null) &&
                        ((56<evt.getX() || evt.getX()<24) || (216<evt.getY() || evt.getY()<64))
                        ){
                    	
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
                            if (focus == last_focus){
                                break;
                            }
        				}
                    }

                    for (Button but: buts){
                        if (but.clicked(evt.getX(), evt.getY())){
                            focus_but = but;

                            if (focus_but.idx == 5 && last_focus != null){
                                getChecked = last_focus.typeOf();

                                if (getChecked.contains("Rect")){
                                    list.set(0, (list.get(0))-1);
                                } else if (getChecked.contains("Arc")){
                                    list.set(1, (list.get(1))-1);
                                } else if (getChecked.contains("Line")){
                                    list.set(2, (list.get(2))-1);
                                } else if (getChecked.contains("Ellipse")){
                                    list.set(3, (list.get(3))-1);
                                } 

                                figs.remove(last_focus);
                                focus_but = null;
                                
                            } //else if (focus_but.idx == 5 && last_focus == null) focus_but = null;
                            focus = null;
                            last_focus = null;
                        }
                    }
                    last_focus = focus;

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
        		};

                public void mouseMoved(MouseEvent evt){
                    x1 = evt.getX();
                    y1 = evt.getY();
                }
        	}
        );
        
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'p') {
                        if (positionMouse) positionMouse = false; else positionMouse = true;

                    } else if (evt.getKeyChar() == 'r') {
                        int x = rand.nextInt(300);
                        int y = rand.nextInt(300); 
                        int w = rand.nextInt(50)+20;
                        int h = rand.nextInt(50)+20;
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);
                        

                        if (positionMouse) {
                            //figs.get(figs.size()-1).x = x1- (figs.get(figs.size()-1).w)/2;
                            //figs.get(figs.size()-1).y = y1- (figs.get(figs.size()-1).h)/2;
                            figs.add(new Rect(x1-w/2,y1-h/2, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        } else {
                            figs.add(new Rect(x,y, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        }

                        list.set(0, (list.get(0))+1);
                    }

                    else if (evt.getKeyChar() == 'e') {
                        int x = rand.nextInt(300);
                        int y = rand.nextInt(300);
                        int w = rand.nextInt(50)+20;
                        int h = rand.nextInt(50)+20;
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                        if (positionMouse) {
                            figs.add(new Ellipse(x1-w/2,y1-h/2, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        } else {
                            figs.add(new Ellipse(x,y, w,h, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        }

                        list.set(3, (list.get(3))+1);
                    }

                    else if (evt.getKeyChar() == 'a') {
                    	int x = rand.nextInt(300);
                        int y = rand.nextInt(300);
                        int w = rand.nextInt(50)+20;
                        int h = rand.nextInt(50)+20;
                        int startAngle = rand.nextInt(360);
                        int endAngle = rand.nextInt(360);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                        if (positionMouse) {
                            figs.add(new Arc(x1-w/2,y1-h/2, w,h, startAngle,endAngle, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        } else {
                            figs.add(new Arc(x,y, w,h, startAngle,endAngle, rFill,gFill,bFill, rDraw,gDraw,bDraw));
                        }

                        list.set(1, (list.get(1))+1);

                    } else if (evt.getKeyChar() == 'l'){
                    	int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50)+5;
                        int h = rand.nextInt(50)+5;
                        int rDraw = rand.nextInt(255);
                        int gDraw = rand.nextInt(255);
                        int bDraw = rand.nextInt(255);

                        if (positionMouse) {
                            figs.add(new Line(x1,y1, w,h, rDraw,gDraw,bDraw));
                        } else {
                            figs.add(new Line(x,y, w,h, rDraw,gDraw,bDraw));
                        }

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
                		if ((focus.w != 10) || (focus.h != 10)){
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
                    else if( evt.getKeyChar() == java.awt.event.KeyEvent.VK_TAB) {
                    	int length = figs.size();
                    	focus = figs.get(j);
                    	j++;
                    	if (j >= length){ j = 0; }
                    } else if ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) && (focus != null)) {

                        if (focus.typeOf().contains("Line")){
                            focus.y -= 10;
                            focus.h -= 10;
                        } else {focus.y -= 10;}
                        
                    } else if  ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) && (focus != null)) {

                        if (focus.typeOf().contains("Line")){
                            focus.y += 10;
                            focus.h += 10;
                        } else {focus.y += 10;}

                    } else if  ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) && (focus != null)) {

                        if (focus.typeOf().contains("Line")){
                            focus.x -= 10;
                            focus.w -= 10;
                        } else {focus.x -= 10;}

                    } else if  ((evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) && (focus != null)) {

                        if (focus.typeOf().contains("Line")){
                            focus.x += 10;
                            focus.w += 10;
                        } else {focus.x += 10;}
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
        g.drawImage(img, 26,225, null);
        
        if (positionMouse) {
            g.setFont(new Font("TimesRoman", Font.BOLD, 10));
            g.setColor(Color.black);
            buttonMouse.rFill = 103;
            buttonMouse.gFill = 250;
            buttonMouse.bFill = 142;

            g.drawString("ON", 40,284);
            buttonMouse.paint(g, false);
        } else {
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.BOLD, 10));
            buttonMouse.rFill = 255;
            buttonMouse.gFill = 0;
            buttonMouse.bFill = 0;

            g.drawString("OFF", 40,284);
            buttonMouse.paint(g, false);
        }
        

        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, 17));

        g.drawString("Rect: "+list.get(0), 15, 50);
        g.drawString("Arc: "+list.get(1), 97, 50);
        g.drawString("Line: "+list.get(2), 170, 50);
        g.drawString("Ellipse: "+list.get(3), 263, 50);

    }
}