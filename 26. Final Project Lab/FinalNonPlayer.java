import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public class FinalNonPlayer extends NonPlayer{

	public FinalNonPlayer(){
		super(385, 210, 31, 33, "finalNonPlayer", new Dialogue("I am the champion.", "Lets see if you prepared.", "I won't go easy!"), new Dialogue(new Feather(), new Rarecandy(), new String[] {"See I told you.", "You can't beat me!"}, new String[] {"Well congratulations..."}));

	}
}
