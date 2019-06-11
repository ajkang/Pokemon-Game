import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pokemon{

	private Image front = null, back = null;
	private Attack[] attacks;
	private final int totalHP = 100;
	private int hp = totalHP;
	private String name;

	public Pokemon(String name, Attack... attacks){
		front = new ImageIcon(name.toLowerCase()+"Front.gif").getImage();
		back = new ImageIcon(name.toLowerCase()+"Back.gif").getImage();
		this.attacks = attacks;
		this.name = name;
	}


	public void drawMe(Graphics g, boolean front, int x, int y, int width, int height){
		for(int i = 0; i < 3; i++){
			g.drawImage(front?this.front:this.back, x, y, width, height, null);
		}

	}

	public String getName(){
		return name;
	}

	public Attack[] getAttacks() {
		return attacks;
	}

	public int getHP() {
		return hp;
	}
	public float getHealthPercent() {
		// returns float from 0 to 1.
		return hp*1f/totalHP;
	}

	public void attack(int damage) {
		hp = Math.max(0, hp - damage);
	}




}
