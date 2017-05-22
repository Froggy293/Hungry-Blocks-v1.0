package com.likeajosh.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.likeajosh.game.Game;
import com.likeajosh.game.Handler;

public class BasicEnemy extends GameObject {
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 5;
		velY = 5;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.WIDTH - 32) velX = -velX;
		if (y <= 0 || y >= Game.HEIGHT - 64) velY = -velY;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.1f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 16, 16);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
