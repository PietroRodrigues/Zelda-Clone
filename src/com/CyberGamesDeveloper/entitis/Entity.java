package com.CyberGamesDeveloper.entitis;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.CyberGames.Word.Camera;
import com.CyberGamesDeveloper.main.Game;

public class Entity {

	public static BufferedImage Life_EN = Game.spriteSheet.GetSprite(6*16, 0, 16, 16); 
	public static BufferedImage Welpom_EN = Game.spriteSheet.GetSprite(7*16, 0, 16, 16);
	public static BufferedImage Bullet_EN = Game.spriteSheet.GetSprite(6*16, 16, 16, 16);
	public static BufferedImage Enemy_EN = Game.spriteSheet.GetSprite(7*16, 16, 16, 16);
	
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	private BufferedImage sprite;
	
	protected Entity(int x,int y,int w,int h,BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.sprite = sprite;
		
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}

	public int getY() {
		return (int)this.y;
	}

	public int getW() {
		return this.w;
	}

	public int getH() {
		return this.h;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

		public void Tick() {
		
	}
	
}
