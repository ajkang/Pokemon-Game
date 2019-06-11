import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

public class Screen extends JPanel implements KeyListener{

	private Image startMenu, startMenu2, winScreen;
	private BufferedImage instructionsbg, quest1bg, quest2bg, quest3bg, battlebg, losebg;
	private int changeScene;
	private int whichPerson; //this is to see who you are talking to
	private int currentScene;
	private Player player;
	private Dialogue dialogue;
	private Backpack backpack;
	private Battle battle;

	private ArrayList<Pokeball> groundItems;
	private ArrayList<Item> bagItems;
	private ArrayList<NonPlayer> nonPlayers;

	private boolean isTalking;
	private boolean doneTalking, showPokemon;
	private int whoIsTalking;

	private String person;
	private Pokemon pokemon;
	private Pokemon enemyPokemon;
	private int pokemonLevel;

	public Screen(){
		bagItems = new ArrayList<Item>();
		groundItems = new ArrayList<Pokeball>();
		groundItems.add(new Pokeball(140, 299, new Potion()));
		groundItems.add(new Pokeball(390, 110, new Feather()));

		nonPlayers = new ArrayList<NonPlayer>();
		nonPlayers.add(new GirlNonPlayer1());
		nonPlayers.add(new GuyNonPlayer1());


		startMenu = new ImageIcon("start.gif").getImage();
		startMenu2 = new ImageIcon("startScreen.gif").getImage();
		winScreen = new ImageIcon("pikachuBalloon.gif").getImage();



		try {
			instructionsbg = ImageIO.read(new File("instructionsbg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			quest1bg = ImageIO.read(new File("quest1bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			quest2bg = ImageIO.read(new File("quest2bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			quest3bg = ImageIO.read(new File("quest3bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			battlebg = ImageIO.read(new File("battlebg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			losebg = ImageIO.read(new File("losebg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//changeScene = 1000;

		player = new Player(0, 200, 28, 30);

		backpack = new Backpack();

		isTalking = false;
		whoIsTalking = 0;
		doneTalking = false;

		pokemon = new Pikachu();
		enemyPokemon = new Pokemon("Electabuzz", new Attack("Thunderbolt", 30), new Attack("Scratch", 10), new Attack("Fire Punch", 20), new Attack("Ice Punch", 20));

		showPokemon = false;

		pokemonLevel = 50;


		addKeyListener(this);
        setFocusable(true);

	}


	public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }

	public void setNewItemsNonPlayers(int scene){
		if(scene == 4){
			groundItems.add(new Pokeball(600, 400, new HyperPotion()));
			groundItems.add(new Pokeball(210, 550, new FullRestore()));

			nonPlayers.add(new GirlNonPlayer2());
			nonPlayers.add(new GuyNonPlayer2());
		}
		if(scene == 5){
			groundItems.add(new Pokeball(280, 500, new Bell()));
			groundItems.add(new Pokeball(400, 500, new ParlyzHeal()));
		}
		if(scene == 6){
			nonPlayers.add(new FinalNonPlayer());
		}
	}

	public void reset(){
		isTalking = false;
		doneTalking = false;
		whoIsTalking = 0;

		nonPlayers.clear();
		groundItems.clear();

		setNewItemsNonPlayers(changeScene);
	}

	public void talkingToPerson(boolean isColliding){
		if(isColliding == true){
			Sound.talkingToSomeone();
		}

	}



	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Color sky = new Color(204, 248, 255);
		Font t = new Font("Pokemon GB", Font.PLAIN, 50); //larger final title screen text
		Font f = new Font("Pokemon GB", Font.PLAIN, 20); //smaller title and instructions screen text
		Font s = new Font("Pokemon GB", Font.BOLD, 18); //start button text



		if(changeScene == 0){
			//gif start (press s to go to the start menu)
			g.drawImage(startMenu, 0, 0, 800, 600, null);
			g.setFont(s);
			g.setColor(Color.YELLOW);
			g.drawString("Press S for Start Menu", 220, 550);
		}else if(changeScene == 1){
			//actual start (press s to go to instructions)
			g.drawImage(startMenu2, 0, 0, 800, 600, null);
			g.setFont(s);
			g.setColor(Color.BLACK);
			g.drawString("Press S to Start", 260, 510);
		}else if(changeScene == 2){
			//instructions
			g.drawImage(instructionsbg, 0, 0, 800, 600, null);
			g.setFont(t);
			g.setColor(Color.BLACK);
			g.drawString("Instructions", 90, 55);
			g.setFont(f);

			g.drawString("You turned old enough to have pokemon!", 0, 100);
			g.drawString("You want to get your pokemon stronger to", 0, 140);
			g.drawString("battle and defeat the regional champion.", 0, 180);
			g.drawString("Your objective is to talk to people & ", 0, 220);
			g.drawString("get items to prepare for your battle.", 0, 260);

			g.drawString("MOVE: use the up, down, right, left keys", 0, 310);
			g.drawString("BAG: press the b key", 0, 340);
			g.drawString("POKEMON: press the 1 key", 0, 370);

			g.drawString("Q1: Get a Pokemon & Items", 0, 430);
			g.drawString("Q2: Trade/Give Items", 0, 460);
			g.drawString("Q3: Battle Champion", 0, 490);

			g.drawString("DON'T WALK ON THE BROWN LEDGES & TREES!", 5, 570);


		}
		else if(changeScene == 3){
			//this is QUEST 1 (Just talk to people to get a pokemon and item)
			g.drawImage(quest1bg, 0, 0, 800, 600, null);
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("Quest 1", 630, 50);

			g.setColor(Color.YELLOW);
			g.fillRect(745, 70, 50, 50);

			for(int i = 0; i < nonPlayers.size(); i++){
				nonPlayers.get(i).drawMe(g);
			}

			for(int i = 0; i < groundItems.size(); i++){
				groundItems.get(i).drawMe(g);
			}

			player.drawMe(g);


			for(int i = 0; i < nonPlayers.size(); i++){
				if(nonPlayers.get(i).checkCollision(player) == true){
					//System.out.println("touching");
					if(nonPlayers.get(1).checkCollision(player) == true){
						showPokemon = true;
					}
					if(doneTalking == false) {
						if(isTalking && nonPlayers.get(i).getDialogue().start(bagItems) == true || !isTalking && nonPlayers.get(i).getNextDialogue().start(bagItems) == true){
							isTalking = true;
							whoIsTalking = i;
							talkingToPerson(nonPlayers.get(i).checkCollision(player));


						}
					}

				}else if(i == whoIsTalking){
					//System.out.println("notTouching");
					if(isTalking == true){
						doneTalking = false;
						//System.out.println("reset");
					}

					isTalking = false;
				}


				for(int j = 0; j < groundItems.size(); j++){
					if(groundItems.get(j).checkCollision(player) == true){
						Sound.itemCollect();
						Pokeball sub = groundItems.get(j);
						groundItems.remove(j);
						bagItems.add(sub.getItem());
						j--;
					}
				}
			}

			if(doneTalking == false && isTalking == true){
				g.setFont(f);
				String name = nonPlayers.get(whoIsTalking).getName();
				nonPlayers.get(whoIsTalking).getDialogue().drawMe(g, name);
			}

			boolean done = true;
			for(int i = 0; i < nonPlayers.size(); i++){
				if(nonPlayers.get(i).getDialogue().finished() == false){
					done = false;

					break;
				}
			}

			if(done == true) {
				if(player.getX() > 700 && player.getY() < 100){
					changeScene = 4;
					Sound.questComplete();
					reset();
					player.setPosition(210, 225);
				}
			}



		}else if(changeScene == 4){
			//this is QUEST 2
			//remove nonPlayers
			//add new Non Player at a new position

			g.drawImage(quest2bg, 0, 0, 800, 600, null);
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("Quest 2", 630, 50);

			g.setColor(Color.YELLOW);
			g.fillRect(700, 525, 50, 50);


			for(int i = 0; i < nonPlayers.size(); i++){
				nonPlayers.get(i).drawMe(g);
			}

			for(int i = 0; i < groundItems.size(); i++){
				groundItems.get(i).drawMe(g);
			}

			player.drawMe(g);


			for(int i = 0; i < nonPlayers.size(); i++){
				if(nonPlayers.get(i).checkCollision(player) == true){


					if(doneTalking == false) {
						if(isTalking && nonPlayers.get(i).getDialogue().start(bagItems) == true || !isTalking && nonPlayers.get(i).getNextDialogue().start(bagItems) == true){
							isTalking = true;
							talkingToPerson(nonPlayers.get(i).checkCollision(player));
							whoIsTalking = i;


						}
					}

				}else if(i == whoIsTalking){
					//System.out.println("notTouching");
					if(isTalking == true){
						doneTalking = false;
						//System.out.println("reset");
					}

					isTalking = false;
				}


				for(int j = 0; j < groundItems.size(); j++){
					//System.out.println(groundItems.get(i).checkCollision(player));
					if(groundItems.get(j).checkCollision(player) == true){
						Sound.itemCollect();
						bagItems.add(groundItems.remove(j).getItem());
						j--;
					}
				}
			}

			if(doneTalking == false && isTalking == true){
				g.setFont(f);
				String name = nonPlayers.get(whoIsTalking).getName();
				nonPlayers.get(whoIsTalking).getDialogue().drawMe(g, name);
			}

			boolean done = true;
			for(int i = 0; i < nonPlayers.size(); i++){
				if(nonPlayers.get(i).getDialogue().finished() == false){
					done = false;
					break;
				}
			}


			if(done == true) {
				if(player.getX() > 700 && player.getY() < 600){
					changeScene = 5;
					player.setPosition(30, 550);
					Sound.questComplete();
					reset();
				}
			}


		}else if(changeScene == 5){
			//this is QUEST 3
			g.drawImage(quest3bg, 0, 0, 800, 600, null);
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("Quest 3", 630, 50);

			for(int i = 0; i < groundItems.size(); i++){
				groundItems.get(i).drawMe(g);
			}

			g.setColor(Color.BLACK);
			g.fillRect(438, 235, 45, 65);

			player.drawMe(g);

			for(int j = 0; j < groundItems.size(); j++){
				//System.out.println(groundItems.get(i).checkCollision(player));
				if(groundItems.get(j).checkCollision(player) == true){
					Sound.itemCollect();
					bagItems.add(groundItems.remove(j).getItem());
					j--;
				}
			}

			boolean touchingCaveDoor = false;
			if(player.getX() < 484 && player.getY() < 280 && player.getX() > 437 && player.getY() > 234){
				touchingCaveDoor = true;
			}

			boolean done = false;
			if(groundItems.size() == 0){
				done = true;
			}

			if(done == true) {
				if(touchingCaveDoor == true){
					player.setPosition(385, 550);
					changeScene = 6;
					reset();
				}
			}




		}else if(changeScene == 6){
			//this is the fight area
			g.setColor(Color.WHITE);
			g.drawString("Quest 3", 630, 50);
			g.drawImage(battlebg, 0, 0, 800, 600, null);


			player.drawMe(g);

			for(int i = 0; i < nonPlayers.size(); i++){
				nonPlayers.get(i).drawMe(g);
			}

			for(int i = 0; i < nonPlayers.size(); i++){
				if(nonPlayers.get(i).checkCollision(player) == true){
					//System.out.println("touching");

					if(doneTalking == false) {
						if(isTalking && nonPlayers.get(i).getDialogue().start(bagItems) == true || !isTalking && nonPlayers.get(i).getNextDialogue().start(bagItems) == true){
							isTalking = true;
							whoIsTalking = i;
							talkingToPerson(nonPlayers.get(i).checkCollision(player));

						}
					}

				}else if(i == whoIsTalking){
					//System.out.println("notTouching");
					if(isTalking == true){
						doneTalking = false;
						//System.out.println("reset");
					}

					isTalking = false;
				}

			}

			if(doneTalking == false && isTalking == true){
				g.setFont(f);
				String name = nonPlayers.get(whoIsTalking).getName();
				nonPlayers.get(whoIsTalking).getDialogue().drawMe(g, name);
			}

			boolean done = true;
			for(int i = 0; i < nonPlayers.size(); i++){
				if(nonPlayers.get(i).getDialogue().finished() == false){
					done = false;

					break;
				}
			}




		}else if(changeScene == 7){
			//win pikachu
			g.setColor(sky);
			g.fillRect(0, 0, 800, 600);
			g.drawImage(winScreen, 0, 0, 800, 600, null);
			g.drawImage(winScreen, 0, 0, 800, 600, null);
			g.drawImage(winScreen, 0, 0, 800, 600, null);
			g.setFont(t);
			g.setColor(Color.BLACK);
			g.drawString("Congratulations!", 5, 460);
			g.setFont(f);
			g.drawString("You are the champion of the region!", 50, 550);
		}else if(changeScene == 100){
			//this is the backpack setting
			backpack.drawMe(g);
			for(int i = 0; i < bagItems.size(); i++){
				bagItems.get(i).drawMe(g);

			}


		}else if(changeScene == 101){
			//pokemon in here
			//need to make a pokemon class
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);

			//TODO make a boolean that will change when the 1st person gives them a pokemon
			if(showPokemon == true){
				//System.out.println("hi");
				pokemon.drawMe(g, true, 310, 50, 130, 130);
				g.setFont(f);
				g.setColor(Color.BLACK);
				g.drawString("PIKACHU", 310, 300);
				g.drawString("Level: " + pokemonLevel, 295, 400);
			}



		}else if(changeScene == 1000){
			//battle scene
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 800, 600);
			battle.drawMe(g);

			if(battle.playerWin()){
				//System.out.println("player win");
				changeScene = 7;
				//battle = null;
			}
			if(battle.enemyWin()){
				System.out.println("enemy win");
				changeScene = 200;
				//battle = null;
			}

		}else if(changeScene == 200){
			//loser screen
			g.drawImage(losebg, 0, 0, 800, 600, null);
			g.setFont(f);
			g.drawString("Try Again? Press r", 200, 500);
		}
	}


	public void animate(){
		while(true){
			try {
	                Thread.sleep(10);
	            } catch(InterruptedException ex) {
	                Thread.currentThread().interrupt();
	            }
				repaint();
		}
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == 83){ // if you press s you switch the scene
			if(changeScene <= 2){
				changeScene++;
			}
		}

		if(battle == null) {
			if(e.getKeyCode() == 66){ //if you press b you switch to backpack

				if(changeScene == 100){
					changeScene = currentScene;
				}
				else if(changeScene == 3 || changeScene == 4 || changeScene == 5){
					currentScene = changeScene;
					changeScene = 100;

				}
			}

			if(e.getKeyCode() == 37){ //if you press left arrow key
				player.moveLeft();
			}

			if(e.getKeyCode() == 39){ //if you press right arrow key
				player.moveRight();
			}

			if(e.getKeyCode() == 38){ //if you press up arrow key
				player.moveUp();
			}

			if(e.getKeyCode() == 40){ //if you press down arrow key
				player.moveDown();
			}

			if(e.getKeyCode() == 78){ //if you want to go to the next dialogue (n key)
				//System.out.println(isTalking);
				if(isTalking == true){
					NonPlayer nonplayer = nonPlayers.get(whoIsTalking);
					//System.out.println("incrementing dialogue of " + whoIsTalking);
					nonplayer.getDialogue().changeDialogue();
					//System.out.println(nonPlayers.get(whoIsTalking).getDialogue());

					if(nonplayer.getDialogue().finished()) {
						doneTalking = true;
						if(nonplayer instanceof FinalNonPlayer) {
							battle = new Battle(pokemon, enemyPokemon);
							changeScene = 1000;
						}
					}
				}

				//System.out.println(doneTalking);
			}

			if(e.getKeyCode() == 49){ //if you press 1 = pokemon you have
				if(changeScene == 101){
					changeScene = currentScene;
				}
				else if(changeScene == 3 || changeScene == 4 || changeScene == 5 || changeScene == 6){
					currentScene = changeScene;
					changeScene = 101;

				}
			}
		}
		else {
			if(!battle.playerWin() && !battle.enemyWin()) {
				// BATTLE CONTROLS

				if(e.getKeyCode() == 37){ //if you press left arrow key
					battle.changeSelection(-1);
				}

				if(e.getKeyCode() == 39){ //if you press right arrow key
					battle.changeSelection(1);
				}

				if(e.getKeyCode() == 38){ //if you press up arrow key
					battle.changeSelection(-2);
				}

				if(e.getKeyCode() == 40){ //if you press down arrow key
					battle.changeSelection(2);
				}

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//System.out.println("player attack");
					battle.playerAttack();
				}
			}
			else if(battle.enemyWin()) {
				if(e.getKeyCode() == 82){ //if you press r you can play again after you lose
					if(changeScene == 200){
						changeScene = 1;
						battle = null;
					}
				}
			}
		}

		if(e.getKeyCode() == 80){ //if you press p you can skip the parts
			if(changeScene < 7){
				changeScene++;

				if(changeScene > 3){
					reset();
				}

			}

			if(changeScene == 7) {
				changeScene = 1000;
				battle = new Battle(pokemon, enemyPokemon);
			}
		}


		repaint();

	}




	public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

}
