package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Applet implements Runnable, KeyListener{
	
    private static final long serialVersionUID = 1L;
	final int WIDTH = 640; //SZEREKOSC
    final int HEIGHT = 320; //WYSOKOSC
    
	private Boolean gameOver;
	private Ball ball;
	private Block blockLeft;
    private Block blockRight;
    
    private int leftPoints;
    private int rightPoints;
	
    private Thread gameThread;
    
    private Boolean wPressed;
    private Boolean sPressed;
    
    private Boolean UpPressed;
    private Boolean DownPressed;
    private Game game;
    
       
    @Override
    public void init(){
    	game = this;
    	gameOver = false;
    	ball = new Ball();
    	blockLeft = new Block(16,96);
    	blockRight = new Block(608,96);
    	leftPoints=0;
    	rightPoints=0;
    	
    	wPressed=false;
    	sPressed=false;
    	UpPressed=false;
    	DownPressed=false;
    	
    	addKeyListener(this);
    	this.setSize(WIDTH, HEIGHT);  
    	
    	new Timer().schedule(new TimerTask(){
          @Override
          public void run(){
            game.run();
          }
        }, 0, 40);
    }
    
    public void start() {
		
		if (gameThread == null)
			gameThread = new Thread(this, "PongGame");
		
		gameThread.start();	
		
	}
    
    public void stop() {
		if (gameThread != null)
			gameThread.stop();
		gameThread = null;
		System.gc();
	}

    
    @Override
    public void paint(Graphics g) {
    	update(g);
      }
    
    public void update(Graphics g){
    	g.setColor(Color.PINK);
    	g.fillRect(0, 0, WIDTH,HEIGHT);
    	ball.paint(g);
    	blockLeft.paint(g);
    	blockRight.paint(g);
    	g.setFont(new Font("TimesRoman", Font.BOLD, 18));
    	g.setColor(Color.black);
    	g.drawString(""+leftPoints, 48, 32);
    	g.drawString(""+rightPoints, 592, 32);
    	
    	if(gameOver){    		
    		g.drawString("GameOver", 280, 150);
    	}
    	
    }
	
	@Override
	public void run() {
		if(!gameOver){
			
			ball.update();
			checkColission();
			updateBlocks();
			
			repaint();
//			try {
//				Thread.sleep(20);
//			} catch (InterruptedException e) {
//			}
			
		}
		repaint();
	}	
	
	public void checkColission(){
		int bX = ball.getX();
		int bY = ball.getY();
		
		if(bX >= blockRight.getX()){
			leftPoints++;
			
			if(leftPoints>=3){
				gameOver=true;
			}
			ball.switchDX();
			ball.center();
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		} 
		
		if(bX <= blockLeft.getX()){
			rightPoints++;
			
			if(rightPoints>=3){
				gameOver=true;
			}
			ball.switchDX();
			ball.center();
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		} 
		
		if(bY <= 0 || bY >= HEIGHT-32){
			ball.switchDY();
		}
		
		if(bX<=blockLeft.getX()+blockLeft.getWidth()){
			if(bY>=blockLeft.getY() && bY<=blockLeft.getY()+blockLeft.getHeight()){
				ball.switchDX();
			}
		}
		
		if(bX+ball.getWidth()>=blockRight.getX()){
			if(bY>=blockRight.getY() && bY<=blockRight.getY()+blockRight.getHeight()){
				ball.switchDX();
			}
		}
		
		
	}
	
	public void updateBlocks(){
		
		blockLeft.setY(blockLeft.getY()+blockLeft.getdY());
		blockRight.setY(blockRight.getY()+blockRight.getdY());
		
		if(!wPressed && !sPressed){
			if(blockLeft.getdY()>0){
				blockLeft.setdY(blockLeft.getdY()-1);
			} else if(blockLeft.getdY()<0){
				blockLeft.setdY(blockLeft.getdY()+1);
			}
		}
		
		if(!UpPressed && !DownPressed){
			if(blockRight.getdY()>0){
				blockRight.setdY(blockRight.getdY()-1);
			} else if(blockRight.getdY()<0){
				blockRight.setdY(blockRight.getdY()+1);
			}
		}
		
		if(wPressed){
			blockLeft.setdY(-3);
		} else if(sPressed){
			blockLeft.setdY(3);
		} 
		
		if(UpPressed){
			blockRight.setdY(-3);
		} else if(DownPressed){
			blockRight.setdY(3);
		}	
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W){
			wPressed = true;
		}
		
		if(key == KeyEvent.VK_S){
			sPressed = true;
		}
		
		if(key == KeyEvent.VK_UP){
			UpPressed = true;
		}
		
		if(key == KeyEvent.VK_DOWN){
			DownPressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W){
			wPressed = false;
			
		}
		
		if(key == KeyEvent.VK_S){
			sPressed = false;
		}
		
		if(key == KeyEvent.VK_UP){
			UpPressed = false;
		}
		
		if(key == KeyEvent.VK_DOWN){
			DownPressed = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
