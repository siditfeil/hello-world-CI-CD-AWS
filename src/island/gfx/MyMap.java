package island.gfx;

public class MyMap {
	private NumBox numB;



	
	private int numCouleurJoeur;
	private int indexJoueur;
	
	public MyMap(NumBox numB, int numCouleurJoeur,int indexJoueur) {
		super();
		this.numB = numB;
		this.numCouleurJoeur = numCouleurJoeur;
		this.indexJoueur=indexJoueur;
	}
	
	
	public NumBox getNumB() {
		return numB;
	}

	
	public int getIndexJoueur() {
		return indexJoueur;
	}
	
	public int getNumCouleurJoeur() {
		return numCouleurJoeur;
	}
	
	public void setNumB(NumBox numB) {
		this.numB = numB;
	}
	
	public void setNumCouleurJoeur(int numCouleurJoeur) {
		this.numCouleurJoeur = numCouleurJoeur;
	}
	public void setIndexJoueur(int indexJoueur) {
		this.indexJoueur = indexJoueur;
	}
	
	
}
