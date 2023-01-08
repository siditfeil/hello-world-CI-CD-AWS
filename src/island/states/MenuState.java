//cette classe s'occupe de l'affichage et la mise à jour des bouttons au lancement du jeu 
//c'est la prémiére fenêtre qui s'ouvre 
//permet à l'utilisateur soit de choisir de jouer ,soit de consulter les régles du jeu ,soit de quitter
//cette classe hérite de la classe State 
//donc on definit toutes les fonctions de la classe state (click et render )

package island.states;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import island.Game;
import island.display.Display;
import island.gfx.Assets;
import island.input.MouseManager;

public class MenuState extends State {
	
	private int x,y,xBtnJ,yBtnJ,xBtnQ,yBtnQ,xBtnR,yBtnR,widthBtn,heightBtn;
	private ReglesState regleState;
	private boolean boolJouer,boolQuitter,boolRegles;
	public static int varGlob=0;
	island.gfx.Point retourClic;
	
	
	public MenuState(Game game){
		super(game);
		
	}

	@Override
	public void tick() {
		
		//récuperer les coordonnées du clic sur la souris 
		retourClic=recupererPointClicLeft(game.getMouseManager());

			
		
			
		//verifie si le clic est sur le boutton Jouer
		boolJouer=retourClic.getX()!=-1 && estJouer(retourClic.getX(), retourClic.getY());
		
		//Verifie si le clic est sur le boutton Quitter
		boolQuitter=retourClic.getX()!=-1 && estQuitter(retourClic.getX(), retourClic.getY());
		
		//si c'est le boutton Jouer on change de state 
		if(boolJouer) {
			State.setState(game.gameState);
			return;
		}
		
		//si c'est le boutton quitter on demande à l'utilisateur de confirmer s'il veut bien sortir 
		if(boolQuitter) {
			int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Are you sure you want to exit the program?", "Exit Program Message Box",
			        JOptionPane.YES_NO_OPTION);

			    if (confirmed == JOptionPane.YES_OPTION) {
			    	System.exit(0);
			    }else {
			    	State.setState(game.menuState);
			    }
			
			return;
		}
		


					
	}

	@Override
	public void render(Graphics g) {
		xBtnJ=245 ;  yBtnJ=200;
		xBtnR=245 ;  yBtnR=316;
		xBtnQ=245 ;  yBtnQ=436;
		widthBtn = 180 ; heightBtn =70;
		
		//affichage des bouttons Jouer,Quitter et Regles 
		g.drawImage(Assets.imagePrincipale, 0, 0,game.getWidth(),game.getHeight(), null);
		g.drawImage(Assets.jouer, xBtnJ, yBtnJ,widthBtn,heightBtn, null);
		g.drawImage(Assets.quitter, xBtnQ, yBtnQ,widthBtn,heightBtn, null);
		g.drawImage(Assets.regles,xBtnR, yBtnR,widthBtn,heightBtn, null);
		//afficherCoord(game.getMouseManager(), g);
	}
	
	

	//fonction qui permet de verifier si un point est dans la zone du boutton Jouer 
	public boolean estJouer(int x,int y) {
		if((x >= 245 && x<=425) && (y>=200 && y<=270))
			return true;
		else 
			return false;
	}
	
	
	//fonction qui permet de verifier si un point est dans la zone du boutton Quitter
	public boolean estQuitter(int x,int y) {
		if((x >= 245 && x<=425) && (y>=436 && y<=506))
			return true;
		else 
			return false;
	}
	
	
	//fonction qui permet de retourner la position du clic gauche de la sourie 
	public island.gfx.Point recupererPointClicLeft(MouseManager mouseManager)
	{
		island.gfx.Point p;
		p=new island.gfx.Point(-1,-1);
		
		if(mouseManager.isLeftPressed())
		{
			int xClic =game.getMouseManager().getMouseX();
			int yClic=game.getMouseManager().getMouseY();
		
			p.setX(xClic);
			p.setY(yClic);
			
			
			
		}
		
		
		return p;
	
	
	}
	
	//fonction qui permet de retourner la position du clic droit de la sourie 
	
	public island.gfx.Point recupererPointClicRight(MouseManager mouseManager)
	{
		island.gfx.Point p;
		p=new island.gfx.Point(-1,-1);
		
		if(mouseManager.isRightPressed())
		{
			int xClic =game.getMouseManager().getMouseX();
			int yClic=game.getMouseManager().getMouseY();
			p.setX(xClic);
			p.setY(yClic);
						
			
		}

		
		return p;
	
	
	}
	
}
