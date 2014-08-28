package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class End {
	
	private Game game;
	
	public End(Game game){
		this.game = game;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		Font f = new Font("Verdana", Font.BOLD, 50);
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString("Congratulations!", game.WIDTH/4, 200);
		g.drawString("You killed "+game.controller.totalPoint+" enemys!", game.WIDTH/5, 300);
		
		Font f1 = new Font("Verdana", Font.BOLD, 30);
		g.setFont(f1);
		g.drawString("click anywhere to exit.", game.WIDTH/3, 500);
	}
}
