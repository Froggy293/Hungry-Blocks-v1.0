package com.likeajosh.game.window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.likeajosh.game.Game;
import com.likeajosh.game.HUD;
import com.likeajosh.game.Handler;
import com.likeajosh.game.entity.GameObject;
import com.likeajosh.game.entity.ID;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player){
				if (key == KeyEvent.VK_W) tempObject.setVelY(-5);
				if (key == KeyEvent.VK_S) tempObject.setVelY(5);
				if (key == KeyEvent.VK_D) tempObject.setVelX(5);
				if (key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if (key == KeyEvent.VK_F1) System.out.println(
						"[Debug Stats]\n" +
						"FPS: " + Game.fps +
						"\nObjects: " + handler.object.size() +  
						"\nEnemies: " + enemies() +
						"\nHealth: " + HUD.HEALTH + "\n");
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player){
				if (key == KeyEvent.VK_W) tempObject.setVelY(0);
				if (key == KeyEvent.VK_S) tempObject.setVelY(0);
				if (key == KeyEvent.VK_D) tempObject.setVelX(0);
				if (key == KeyEvent.VK_A) tempObject.setVelX(0);
			}
		}
	}
	private int enemies(){
		int enemies = 0;
		for (int i = 0; i < handler.object.size(); i++){
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ID.BasicEnemy){
				enemies++;
			}
		}
		return enemies;
	}
}
