import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Collidable {
	private BufferedImage player;


	public Player(int x, int y, int width, int height){
		super(x, y, width, height);

		try {
			player = ImageIO.read(new File("trainer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawMe(Graphics g){
		g.drawImage(player, getX(), getY(), getWidth(), getHeight(), null);
	}

}
