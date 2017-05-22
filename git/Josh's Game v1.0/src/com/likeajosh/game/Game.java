package com.likeajosh.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.likeajosh.game.entity.BasicEnemy;
import com.likeajosh.game.entity.Food;
import com.likeajosh.game.entity.GameObject;
import com.likeajosh.game.entity.ID;
import com.likeajosh.game.entity.Player;
import com.likeajosh.game.window.Debug;
import com.likeajosh.game.window.KeyInput;
import com.likeajosh.game.window.MouseMotion;
import com.likeajosh.game.window.Window;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random rand;
    private HUD hud;
    public static Player player1;
    public Food food;
    public static int fps;
     
    public Game(){
        handler = new Handler();
        hud = new HUD();
        
        new Window(WIDTH, HEIGHT, "Josh's Game v1.2", this);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseMotionListener(new MouseMotion(handler));
        
        rand = new Random();
        player1 = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler);
        food = new Food(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), ID.Food);
        handler.addObject(player1);
        handler.addObject(food);
        handler.addObject(new BasicEnemy(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), ID.BasicEnemy, handler));
    }
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000.0 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){ render(); }
            frames++;
            if (System.currentTimeMillis() - timer > 1000){
            	if (frames > 60) frames = 60;
            	fps = frames;
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }
    private void tick(){
        handler.tick();
        hud.tick();
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        handler.render(g);
        hud.render(g);
        
        g.dispose();
        bs.show();
    }
    public static int clamp(int var, int min, int max){
        if (var >= max) 
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }
    public static void main(String[] args){
        new Game();
    }
}