package com.CyberGamesDeveloper.entitis;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.CyberGames.Word.Camera;
import com.CyberGamesDeveloper.main.Game;

public class Player extends Entity {

	public boolean right,left,up,down;
	public char animationDirection = 'r';
	public double speed = 1;
	
	private boolean mover;
	private int frames = 0;
	private int indc = 0;
	private BufferedImage[] rPlayer;
	private BufferedImage[] lPlayer;
		
	
	public Player(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
						
		rPlayer = new BufferedImage[4];
		lPlayer = new BufferedImage[4];
		
		

		for(int i = 0; i < 4;i++) {
			rPlayer[i] = Game.spriteSheet.GetSprite(32 + (i*16), 0, 16, 16);
		}
		
		for(int i = 0; i < 4;i++) {
			lPlayer[i] = Game.spriteSheet.GetSprite(32 + (i*16), 16, 16, 16);
		}
		
		
		
	}
	
	public void Tick() {
		
		mover = false;
		
		if(right) {
			mover = true;
			x += speed;
			animationDirection = 'r';

			
		}else if(left) {
			mover = true;
			x -= speed;
			animationDirection = 'l';
			
		}

		if(up) {
			mover = true;
			y -= speed;
			Camera.y += speed;
		}else if(down) {
			mover = true;
			y += speed;
			
		}		
		
		
		if(mover) {
			frames++;
			if(frames == 5) {
				frames = 0;
				indc++;
				if(indc > 3) {
					indc = 0;
				}
			}
		}
		
		Camera.x = this.getX() - (Game.Width/2);
		Camera.y = this.getY() - (Game.Height/2);
		
	}
	
	public void render(Graphics g) {
		switch (animationDirection) {
		case 'r' :
			g.drawImage(rPlayer[indc],this.getX() - Camera.x,this.getY() - Camera.y,null);
			break;
		case 'l' :
			g.drawImage(lPlayer[indc],this.getX() - Camera.x,this.getY() - Camera.y,null);
		break;			
		default:
			break;
			
		}
		
	}
	
	
}
