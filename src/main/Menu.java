package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Menu {
	private Game game;
	private BufferedImage[] ships;
	
	
	public Rectangle playButton;
	public Rectangle quitButton;
	
	public Menu(Game game){
		this.game = game;
		ships = game.getPlayerImages();
		playButton = new Rectangle(Game.WIDTH/3, 400, 300, 100);
		quitButton = new Rectangle(Game.WIDTH/3, 550, 300, 100);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		
		// 2D space game
		Font f0 = new Font("arial", Font.BOLD, 80);
		g.setFont(f0);
		g.setColor(Color.RED);
		g.drawString("2D Space Game", Game.WIDTH/5, 200);
		
		// different kinds of ships
		for(int i = 0; i < ships.length; i++){
			g.drawImage(ships[i], Game.WIDTH/4 + Game.WIDTH*3*i/(5*ships.length), 300, null);
		}
		
		// draw buttons
		Font f1 = new Font("arial", Font.BOLD, 50);
		g.setFont(f1);
		g.drawString("Play", playButton.x + 100, playButton.y + 70);
		g.drawString("Quit", quitButton.x + 100, quitButton.y + 70);
		
		g.setColor(Color.WHITE);
		Graphics2D g2d = (Graphics2D)g;
		g2d.draw(playButton);
		g2d.draw(quitButton);
		
		
		
		
	}
}
