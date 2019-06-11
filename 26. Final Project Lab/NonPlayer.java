import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class NonPlayer extends Collidable {
	private BufferedImage nonPlayer;
	public boolean touchingEnemy;
	private Dialogue firstDialogue, afterDialogue;
	private Dialogue curDialogue;
	private String naming;

	public NonPlayer(int x, int y, int width, int height, String name, Dialogue dialogue) {
		this(x, y, width, height, name, dialogue, null);

		naming = name;

	}
	public NonPlayer(int x, int y, int width, int height, String name, Dialogue firstDialogue, Dialogue afterDialogue){
		super(x, y, width, height);

		this.firstDialogue = firstDialogue;
		this.afterDialogue = afterDialogue;
		curDialogue = firstDialogue;

		try {
			nonPlayer = ImageIO.read(new File(name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		naming = name;

	}


	public void drawMe(Graphics g){
		g.drawImage(nonPlayer, getX(), getY(), getWidth(), getHeight(), null);

	}

	public Dialogue getNextDialogue() {
		if(afterDialogue != null && firstDialogue.finished(false)) {
			curDialogue = afterDialogue;
			//System.out.println("advancing to second dialogue");
		}
		return curDialogue;
	}
	public Dialogue getDialogue(){
		return curDialogue;
	}

	public String getName(){
		//System.out.println("return" + naming);
		return naming;

	}


}
