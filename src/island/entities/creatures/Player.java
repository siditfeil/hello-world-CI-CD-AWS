package island.entities.creatures;
import java.awt.Color;
import java.awt.Graphics;

import island.Game;
import island.gfx.Assets;
import island.input.MouseManager;
import island.states.GameState;

public class Player extends Creature {
	
	private Game game;
	private int numCouleurJoueur;//o pour le rouge
								// 1 pour jaune
								//2 pour Vert
								// 3 pour Bleu
	private int numeroSurPion;
	private boolean bool1,bool2;
	private Bateau bateau;



	public Player(Game game,float x, float y,boolean mvt,int numCouleurJoueur,int numeroSurPion) {
		super(x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,mvt);
		this.game = game;
		this.numCouleurJoueur=numCouleurJoueur;
		this.numeroSurPion=numeroSurPion;
	}



	@Override
	public void tick() {
	
		/*getInput();
		if(this.getMvt())
		{
			move();
			
		}*/			

	}

	@Override
	public void render(Graphics g,int num) {
		switch(num) {
		  case 0:
			  g.drawImage(Assets.joueurRouge, (int) x, (int) y,18,35, null);
			  break;
		  case 1:
			  g.drawImage(Assets.joueurJaune, (int) x, (int) y,18,35, null);
			  break;
		  case 2:
			  g.drawImage(Assets.joueurVert, (int) x, (int) y,18,35, null);
			  break;
		  case 3:
			  g.drawImage(Assets.joueurBleu, (int) x, (int) y,18,35, null);
			  break;
		  default:
			  break;
			  
		}


	}
	
	public boolean estSurJoueur(int x,int y,int wid,int hei,int xPos,int yPos)
	{
		boolean a = (xPos>x && xPos<x+wid);
		boolean b = (yPos>y && yPos<y+hei);
		if(a && b)
			return true;
		else 
			return false;
	}
	
	public int getNumCouleurJoueur() {
		return numCouleurJoueur;
	}

	public void setNumCouleurJoueur(int numJoueur) {
		numCouleurJoueur = numJoueur;
	}
	
	public int getNumeroSurPion() {
		return numeroSurPion;
	}



	public void setNumeroSurPion(int numeroSurPion) {
		this.numeroSurPion = numeroSurPion;
	}


}


