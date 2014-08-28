package main;

import java.awt.Graphics;

public class Player extends Friendly{

	
	private double velX = 0;
	private double velY = 0;
	

	
	public Player(double x, double y, Game game){
		super(x, y);
		this.game = game;

		images = game.getPlayerImages();
		img = images[0];
	}
	
	public void tick(){
		x += velX;
		
		if(x > Game.WIDTH-img.getWidth()){
			x = Game.WIDTH-img.getWidth();
		}
		else if(x < 0){
			x = 0;
		}
		y += velY;
		if(y > Game.HEIGHT-img.getHeight()){
			y = Game.HEIGHT-img.getHeight();
		}
		else if(y < 0){
			y = 0;
		}
		

		
	}
	
	public void render(Graphics g){
		g.drawImage(img, (int)x, (int)y, null);
	}
	
	public double getVelX(){
		return velX;
		
	}
	
	public double getVelY(){
		return velY;
	}
	
	public void setVelX(double velX){
		this.velX = velX;
	}
	
	public void setVelY(double velY){
		this.velY = velY;
	}
	
}
