import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class Background extends JFrame {
	
	static ArrayList<Rectangle> image = new ArrayList<Rectangle>();
	static ArrayList<Enemies> Enemy = new ArrayList<Enemies>();

	private int screenWidth = 1320;
	private int screenHeight = 480;
	//to scroll background
	private int tilesx;
	private int tilesy;
	//how much screen moves by
	private int offsetx=0;
	private int offsety =0;
	//x locations of onscreen and offscreen images
	private int onscreenx;
	private int offscreenx;
	private int offscreenx2;
	private int offscreenx3;
	private int offscreenx4;
	private int offscreenx5;
	private int offscreenx6;
	private int offscreenx7;
	private int offscreenx8;
	private int offscreenx9;
	private int offscreenx10;
	private int offscreenx11;
	public static boolean scrollingDone = false;
	public static boolean intersects = false;
	
	private static final double FLIP_TIME = 0.125;
	private double timer = 0.0;
        
	//background images
	private Image bg[]= new Image[12];
	private Image offscreen;
	private Graphics goff;
	// width and height of image
        
	private int height= screenHeight;
	private int height10;
        
	private int width0=screenWidth;
	private int width1=screenWidth;
	private int width2=screenWidth;
	private int width3=screenWidth;
	private int width4=screenWidth;
	private int width5=screenWidth;
	private int width6=screenWidth;
	private int width7=screenWidth;
	private int width8=screenWidth;
	private int width9=screenWidth;
	private int width10=screenWidth;
	private int width11=screenWidth;
      
	private int w0=screenWidth;
	private int w1=screenWidth;
	private int w2=screenWidth;
	private int w3=screenWidth;
	private int w4=screenWidth;
	private int w5=screenWidth;
	private int w6=screenWidth;
	private int w7=screenWidth;
	private int w8=screenWidth;
	private int w9=screenWidth;
	private int w10=screenWidth;
	private int w11=screenWidth;
	
	public static int score = 0;
	public String scoreString;
        
	public Background() {
		try {
			bg[0] = new ImageIcon("BG1.png").getImage();
			bg[1] = new ImageIcon("BG2.png").getImage();
			bg[2] = new ImageIcon("BG3.png").getImage();
			bg[3] = new ImageIcon("BG4.png").getImage();
			bg[4] = new ImageIcon("BG5.png").getImage();
			bg[5] = new ImageIcon("BG6.png").getImage();
			bg[6] = new ImageIcon("BG7.png").getImage();
			bg[7] = new ImageIcon("BG8.png").getImage();
			bg[8] = new ImageIcon("BG9.png").getImage();
			bg[9] = new ImageIcon("BG10.png").getImage();
			bg[10] = new ImageIcon("BG11.png").getImage();
			bg[11] = new ImageIcon("BG12.png").getImage();
                        
		} catch(Exception e) {
			bg = null;
		}
                                   
		height= bg[0].getHeight(this)*2;
		width0= bg[0].getWidth(this)*3;
		width1= bg[1].getWidth(this)*3;
		width2= bg[2].getWidth(this)*3;
		width3= bg[3].getWidth(this)*3;
		width4= bg[4].getWidth(this)*3;
		width5= bg[5].getWidth(this)*3;
		width6= bg[6].getWidth(this)*3;
		width7= bg[7].getWidth(this)*3;
		width8= bg[8].getWidth(this)*3;
		width9= bg[9].getWidth(this)*3;
		width10= bg[10].getWidth(this)*3;
		height10= bg[10].getHeight(this)*2;
		width11= bg[11].getWidth(this)*3;
                
		screenHeight = bg[0].getHeight(this)*2;
	
		
		w0= width0;
		w1= width0+width1;
		w2= width0+width1+width2;
		w3= width0+width1+width2+width3;
		w4=width0+width1+width2+width3+ width4;
		w5= width0+width1+width2+width3+ width4+width5;
		w6= width0+width1+width2+width3+ width4+width5+width6;
		w7= width0+width1+width2+width3+ width4+width5+width6+width7;
		w8= width0+width1+width2+width3+ width4+width5+width6+width7+width8;
		w9= width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9;
		w10= width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10;
		
		
		
		

		//Collision Detection Rectangles for whole background
		for(int i=0;i<59;i++)
			image.add(new Rectangle(0, 365, 2495, 115));
		
		//screen 1
		Enemy.add(new Enemies(2, 1200, 100));
		Enemy.add(new Enemies(2, 1200, 300));
		Enemy.add(new Enemies(4, 1245, 115));
		
		//screen 2
		Enemy.add(new Enemies(3, w0 + 500, 340));
		
		//screen 3
		Enemy.add(new Enemies(2, w2 + 2400, 100));
		Enemy.add(new Enemies(2, w2 + 2100, 200));
		Enemy.add(new Enemies(2, w2 + 1800, 300));
		
		//screen 4
		Enemy.add(new Enemies(3, w2 + 140, 350));
		Enemy.add(new Enemies(3, w2 + 240, 218));
	
		//screen 5
		 Enemy.add(new Enemies(3, w3 + 500, 350));
		 
		//screen 6
		 Enemy.add(new Enemies(3, w4 + 500, 350));
		 
		//screen 7
		 Enemy.add(new Enemies(2, w5 + 500, 100));
		 
		 //screen8
		 Enemy.add(new Enemies(3, w6 + 900, 350));
		 Enemy.add(new Enemies(3, w6 + 1500, 350));
		 
		//screen 9
		 Enemy.add(new Enemies(4, w7, 315));
		 
		 Enemy.add(new Enemies(1, w10 + 600, 370));
		 
		 
		
	            
	}
                
        
	
        
        
	public void draw(Graphics g) {                
		//scrolls background
		
//		JLabel scoreLabel = new JLabel(scoreString);
//		scoreLabel.setVerticalTextPosition(200);
//		scoreLabel.setHorizontalTextPosition(200);
		
		for(int y =0; y<1; y++) {
			for(int x=0; x<1;x++) {                            
				onscreenx= x*w0-offsetx;
				offscreenx= w0-offsetx;
                        
				
				offscreenx2= w1-offsetx;
				offscreenx3= w2-offsetx;
				offscreenx4= w3-offsetx;;
				offscreenx5= w4-offsetx;;
				offscreenx6= w5-offsetx;;
				offscreenx7= w6-offsetx;;
				offscreenx8= w7-offsetx;;
				offscreenx9= w8-offsetx;;
				offscreenx10= w9-offsetx;;
				offscreenx11= w10-offsetx;;
                                        
				g.drawImage(bg[0], onscreenx, y*height-offsety,width0,height, null);
				g.drawImage(bg[1], offscreenx, y*height-offsety,width1,screenHeight, null);
				g.drawImage(bg[2], offscreenx2, y*height-offsety,width2,screenHeight, null);
				g.drawImage(bg[3], offscreenx3, y*height-offsety,width3,screenHeight, null);
				g.drawImage(bg[4], offscreenx4, y*height-offsety,width4,screenHeight, null);
				g.drawImage(bg[5], offscreenx5, y*height-offsety,width5,screenHeight, null);
				g.drawImage(bg[6], offscreenx6, y*height-offsety,width6,screenHeight, null);
				g.drawImage(bg[7], offscreenx7, y*height-offsety,width7,screenHeight, null);
				g.drawImage(bg[8], offscreenx8, y*height-offsety,width8,screenHeight, null);
				g.drawImage(bg[9], offscreenx9, y*height-offsety,width9,screenHeight, null);
				g.drawImage(bg[10], offscreenx10, y*height-offsety,width10,screenHeight, null);
				g.drawImage(bg[11], offscreenx11, y*height-offsety,width11,screenHeight, null);
				
				
				
				drawImage1(g);
				drawImage2(g);
				drawImage3(g);
				drawImage4(g);
				drawImage5(g);
				drawImage6(g);
				drawImage7(g);
				drawImage8(g);
				drawImage9(g);
				drawImage10(g);
				drawImage11(g);
				drawImage12(g);		
				
				
			}
		}
		
		scoreString = String.valueOf(score);
		Font f = new Font("Arial", Font.BOLD, 30);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString(scoreString, 620, 50);
	}                
    
	public void left() {
		  
		//if(offsetx>=0)
			//offsetx = (offsetx-10);                              
	}                    
                        
	public void right(double seconds) {
	
		if (scrollingDone == false) {
		timer += seconds;
		if(timer > FLIP_TIME) {
			timer = 0;
		}
		else {
			if(offsetx< width0+width1+width2+width3+ width4+width5+width6+width7+width8+width9+width10-(screenWidth-width10)) {
					offsetx = (offsetx+5);
					Enemy.get(0).scroll(15);
					Enemy.get(1).scroll(15);
					Enemy.get(2).scroll(5);
					Enemy.get(3).scroll(5);
					Enemy.get(4).scroll(15);
					Enemy.get(5).scroll(15);
					Enemy.get(6).scroll(15);
					Enemy.get(7).scroll(5);
					Enemy.get(8).scroll(5);
					Enemy.get(9).scroll(5);
                    Enemy.get(10).scroll(5);
                    Enemy.get(11).scroll(15);
                    Enemy.get(12).scroll(5);
                    Enemy.get(13).scroll(5);
                    Enemy.get(14).scroll(5);
                    Enemy.get(15).scroll(5);
				
			}
			else {
				scrollingDone = true;
				GameWorld.bgMusic.stop();
				GameWorld.bossMusic.gameMusic();
			}
		}
		}
		for(int i=0; i< Enemy.size();i++)
			Enemy.get(i).update(seconds);
	}
                        

                        
                        
	private void drawImage1(Graphics g) {
			
		
		//Collision Detection Rectangles for BG Image 1
		image.get(0).setBounds(0 - offsetx, 365, 2495, 115);
		image.get(1).setBounds(765 - offsetx, 300, 769, 180);
		image.get(2).setBounds(957 - offsetx, 238, 480, 242);
		image.get(3).setBounds(1150 - offsetx, 172, 190, 308);
		//enemies for Image 1
		Enemy.get(0).draw(g);
		Enemy.get(1).draw(g);
		Enemy.get(2).draw(g);
		
		/*//Collision Detection Rectangles for BG Image 1
		g.setColor(Color.red);
		g.drawRect(0 - offsetx, 365, 2495, 115);
		g.drawRect(765 - offsetx, 300, 769, 180);
		g.drawRect(957 - offsetx, 238, 480, 242);
		g.drawRect(1150 - offsetx, 172, 190, 308);
		*/
				
	}
	
	
	private void drawImage2(Graphics g) {
		
		//Collision Detection Rectangles for BG Image 2
		image.get(4).setBounds(offscreenx + 982, 300, 190, 308);
		image.get(5).setBounds(offscreenx + 1080, 242, 107, 320);
		Enemy.get(3).draw(g);
		
		/*
		g.setColor(Color.red);
		//Collision Detection Rectangles for BG Image 2
		g.drawRect(offscreenx + 982, 300, 190, 308);
		g.drawRect(offscreenx + 1080, 242, 107, 320);
	*/
	
	}
	
	private void drawImage3(Graphics g) {
	
		//Collision Detection Rectangles for BG Image 3
		image.get(6).setBounds(offscreenx2 + 125, 376, 285, 115);
		image.get(7).setBounds(offscreenx2 + 210, 315, 185, 308);
		image.get(8).setBounds(offscreenx2 + 317, 242, 98, 325);
		image.get(9).setBounds(offscreenx2 + 510, 376, 280, 115);
		image.get(10).setBounds(offscreenx2 + 605, 310, 180, 308);
		image.get(11).setBounds(offscreenx2 + 702, 242, 97, 325);
		image.get(12).setBounds(offscreenx2 + 893, 376, 280, 115);
		image.get(13).setBounds(offscreenx2 + 990, 310, 180, 308);
		image.get(14).setBounds(offscreenx2 + 1088, 242, 97, 325);
		image.get(15).setBounds(offscreenx2 + 1278, 376, 280, 115);
		image.get(16).setBounds(offscreenx2 + 1470, 310, 180, 308);
		image.get(17).setBounds(offscreenx2 + 1568, 242, 100, 325);
		Enemy.get(4).draw(g);
		Enemy.get(5).draw(g);
		Enemy.get(6).draw(g);
		
		/*
		//Collision Detection Rectangles for BG Image 3
		g.setColor(Color.red);
		g.drawRect(offscreenx2 + 125, 376, 280, 115);
		g.drawRect(offscreenx2 + 220, 310, 180, 308);
		g.drawRect(offscreenx2 + 317, 242, 98, 325);
		g.drawRect(offscreenx2 + 510, 376, 280, 115);
		g.drawRect(offscreenx2 + 605, 310, 180, 308);
		g.drawRect(offscreenx2 + 702, 242, 97, 325);
		g.drawRect(offscreenx2 + 893, 376, 280, 115);
		g.drawRect(offscreenx2 + 990, 310, 180, 308);
		g.drawRect(offscreenx2 + 1088, 242, 97, 325);
		g.drawRect(offscreenx2 + 1278, 376, 280, 115);
		g.drawRect(offscreenx2 + 1470, 310, 180, 308);
		g.drawRect(offscreenx2 + 1568, 242, 100, 325); 
		*/	
	
	
	}
	
	private void drawImage4(Graphics g){
	
	
		//Collision Detection Rectangles for BG Image 4
		image.get(18).setBounds(offscreenx3, 374, 1100, 104);
		image.get(19).setBounds(offscreenx3 + 170, 240, 285, 33);
		image.get(20).setBounds(offscreenx3 + 365, 207, 93, 66);
		image.get(21).setBounds(offscreenx3 + 599, 141, 97, 33);
		image.get(22).setBounds(offscreenx3 + 745, 306, 49, 33);
		image.get(23).setBounds(offscreenx3 + 794, 207, 49, 33);
		image.get(24).setBounds(offscreenx3 + 840, 108, 292, 33);
		image.get(25).setBounds(offscreenx3 + 986, 174, 146, 33);
		image.get(26).setBounds(offscreenx3 + 1035, 207, 97, 33);
		image.get(27).setBounds(offscreenx3 + 1035, 339, 97, 33);
		image.get(28).setBounds(offscreenx3 + 1082, 240, 49, 33);
		image.get(29).setBounds(offscreenx3 + 1130, 108, 96, 350);
		Enemy.get(7).draw(g);
		Enemy.get(8).draw(g);
		
		
		/*
		//Collision Detection Rectangles for BG Image 4
		g.setColor(Color.red);
		g.drawRect(offscreenx3, 374, 1100, 104);
		g.drawRect(offscreenx3 + 170, 240, 285, 33);
		g.drawRect(offscreenx3 + 365, 207, 93, 66);
		g.drawRect(offscreenx3 + 599, 141, 97, 33);
		g.drawRect(offscreenx3 + 745, 306, 49, 33);
		g.drawRect(offscreenx3 + 794, 207, 49, 33);
		g.drawRect(offscreenx3 + 1035, 207, 97, 33);
		g.drawRect(offscreenx3 + 1082, 240, 49, 33);
		g.drawRect(offscreenx3 + 1035, 339, 97, 33);
		g.drawRect(offscreenx3 + 986, 174, 146, 33);
		g.drawRect(offscreenx3 + 840, 108, 292, 33);
		g.drawRect(offscreenx3 + 1130, 108, 96, 350); 
		*/
			
	}
	
	
	private void drawImage5(Graphics g){
		
		//Collision Detection Rectangles for BG Image 5
		image.get(30).setBounds(offscreenx4 + 62, 376, 1655, 104);
		image.get(31).setBounds(offscreenx4 + 1532, 310, 175, 170);
		image.get(32).setBounds(offscreenx4 + 1628, 242, 98, 238);
		Enemy.get(9).draw(g);
		
		
		/*
		//Collision Detection Rectangles for BG Image 5
		g.setColor(Color.red);
		g.drawRect(offscreenx4 + 62, 376, 1655, 104);
		g.drawRect(offscreenx4 + 1532, 310, 175, 170);
		g.drawRect(offscreenx4 + 1628, 242, 98, 238);
		*/
	
	
	}
	
	private void drawImage6(Graphics g){
	
	
		//Collision Detection Rectangles for BG Image 6
		image.get(33).setBounds(offscreenx5 + 120, 376, 285, 115);
		image.get(34).setBounds(offscreenx5 + 205, 315, 185, 308);
		image.get(35).setBounds(offscreenx5 + 312, 242, 98, 325);
		image.get(36).setBounds(offscreenx5 + 505, 376, 280, 115);
		image.get(37).setBounds(offscreenx5 + 600, 310, 180, 308);
		image.get(38).setBounds(offscreenx5 + 697, 242, 97, 325);
		image.get(39).setBounds(offscreenx5 + 888, 376, 280, 115);
		image.get(40).setBounds(offscreenx5 + 985, 310, 180, 308);
		image.get(41).setBounds(offscreenx5 + 1083, 242, 97, 325);
		image.get(42).setBounds(offscreenx5 + 1273, 376, 780, 115);
		Enemy.get(10).draw(g);
	
		
		/*
		//Collision Detection Rectangles for BG Image 6
		g.setColor(Color.red);
		g.drawRect(offscreenx5 + 120, 376, 280, 115);
		g.drawRect(offscreenx5 + 215, 310, 180, 308);
		g.drawRect(offscreenx5 + 312, 242, 98, 325);
		g.drawRect(offscreenx5 + 505, 376, 280, 115);
		g.drawRect(offscreenx5 + 600, 310, 180, 308);
		g.drawRect(offscreenx5 + 697, 242, 97, 325);
		g.drawRect(offscreenx5 + 888, 376, 280, 115);
		g.drawRect(offscreenx5 + 985, 310, 180, 308);
		g.drawRect(offscreenx5 + 1083, 242, 97, 325);
		g.drawRect(offscreenx5 + 1273, 376, 780, 115);
		*/
		
	}
	
	
	private void drawImage7(Graphics g){
		
		//Collision Detection Rectangles for BG Image 7
		image.get(43).setBounds(offscreenx6 + 620, 115, 97, 300);
		image.get(44).setBounds(offscreenx6 + 236, 312, 49, 33);
		image.get(45).setBounds(offscreenx6 + 284, 215, 49, 33);
		image.get(46).setBounds(offscreenx6 + 332, 115, 386, 33);
		image.get(47).setBounds(offscreenx6 + 92, 148, 94, 33);
		image.get(48).setBounds(offscreenx6 - 122, 377, width6, 200);
		Enemy.get(11).draw(g);
		
		
		
		/*
		//Collision Detection Rectangles for BG Image 7
		g.setColor(Color.red);
		g.drawRect(offscreenx6 + 620, 115, 97, 300);
		g.drawRect(offscreenx6 + 236, 312, 49, 33);
		g.drawRect(offscreenx6 + 284, 215, 49, 33);
		g.drawRect(offscreenx6 + 332, 115, 386, 33);
		g.drawRect(offscreenx6 + 92, 148, 94, 33);
		g.drawRect(offscreenx6 - 122, 377, width6, 200); 
		*/
			
	
	}

	
	
	private void drawImage8(Graphics g){
	
		//Collision Detection Rectangles for BG Image 8
		image.get(49).setBounds(offscreenx7+572, 210, 49, 33);
		image.get(50).setBounds(offscreenx7+332, 308, 49, 33);
		image.get(51).setBounds(offscreenx7+379, 210, 49, 33);
		image.get(52).setBounds(offscreenx7+40, 142, 49, 33);
		image.get(53).setBounds(offscreenx7+138, 372, 100, 200);
		image.get(54).setBounds(offscreenx7+765, 372, width7, 200);
		Enemy.get(12).draw(g);
		Enemy.get(13).draw(g);
		
		/*
		//Collision Detection Rectangles for BG Image 8
		g.setColor(Color.red);
		g.drawRect(offscreenx7+572, 210, 49, 33);
		g.drawRect(offscreenx7+332, 308, 49, 33);
		g.drawRect(offscreenx7+379, 210, 49, 33);
		g.drawRect(offscreenx7+40, 142, 49, 33);
		g.drawRect(offscreenx7+138, 372, 100, 200);
		g.drawRect(offscreenx7+765, 372, width7, 200);
		*/
		
	}
	
	private void drawImage9(Graphics g){
	
		//Collision Detection Rectangles for BG Image 9
		image.get(55).setBounds(offscreenx8, 372, width8, 200);
		Enemy.get(14).draw(g);
		
		/*
		//Collision Detection Rectangles for BG Image 9
		g.setColor(Color.red);
		g.drawRect(offscreenx8, 372, width8, 200);
		*/
		
		
	}
	
	private void drawImage10(Graphics g){
	
		//Collision Detection Rectangles for BG Image 10
		image.get(56).setBounds(offscreenx9-80, 308, 500, 200);
	
		/*
		//Collision Detection Rectangles for BG Image 10
		g.setColor(Color.red);
		g.drawRect(offscreenx9-80, 308, 500, 200);
		*/
		
	}
	
	private void drawImage11(Graphics g){
	
		
		//Collision Detection Rectangles for BG Image 11
		image.get(57).setBounds(offscreenx10, 308, 918, 200);
		
		/*
		//Collision Detection Rectangles for BG Image 11
		g.setColor(Color.red);
		g.drawRect(offscreenx10, 308, 918, 200);
		*/
	}
	
	private void drawImage12(Graphics g){
	
	
		//Collision Detection Rectangles for BG Image 12
		image.get(58).setBounds(offscreenx11, 402, 1300, 50);
		if (scrollingDone == true)
			Enemy.get(15).draw(g);
		if (!Enemy.get(15).isAlive()) {
			Font f = new Font("Arial", Font.BOLD, 150);
			g.setFont(f);
			g.drawString("Congratulations!", 70, 200);
			g.drawString("You Win!", 300, 400);
			try {
			PrintWriter out = new PrintWriter("highscore.txt");
			String t = String.valueOf(score);
			out.println(t);
			out.close();
			} catch (Exception e) {
				//unused
			}
		}
		
		/*
		//Collision Detection Rectangles for BG Image 12
		g.setColor(Color.red);
		g.drawRect(offscreenx11, 402, 1300, 50);
		*/
		
	}
	}
