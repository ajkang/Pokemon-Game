import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Pokeball extends Collidable {
	private Image icon;
	private Item item;

	public Pokeball(int x, int y, Item item){
		super(x, y, 23, 23);
		icon = new ImageIcon("item.gif").getImage();
		this.item = item;

	}


	public void drawMe(Graphics g){

		g.drawImage(icon, getX(), getY(), getWidth(), getHeight(), null); //this is the 1st item


	}

	public Item getItem(){
		return item;
	}

}
