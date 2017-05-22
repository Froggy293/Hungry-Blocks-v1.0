package com.likeajosh.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.likeajosh.game.Game;
import com.likeajosh.game.Handler;

public class Food extends GameObject{
	
	private Handler handler;
	private Random rand;
	
	public Food(int x, int y, ID id) {
		super(x, y, id);
		
		rand = new Random();
		
		velX = rand.nextInt(8) - 4;
		velY = rand.nextInt(8) - 4;
		
		handler =  new Handler();
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.WIDTH - 38) velX = -velX;
		if (y <= 0 || y >= Game.HEIGHT - 83) velY = -velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 10, 10);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}

}
