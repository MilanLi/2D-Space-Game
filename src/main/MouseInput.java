package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	private Game game;
	
	public MouseInput(Game game){
		this.game = game;
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
//		playButton = new Rectangle(Game.WIDTH/3, 400, 300, 100);
//		quitButton = new Rectangle(Game.WIDTH/3, 550, 300, 100);
		
		if(game.getGameState() == Game.STATE.MENU){
			Rectangle playButton = game.getMenu().playButton;
			Rectangle quitButton = game.getMenu().quitButton;
			if(x <= playButton.x+playButton.getWidth() && x >= playButton.x &&
				y <= playButton.y+playButton.getHeight() && y >= playButton.y){
				game.setGameState(Game.STATE.GAME);
			}
			else if(x <= quitButton.x+quitButton.getWidth() && x >= quitButton.x &&
					y <= quitButton.y+quitButton.getHeight() && y >= quitButton.y){
					System.exit(1);
				}
		}
		else if(game.getGameState() == Game.STATE.END){
			System.exit(1);
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
