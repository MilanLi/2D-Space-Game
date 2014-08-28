package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GameObject {
	protected double x;
	protected double y;
	
	protected BufferedImage img;
	protected Game game = null;
	protected BufferedImage[] images = null;
	
	public GameObject(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, img.getWidth(), img.getHeight());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
