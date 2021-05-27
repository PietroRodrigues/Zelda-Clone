package com.CyberGames.Word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.CyberGamesDeveloper.entitis.*;
import com.CyberGamesDeveloper.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int Wmap, Hmap;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			
			Wmap = map.getWidth();
			Hmap = map.getHeight();
			
			tiles = new Tile[map.getWidth() * map.getHeight()];
			
			map.getRGB(0, 0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());
			
			for(int i = 0;i < map.getWidth(); i++) {
				
				for(int y = 0;y < map.getHeight(); y++) {
				
					int corPixel = pixels[i + (y * map.getWidth())];
				
					tiles[i + (y * Wmap)] = new FloorTile(i*16, y*16, Tile.TILE_FLOOR);
				switch (corPixel) {
				case 0xFFFF0000:
					//vermelho Enemy
					Game.entities.add(new Enemy(i*16,y*16,16,16,Entity.Enemy_EN));					
					break;
				case 0xFFFFFFFF:
					//prento chao
					tiles[i + (y * Wmap)] = new FloorTile(i*16, y*16, Tile.TILE_FLOOR);
					break;
				case 0xFF000000:
					//branco Parede
					tiles[i + (y * Wmap)] = new WallTile(i*16, y*16, Tile.TILE_WALL);					
					break;
				case 0xFFFF00C7:
					//rosa life
					Game.entities.add(new LifeTile(i*16,y*16,16,16,Entity.Life_EN));	
					break;
				case 0xFFFFD800:
					//amarelo Bullet
					Game.entities.add(new BulletTile(i*16,y*16,16,16,Entity.Bullet_EN));
					break;
				case 0xFF00FF21:
					//verde Welpom
					Game.entities.add(new LifeTile(i*16,y*16,16,16,Entity.Welpom_EN));
					break;
				case 0xFF0026FF:
					//azul Player
					Game.player.setX(i*16);
					Game.player.setY(y*16);
					break;					
				default:
				
					break;
				}	
				
				}
			}
		
		} catch (IOException e) {		
			e.printStackTrace();
		}
		
	}
	
	public static void render(Graphics g) {
		int xstart = Camera.x / 16;
		int ystart = Camera.y / 16;
		
		int xfinal = xstart + (Game.Width / 16);
		int yfinal = ystart + (Game.Height / 16);
		
		
		for(int i = xstart; i <= xfinal; i++) {
			for(int y = ystart; y <= yfinal; y++) {
				
				if(i < 0 || y < 0 || i >= Wmap || y >= Hmap)
					continue;
				
				Tile tile = tiles[i + (y * Wmap)];
				tile.render(g);					
				
			}
		}
		
	}
	
	

}
