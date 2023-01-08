package island.gfx;

import java.util.ArrayList;

import island.entities.creatures.Player;


public class Box {
	
	//private static final long serialVersionUID = 1L;
	private int ligne,col; 
	private boolean bateau; //s'il y a un bateau dans la case ou pas 
	private int typeTuile;  /* 0 pas de tuile
								1 tuile de plage
								2 tuile de foret 
								3 tuile de montagne
								*/
							
	private boolean requin;
	private boolean serpent_de_mer;
	private boolean baleine;
	private ArrayList<Player> explorateur;
	
	

	//Constructeur
	public Box(int ligne, int col, boolean bateau, int typeTuile, boolean requin, boolean serpent_de_mer,
			boolean baleine) {
		super();
		this.ligne = ligne;
		this.col = col;
		this.bateau = bateau;
		this.typeTuile = typeTuile;
		this.requin = requin;
		this.serpent_de_mer = serpent_de_mer;
		this.baleine = baleine;
		this.explorateur = explorateur;
	}
	
	//Getters
	
	public int getLigne() {
		return ligne;
	}


	public int getCol() {
		return col;
	}


	public boolean isBateau() {
		return bateau;
	}


	public int getTypeTuile() {
		return typeTuile;
	}


	public boolean isRequin() {
		return requin;
	}


	public boolean isSerpent_de_mer() {
		return serpent_de_mer;
	}


	public boolean isBaleine() {
		return baleine;
	}


	public ArrayList<Player> getExplorateur() {
		return explorateur;
	}


	//Setters
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setBateau(boolean bateau) {
		this.bateau = bateau;
	}

	public void setTypeTuile(int typeTuile) {
		this.typeTuile = typeTuile;
	}

	public void setRequin(boolean requin) {
		this.requin = requin;
	}

	public void setSerpent_de_mer(boolean serpent_de_mer) {
		this.serpent_de_mer = serpent_de_mer;
	}

	public void setBaleine(boolean baleine) {
		this.baleine = baleine;
	}

	public void setExplorateur(ArrayList<Player> explorateur) {
		this.explorateur = explorateur;
	}
	
	
	


}
