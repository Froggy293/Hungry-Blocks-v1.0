package com.likeajosh.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.likeajosh.game.Game;
import com.likeajosh.game.HUD;
import com.likeajosh.game.Handler;
import com.likeajosh.game.window.Debug;

public class Player extends GameObject {

	private Random rand;
	private Handler handler;
	private int objects, enemies;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		rand = new Random();
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 83);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.WHITE, 32, 32, 0.1f, handler));
		
		collision();
		
		int enemies = 0;
		setObjects(handler.object.size());
		for (GameObject G: handler.object) { if (G.getId() == ID.BasicEnemy) enemies++; }
		this.setEnemies(enemies);
	}
	
	public void collision(){
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy){
				if (tempObject.getBounds().intersects(getBounds())){
					HUD.HEALTH -= 2;
				}
			} else if (tempObject.getId() == ID.Food){
				if (tempObject.getBounds().intersects(getBounds())){
					HUD.SCORE++;
					if (HUD.HEALTH <= 85){
						HUD.HEALTH += 15;
					} else {
						HUD.HEALTH = 100;
					}
					int x1 = Game.clamp(rand.nextInt(Game.WIDTH - 38), 0, Game.WIDTH - 38);
					int y1 = Game.clamp(rand.nextInt(Game.HEIGHT - 83), 0, Game.HEIGHT - 83);
					
					//Generate new food
					handler.removeObject(tempObject);
					handler.addObject(new Food(x1, y1, ID.Food));
					
					//Generate new basic enemy
					handler.addObject(new BasicEnemy(x1, y1, ID.BasicEnemy, handler));
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
	}

	public int getObjects() {
		return objects;
	}

	public void setObjects(int objects) {
		this.objects = objects;
	}

	public int getEnemies() {
		return enemies;
	}

	public void setEnemies(int enemies) {
		this.enemies = enemies;
	}

}