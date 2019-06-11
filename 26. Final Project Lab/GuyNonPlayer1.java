import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public class GuyNonPlayer1 extends NonPlayer{

	public GuyNonPlayer1(){
		super(215, 200, 28, 29, "guy", new Dialogue("What do you want?  (n)", "You don't have any Pokemon?", "Here have this one."));

	}
}
