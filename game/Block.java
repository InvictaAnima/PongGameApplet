package game;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends Shape{
	
	int dY;	

	public Block(int x, int y){
		this.x=x;
		this.y=y;
		this.dY=0;
		width=16;
		height=128;
		dY=0;
	}
			
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);		
	}
	
	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}
	
}
