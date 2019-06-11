import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public class GuyNonPlayer2 extends NonPlayer{

	public GuyNonPlayer2(){
		super(390, 210, 28, 29, "guy", new Dialogue("I really need a potion!"), new Dialogue(new Potion(), null, new String[]{"I need a potion..."}, new String[]{"Thank you so much"}));

	}
}
