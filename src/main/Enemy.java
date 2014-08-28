package main;

import java.awt.Graphics;
import java.util.Random;

public class Enemy extends GameObject implements Entity{
	private Random r = new Random();
	
	public Enemy(double x, double y, Game game){
		super(x, y);
		this.game = game;
		images = game.getEnemyImages();
		img = images[0];
	}
	
	public void tick(){
		y += 2;
		if(y > Game.HEIGHT){
			y = 0;
			x = r.nextInt(Game.WIDTH-img.getWidth());
		}
	}
	
	public void render(Graphics g){
		g.drawImage(img, (int)x, (int)y, null);
	}

	
}
