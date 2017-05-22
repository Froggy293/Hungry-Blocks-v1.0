package com.likeajosh.game.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.likeajosh.game.Game;
import com.likeajosh.game.Handler;
import com.likeajosh.game.entity.GameObject;
import com.likeajosh.game.entity.ID;

public class MouseMotion implements MouseMotionListener{
	private Handler handler;
	public int x, y;
	private String coords;
	public MouseMotion(Handler handler){
		this.handler = handler;
		
		handler = new Handler();
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		try {
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player){
				int dx = Math.abs(x - tempObject.getX());
				int dy = Math.abs(y - tempObject.getY());
				if (x < tempObject.getX()){
					if (dx <= 20) tempObject.setVelX(-1);
					if (dx > 20 && dx <= 40) tempObject.setVelX(-2);
					if (dx > 40 && dx <= 60) tempObject.setVelX(-3);
					if (dx > 60 && dx <= 80) tempObject.setVelX(-4);
					if (dx > 80 && dx <= 100) tempObject.setVelX(-5);
					if (dx > 100 && dx <= Game.WIDTH) tempObject.setVelX(-6);
				}
				if (x > tempObject.getX()){
					if (dx <= 20) tempObject.setVelX(-1);
					if (dx > 20 && dx <= 40) tempObject.setVelX(2);
					if (dx > 40 && dx <= 60) tempObject.setVelX(3);
					if (dx > 60 && dx <= 80) tempObject.setVelX(4);
					if (dx > 80 && dx <= 100) tempObject.setVelX(5);	
					if (dx > 100 && dx <= Game.WIDTH) tempObject.setVelX(6);
				}
				if (y < tempObject.getY()){ 
					if (dy <= 20) tempObject.setVelX(-1);
					if (dy > 20 && dy <= 40) tempObject.setVelY(-2);
					if (dy > 40 && dy <= 60) tempObject.setVelY(-3);
					if (dy > 60 && dy <= 80) tempObject.setVelY(-4);
					if (dy > 80 && dy <= 100) tempObject.setVelY(-5);	
					if (dy > 100 && dy <= Game.HEIGHT) tempObject.setVelY(-6);
				}
				if (y > tempObject.getY()){ 
					if (dy <= 20) tempObject.setVelX(-1);
					if (dy > 20 && dy <= 40) tempObject.setVelY(2);
					if (dy > 40 && dy <= 60) tempObject.setVelY(3);
					if (dy > 60 && dy <= 80) tempObject.setVelY(4);
					if (dy > 80 && dy <= 100) tempObject.setVelY(5);	
					if (dy > 100 && dy <= Game.HEIGHT) tempObject.setVelY(6);
				} if (x == tempObject.getX() || y == tempObject.getY()) {
					tempObject.setVelX(0);
					tempObject.setVelY(0);
				}
			}
		} } catch (NullPointerException ex){
			ex.printStackTrace();
		}
	}
	public void mouseDragged(MouseEvent e) {
		/*x = e.getX();
		y = e.getY();
		//coords = ("(" + x + ", " + y + ")");
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player){
				int dx = Math.abs(x - tempObject.getX());
				int dy = Math.abs(y - tempObject.getY());
				if (x < tempObject.getX()){
					if (dx <= 20) tempObject.setVelX(-1);
					else if (dx > 20 && dx <= 40) tempObject.setVelX(-2);
					else if (dx > 40 && dx <= 60) tempObject.setVelX(-3);
					else if (dx > 60 && dx <= 80) tempObject.setVelX(-4);
					else if (dx > 80 && dx <= 100) tempObject.setVelX(-5);	
					else tempObject.setVelX(-6);
				}
				else if (x > tempObject.getX()){
					if (dx <= 20) tempObject.setVelX(-1);
					else if (dx > 20 && dx <= 40) tempObject.setVelX(2);
					else if (dx > 40 && dx <= 60) tempObject.setVelX(3);
					else if (dx > 60 && dx <= 80) tempObject.setVelX(4);
					else if (dx > 80 && dx <= 100) tempObject.setVelX(5);	
					else tempObject.setVelX(6);
				}
				else if (y < tempObject.getY()){ 
					if (dy <= 20) tempObject.setVelX(-1);
					else if (dy > 20 && dy <= 40) tempObject.setVelY(-2);
					else if (dy > 40 && dy <= 60) tempObject.setVelY(-3);
					else if (dy > 60 && dy <= 80) tempObject.setVelY(-4);
					else if (dy > 80 && dy <= 100) tempObject.setVelY(-5);	
					else tempObject.setVelY(-6);
				}
				else if (y >= tempObject.getY()){ 
					if (dy <= 20) tempObject.setVelX(-1);
					else if (dy > 20 && dy <= 40) tempObject.setVelY(2);
					else if (dy > 40 && dy <= 60) tempObject.setVelY(3);
					else if (dy > 60 && dy <= 80) tempObject.setVelY(4);
					else if (dy > 80 && dy <= 100) tempObject.setVelY(5);	
					else tempObject.setVelY(6);
				} else {
					tempObject.setVelX(0);
					tempObject.setVelY(0);
				}
			}
		}*/
	}

	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

}
