package main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	private LinkedList<Bullet> bullets;
	private LinkedList<Enemy> enemys;
	private Player player;
	
	private Game game;
	private Random r = new Random();
	private int enemyCount = 1;
	private int enemyKilled = 0;
	public int totalPoint = 0;
	
	public Controller(Game game, Player p){
		bullets = new LinkedList<Bullet>();
		enemys = new LinkedList<Enemy>();
		this.game = game;
		this.player = p;
		createEnemy(enemyCount);
	}
	
	private void createEnemy(int enemyCount){
		for(int i = 0; i < enemyCount; i++){
			addEnemy(new Enemy(r.nextInt(Game.WIDTH-50), 0, game));
		}
	}
	
	//add and remove entities -----------
	
	public void addBullet(Bullet e){
		bullets.add(e);
	}
	public void removeBullet(Bullet e){
		bullets.remove(e);
	}
	
	public void addEnemy(Enemy e){
		enemys.add(e);
	}
	public void removeEnemy(Enemy e){
		enemys.remove(e);
	}
	
	// Graphics -----------------------
	
	public void tick(){
		player.tick();
		if(Physics.collision(player, enemys)){
			game.setGameState(Game.STATE.END);
//			System.out.println("COLLISION HAPPENS BY PLAYER!!!!");
		}
		
		for(int i = 0; i < bullets.size(); i++){
			Bullet b = bullets.get(i);
			b.tick();
			Enemy e;
			if((e = Physics.collisionEnemy(b, enemys)) != null){
				removeBullet(b);
				removeEnemy(e);
				enemyKilled++;
				totalPoint ++;
				if(enemyCount == enemyKilled){
					createEnemy(++enemyCount);
					enemyKilled = 0;
				}
//				System.out.println("COLLISION HAPPENS!!");
			}
			
		}
		for(int i = 0; i < enemys.size(); i++){
			enemys.get(i).tick();
		}
		
	}
	
	public void render(Graphics g){
		player.render(g);
		
		for(int i = 0; i < bullets.size(); i++){
			bullets.get(i).render(g);
		}
		for(int i = 0; i < enemys.size(); i++){
			enemys.get(i).render(g);
		}
	}
}
