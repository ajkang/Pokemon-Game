import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Backpack{
	public BufferedImage bagBG, itemBox;
	public Backpack(){
		try {
			bagBG = ImageIO.read(new File("bagbg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			itemBox = ImageIO.read(new File("itembox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void drawMe(Graphics g){
		g.drawImage(bagBG, 0, 0, 800, 600, null);
		//boxes go from left to right && up to down = order
		g.drawImage(itemBox, 395, 70, 100, 100, null);
		g.drawImage(itemBox, 515, 70, 100, 100, null);
		g.drawImage(itemBox, 635, 70, 100, 100, null);
		g.drawImage(itemBox, 395, 190, 100, 100, null);
		g.drawImage(itemBox, 515, 190, 100, 100, null);
		g.drawImage(itemBox, 635, 190, 100, 100, null);
		g.drawImage(itemBox, 395, 310, 100, 100, null);
		g.drawImage(itemBox, 515, 310, 100, 100, null);
		g.drawImage(itemBox, 635, 310, 100, 100, null);
		g.drawImage(itemBox, 395, 430, 100, 100, null);
		g.drawImage(itemBox, 515, 430, 100, 100, null);
		g.drawImage(itemBox, 635, 430, 100, 100, null);


	}
}
