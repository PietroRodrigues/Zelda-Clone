package com.CyberGamesDeveloper.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private BufferedImage spritesheet;
	
	public SpriteSheet(String path) {
		
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage GetSprite(int x, int y, int w, int h) {
		
		return spritesheet.getSubimage(x, y, w, h);
		
	}
	

}
