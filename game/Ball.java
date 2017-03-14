package game;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Shape{	
	
	int dX;
	int dY;
	
	public Ball(){
		x=320-16;
		y=160-16;
		height = width = 32;
		dX=2;
		dY=2;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillOval(x, y, width, height);
	}	
	
	public void update(){
		x+=dX;
		y+=dY;
	}
	
	public void center(){
		x=320-16;
		y=160-16;
	}	
	
	public void switchDX(){
		dX *= -1;
	}
	
	public void switchDY(){
		dY *= -1;
	}
	
	
	
	
}
