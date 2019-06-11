import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Battle {
	private Pokemon playerPokemon, enemyPokemon;

	private BufferedImage dialogueBox, hpBox;

	private boolean playerTurn, waiting = false;
	private int selectedPlayerAttack;

	private String message;

	public Battle(Pokemon playerPokemon, Pokemon enemyPokemon){
		this.playerPokemon = playerPokemon;
		this.enemyPokemon = enemyPokemon;

		playerTurn = true;

		try {
			dialogueBox = ImageIO.read(new File("textBox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			hpBox = ImageIO.read(new File("hpnone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	public void drawMe(Graphics g){
		//System.out.println("DRAWING BATTLE");
		Font s = new Font("Pokemon GB", Font.BOLD, 18);
		g.setFont(s);
		FontMetrics fm = g.getFontMetrics();

		g.drawImage(dialogueBox, 200, 400, 600, 200, null);
		playerPokemon.drawMe(g, false, 0, 300, 200, 300);
		enemyPokemon.drawMe(g, true, 400, 0, 300, 300);

		//System.out.println("waiting: "+waiting+", playerTurn: "+playerTurn);


		if(!waiting && playerTurn){
			Attack[] attacks = playerPokemon.getAttacks();
			int xo = 280, yo = 460;
			int xsep = 250, ysep = 80;
			for(int i = 0; i < attacks.length; i++) {
				g.setColor(Color.BLACK);
				int x = xo + xsep * (i % 2);
				int y = yo + ysep * (i / 2);
				g.drawString(attacks[i].getName(), x, y);
				if(i == selectedPlayerAttack) {
					g.setColor(Color.RED);
					g.drawRect(x-12, y-fm.getHeight()-3, fm.stringWidth(attacks[i].getName())+20, fm.getHeight()*3/2);
				}
			}

		}else if(message != null){
			g.setColor(Color.BLACK);
			g.setFont(s);
			g.drawString(message, 250, 500);
		}


		// moves in box if waiting and playerTurn
		// else, display message in box.

		// draw pokemon
		// draw status boxes - call drawPokemonStatus
		drawPokemonStatus(playerPokemon, g, 200, 300);
		drawPokemonStatus(enemyPokemon, g, 100, 0);


		g.setColor(Color.BLACK);

		g.drawString("50", 290, 28);
		g.drawString(enemyPokemon.getName(), 60, 28);
		g.drawString("50", 390, 328);
		g.drawString(playerPokemon.getName(), 215, 328);

	}

	private void drawPokemonStatus(Pokemon pokemon, Graphics g, int x, int y) {
		g.drawImage(hpBox, x, y, 300, 100, null);
		//health bar
		float healthPercent = pokemon.getHealthPercent();
		Color barColor;
		if(healthPercent > 0.5) {
			barColor = Color.GREEN;
		} else if(healthPercent > 0.2) {
			barColor = Color.YELLOW;
		} else {
			barColor = Color.RED;
		}

		// find the number of pixels in the health bar image for the bar.
		// the bar length is that number of pixels times the health percent.

		//adjust the length between health bar area
		int numPixels = 178; // find value
		int barLength = (int)Math.ceil(numPixels * healthPercent); // always round up
		int barHeight = 16;
		int barX = x+90, barY = y+45;

		g.setColor(Color.BLACK);
		g.fillRect(barX, barY, numPixels, barHeight);
		g.setColor(barColor);
		//adjust the placement of the bar w/ x and y
		g.fillRect(barX, barY, barLength, barHeight);
		g.setColor(Color.BLACK);
		g.drawRect(barX, barY, numPixels, barHeight);
	}

	public void changeSelection(int amt) {
		if(!playerTurn) return;
		selectedPlayerAttack += amt;
		while(selectedPlayerAttack < 0) selectedPlayerAttack += 4;
		selectedPlayerAttack %= 4;
	}

	public void playerAttack() {
		if(playerTurn) {
			attack(playerPokemon, enemyPokemon, playerPokemon.getAttacks()[selectedPlayerAttack]);
		}
	}

	public void enemyAttack() {
		if(!playerTurn) {
			attack(enemyPokemon, playerPokemon, enemyPokemon.getAttacks()[(int)(Math.random()*4)]);
		}
	}

	private void attack(Pokemon attacker, Pokemon attacked, Attack attack) {
		if(waiting) return;

		waiting = true;
		new Thread(() -> {
			message = attacker.getName()+" used "+attack.getName()+"!";
			try {
				Thread.sleep(2000);
			} catch(InterruptedException ex) {
			}
			// decrease health
			for(int i = 0; i < attack.getDamage(); i++) {
				attacked.attack(1);
				try {
					Thread.sleep(1000/attack.getDamage());
				} catch(InterruptedException ex) {
				}
			}

			playerTurn = !playerTurn;
			waiting = false;

			if(!playerTurn) {
				enemyAttack();
			}
		}).start();
	}



	public boolean playerWin(){
		return enemyPokemon.getHP() <= 0;
	}

	public boolean enemyWin(){
		return playerPokemon.getHP() <= 0;
	}
}
