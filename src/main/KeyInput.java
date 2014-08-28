package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Game game;
	private Player p;
	
	
	public KeyInput(Game game, Player p){
		this.game = game;
		this.p = p;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			p.setVelX(5.0);
		}
		else if(key == KeyEvent.VK_LEFT){
			p.setVelX(-5.0);		
		}
		else if(key == KeyEvent.VK_DOWN){
			p.setVelY(5.0);		
		}
		else if(key == KeyEvent.VK_UP){
			p.setVelY(-5.0);		
		}
		else if(key == KeyEvent.VK_SPACE && !game.isShooting){
			game.isShooting = true;
			game.controller.addBullet(new Bullet(p.getX(), p.getY(), game));
			
		}

	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_LEFT){
			p.setVelX(0);		
		}
		else if(key == KeyEvent.VK_DOWN){
			p.setVelY(0);		
		}
		else if(key == KeyEvent.VK_UP){
			p.setVelY(0);		
		}
		else if(key == KeyEvent.VK_SPACE){
			game.isShooting = false;
		}
	}
	
}
