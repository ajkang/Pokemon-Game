import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Item{
	private BufferedImage icon;
	private int x, y;

	public Item(String name, int x, int y){
		this.x = x;
		this.y = y;
		try {
			icon = ImageIO.read(new File(name+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void drawMe(Graphics g){
		//System.out.println("drawing item "+ this);
		g.drawImage(icon, x, y, 50, 50, null);

	}
}
