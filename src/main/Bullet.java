package main;

import java.awt.Graphics;

public class Bullet extends Friendly implements Entity{
	
	public Bullet(double x, double y, Game game){
		super(x, y);
		this.game = game;
		images = game.getBulletImages();
		img = images[1];
	}
	
	public void tick(){
		y -= 10;
		if(y < 0){
			game.controller.removeBullet(this);
		}
	}
	
	public void render(Graphics g){
		g.drawImage(img, (int)x, (int)y, null);
	}
	
	

}
