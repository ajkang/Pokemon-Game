import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

public class GirlNonPlayer2 extends NonPlayer{

	public GirlNonPlayer2(){
		super(400, 450, 34, 35, "girl", new Dialogue("You wanna have strong pokemon?", "Give me a rare feather.", "I'll give you stuff"), new Dialogue(new Feather(), new Rarecandy(), new String[] {"No rare item? Okay"}, new String[] {"Enjoy!"}));

	}
}
