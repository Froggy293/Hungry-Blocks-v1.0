package com.likeajosh.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.likeajosh.game.entity.GameObject;
import com.likeajosh.game.window.Debug;

public class HUD {
	public static int HEALTH = 100;
	public static int SCORE = 0;
	private Handler handler;
	public HUD(){
		handler = new Handler();
	}
	public void tick(){
		HEALTH = Game.clamp(HEALTH, 0, 100);
	}
	public void render(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 16);	
		drainHealthBar(g);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 16);
		g.setFont(new Font("Consolas", Font.PLAIN, 14));
		g.drawString("Score: " + SCORE, 15, 50);
		if (HEALTH <= 0) gameOver(g);
	}
	private void drainHealthBar(Graphics g){
		g.setColor(Color.YELLOW);
		//System.out.println("Health: " + HEALTH);
		if (HEALTH <= 100 && HEALTH > 87) g.setColor(Color.GREEN);
		if (HEALTH <= 87 && HEALTH > 75) g.setColor(new Color(160, 255, 0));
		if (HEALTH <= 75 && HEALTH > 63) g.setColor(Color.YELLOW);
		if (HEALTH < 63 && HEALTH > 50) g.setColor(new Color(255, 160, 0));
		if (HEALTH <= 50 && HEALTH > 25) g.setColor(new Color(255, 100, 0));
		if (HEALTH <= 25 && HEALTH > 0) g.setColor(Color.RED);
		g.fillRect(15,  15,  HEALTH * 2,  16);
	}
	private void gameOver(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.BOLD, 30));
		g.drawString("GAME OVER", (Game.WIDTH / 2) - 100, Game.HEIGHT / 2);
		g.setFont(new Font("Consolas", Font.PLAIN, 20));
		g.drawString("Score: " + HUD.SCORE, (Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) + 40);
		
		for (int i = 0; i < handler.object.size(); i++){
			GameObject temp = handler.object.get(i);
			handler.removeObject(temp);
		}
		//new Game().stop();
	}
}
