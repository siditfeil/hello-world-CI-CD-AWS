package island.entities.creatures;

import java.awt.Graphics;

import island.Game;
import island.gfx.Assets;

public class Serpent extends Creature{
	private Game game;
	
	public Serpent(Game game,float x, float y) {
		super(x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,false);
		this.game = game;
		
	}



	@Override
	public void tick() {
		

	}


	@Override
	public void render(Graphics g,int num) {
			  g.drawImage(Assets.serpent, (int) x, (int) y,40,90, null);
		}

}
