package com.CyberGamesDeveloper.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.CyberGames.Word.World;
import com.CyberGamesDeveloper.entitis.Entity;
import com.CyberGamesDeveloper.entitis.Player;
import com.CyberGamesDeveloper.graficos.SpriteSheet;

public class Game  extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static int Width = 260;
	public static int Height = 180;
	public final int Scale = 3;
	public static Player player;
	
	private BufferedImage Image;
	
	public static List<Entity> entities;
	public static SpriteSheet spriteSheet;
	
	public static World world;
		
	public Game() {
				
		addKeyListener(this);
		setPreferredSize(new Dimension(Width*Scale,Height*Scale));
		InitFrame();
		//inicializando OBJs		
		
		Image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spriteSheet = new SpriteSheet("/spriteSheat.png");
		player = new Player(0,0,16,16,spriteSheet.GetSprite(32, 0, 16, 16));
		entities.add(player);
		world = new World("/map.png");
		
	}
	
	public void InitFrame() {
		
		frame = new JFrame("Game 1");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public synchronized void Start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
 	}
	
	public synchronized void Stop() {

		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String args[]){		
		Game game = new Game();	
		game.Start();
	}
	
	public void logica() {
		for(int i = 0;i < entities.size();i++) {
			Entity e = entities.get(i);
			e.Tick();
		}				
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = Image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);
		//g.setFont(new Font("Arial",Font.BOLD,16));
		//g.setColor(Color.white);
		//g.drawString("Olá", 19, 19);
		
		/*Renderização do jogo*/
		
		//Graphics2D g2 = (Graphics2D) g;
		World.render(g);
		for(int i = 0;i < entities.size();i++) {
			Entity e = entities.get(i);
			e.render(g);
		}	
		
		
				
		/**/
		
		g.dispose();
		g = bs.getDrawGraphics();	
		g.drawImage(Image, 0, 0, Width*Scale,Height*Scale,null);
		bs.show();
		
	}	
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		//Variaveis so para ver quantos frames o game roda
		
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		
		while(isRunning) {		
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				
				logica();
				render();
				
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		
		Stop();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
