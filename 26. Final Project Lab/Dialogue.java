import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Font;

import java.util.ArrayList;

public class Dialogue{
	private BufferedImage dialogueBox, playerDialogueImage, girlDialogue, guyDialogue, finalPerson;
	private String[] dialogue, noItemDialogue, withItemDialogue;
	private int index;
	private Item reqItem, toGive;

	private int dialogueIndex;
	private NonPlayer nonPlayer;

	public Dialogue(String... dialogue){
		try {
			dialogueBox = ImageIO.read(new File("textBox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			playerDialogueImage = ImageIO.read(new File("pokemonDialogueImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			girlDialogue = ImageIO.read(new File("girlDialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			guyDialogue = ImageIO.read(new File("guyDialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			finalPerson = ImageIO.read(new File("finalNonPlayerDialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.withItemDialogue = dialogue;


	}

	public Dialogue(Item reqItem, Item toGive, String[] noItemDialogue, String[] withItemDialogue){
		try {
			dialogueBox = ImageIO.read(new File("textBox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			playerDialogueImage = ImageIO.read(new File("pokemonDialogueImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			girlDialogue = ImageIO.read(new File("girlDialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			guyDialogue = ImageIO.read(new File("guyDialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			finalPerson = ImageIO.read(new File("finalNonPlayerDialogue.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.dialogue = dialogue;
		this.reqItem = reqItem;
		this.toGive = toGive;
		this.noItemDialogue = noItemDialogue;
		this.withItemDialogue = withItemDialogue;
		//System.out.println(dialogue.length+" lines");


	}



	public void drawMe(Graphics g, String name){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		g.drawImage(dialogueBox, 200, 400, 600, 200, null);
		g.drawImage(playerDialogueImage, 10, 325, 180, 275, null);
		g.setColor(Color.BLACK);
		if(!finished()){
			g.drawString(dialogue[index], 225, 450);
			//TODO find a way to fins which nonPlayer name it is
			if(name.equals("guy")){
				g.drawImage(guyDialogue, 350, 105, 170, 275, null);

			}else if(name.equals("girl")){
				g.drawImage(girlDialogue, 300, 105, 170, 275, null);
			}else if(name.equals("finalNonPlayer")){
				g.drawImage(finalPerson, 300, 105, 170, 275, null);
			}

		}

	}

	public boolean start(ArrayList<Item> items){
		if(finished()){
			int found = -1;
			if(reqItem != null) {
				for(int i = 0; i < items.size(); i++) {
					if(items.get(i).getClass() == reqItem.getClass()) {
						found = i;
						break;
					}
				}
			}
			if(found >= 0 || reqItem == null) {
				dialogue = withItemDialogue;
				if(reqItem != null)
					items.remove(found);
				if(toGive != null)
					items.add(toGive);
			}else{
				dialogue = noItemDialogue;
			}
			index = 0;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean changeDialogue(){
		if(!finished()){
			index++;
		}

		return !finished();
	}

	public boolean finished() { return finished(true); }
	public boolean finished(boolean includeNotStarted){
		if(dialogue == null) {
			return includeNotStarted;
		}
		else {
			return index >= dialogue.length;
		}
	}

}
