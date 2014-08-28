package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 960;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 2;
	public final String TITLE = "Space Game";
	
	private boolean running = false;
	private Thread thread;
	JFrame frame = null;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage[] playerImages = null;
	private BufferedImage[] bulletImages = null;
	private BufferedImage[] enemyImages = null;
	
	private Player p;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	
	
	public Controller controller;
	public boolean isShooting = false;
	
	private STATE gameState = STATE.MENU;
	private Menu menu;
	private End end;
	
	public static enum STATE{
		MENU,
		GAME,
		END
	};
	
	
	public Game(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame(TITLE);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		playerImages = new BufferedImage[4];
		bulletImages = new BufferedImage[4];
		enemyImages = new BufferedImage[10];
		
		

	}
	
	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	

	
	private void init(){
		this.requestFocus();
		BufferedImageLoader bl = new BufferedImageLoader();
		try {
			for(int i = 0; i < 4; i++){
				playerImages[i] = bl.loadImage("res/"+i+".png");
			}
			
			for(int i = 0; i < 2; i++){
				bulletImages[i] = bl.loadImage("res/bullet"+i+".png");
			}
			
			for(int i = 0; i < 10; i++){
				enemyImages[i] = bl.loadImage("res/alien"+i+".png");
			}
			
//			player = bl.loadImage("res/1.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		menu = new Menu(this);
		end = new End(this);
		
		p = new Player(this.WIDTH/2, this.HEIGHT*2/3, this);
		keyInput = new KeyInput(this, p);
		mouseInput = new MouseInput(this);
		
		
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		controller = new Controller(this, p);
		
	}
	
	//when thread is constructed, it calls run() automatically in Runnable class 
	//which it points to .
	public void run() {
		init();
		double amountOfTicks = 60.0;
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000/amountOfTicks;
		double delta = 0.0;
		
		int UPS = 0;
		int FPS = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			while(delta > 1){
				tick();
				delta -= 1;
				UPS++;
			}
			FPS++;
			render();
			
			
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				System.out.println("UPS: "+UPS+" FPS: "+FPS);
				UPS = 0;
				FPS = 0;
			}
			
		}
		
		stop();
		
	}
	
	private void tick(){
		if(gameState == STATE.GAME){
			controller.tick();

		}
		else if(gameState == STATE.MENU){
			menu.tick();
		}
		else if(gameState == STATE.END){
			end.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		if(gameState == STATE.GAME){
			controller.render(g);
		}
		else if(gameState == STATE.MENU){
			menu.render(g);
		}
		else if(gameState == STATE.END){
			end.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public BufferedImage[] getPlayerImages(){
		return playerImages;
	}
	
	public BufferedImage[] getBulletImages() {
		return bulletImages;
	}
	
	public BufferedImage[] getEnemyImages(){
		return enemyImages;
	}
	
	
	
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}
	
	public STATE getGameState(){
		return gameState;
	}
	
	public void setGameState(STATE s){
		gameState = s;
	}

	public Menu getMenu(){
		return menu;
	}
	

	
}
