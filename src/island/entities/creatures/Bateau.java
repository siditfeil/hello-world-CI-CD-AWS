package island.entities.creatures;

import java.awt.Graphics;
import island.Game;
import island.gfx.Assets;
import island.tiles.Tile;

public class Bateau extends Creature {
	private Game game;
	public static boolean etatBateau=true;

	public Bateau(Game game,float x, float y,boolean mvt) {
		super(x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,mvt);
		this.game = game;
	}

	@Override
	public void tick() {
		/*getInput();
		if(etatBateau)
			move();*/

	}
	


	private void getInput() {
		xMove = this.getX();
		yMove = this.getY();
		
		int xPos=(int)this.getX();
		int yPos=(int)this.getY();
		
		
		if(estSurBateau(xPos, 
							yPos,Tile.DEFAULT_TILE_HEIGHT-8,30,game.getMouseManager().getMouseX(),
							game.getMouseManager().getMouseY()) )
		{
		
				
				/*if(game.getKeyManager().up)
					yMove = -1;
				if(game.getKeyManager().down)
					yMove = 1;
				if(game.getKeyManager().left)
					xMove = -1;
				if(game.getKeyManager().right)
					xMove = 1;*/
			/*xMove=game.getMouseManager().getMouseX();
			yMove=game.getMouseManager().getMouseY();*/
			if(!game.getMouseManager().isLeftPressed() || !game.getMouseManager().isRightPressed())
			{	
				xMove=game.getMouseManager().getMouseX()-10;
				yMove=game.getMouseManager().getMouseY()-10;
			}
			
			
		}

				

	}
	
	public boolean estSurBateau(int x,int y,int wid,int hei,int xPos,int yPos)
	{
		boolean a = (xPos>x && xPos<x+wid);
		boolean b = (yPos>y && yPos<y+hei);
		if(a && b)
			return true;
		else 
			return false;
	}

	@Override
	public void render(Graphics g,int num) {
		g.drawImage(Assets.bateau, (int) x, (int) y,width,height, null);
	}
	
	

}


