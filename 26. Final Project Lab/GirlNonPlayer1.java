import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public class GirlNonPlayer1 extends NonPlayer{

	public GirlNonPlayer1(){
		super(520, 80, 34, 35, "girl", new Dialogue("Hi!  (n)", "Are you lost?"));

	}
}
