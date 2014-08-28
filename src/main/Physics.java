package main;

import java.awt.Rectangle;
import java.util.LinkedList;

public class Physics {
	public static boolean collision(Friendly f, LinkedList<Enemy> e){
		Rectangle fr = f.getBounds();
		for(int i = 0; i < e.size(); i++){
			Rectangle er = e.get(i).getBounds();
			if(fr.intersects(er)){
				return true;
			}
		}
		
		return false;
	}
	
	public static Enemy collisionEnemy(Friendly f, LinkedList<Enemy> e){
		Rectangle fr = f.getBounds();
		for(int i = 0; i < e.size(); i++){
			Rectangle er = e.get(i).getBounds();
			if(fr.intersects(er)){
				return e.get(i);
			}
		}
		
		return null;
	}
}
