package game;

import java.awt.Graphics;

public abstract class Shape {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	
	public Shape(){
		
	}
	
	abstract  void paint(Graphics g);	
	
	
	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
