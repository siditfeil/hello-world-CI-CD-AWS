package island.states;

//la classe state régroupe tous les états du jeu 
//game state
//Menu state
//regles state
//c'est une abstract classe
import java.awt.Graphics;

import island.Game;


public abstract class State {

	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
