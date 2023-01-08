package island.tiles;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import island.Game;
import island.gfx.Assets;

public class ForestTile  extends Tile {
	
	Game game;

	public ForestTile(Game game,float x, float y,int face) {
		super(x, y,Tile.DEFAULT_TILE_WIDTH,Tile.DEFAULT_TILE_HEIGHT,face);
		this.game = game;
	}

	@Override
	public void tick() {
		//getInput();
		//move();

	}
	
	/*private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up)
			yMove = -3;
		if(game.getKeyManager().down)
			yMove = 3;
		if(game.getKeyManager().left)
			xMove = -3;
		if(game.getKeyManager().right)
			xMove = 3;
	}*/

	@Override
	public void render(Graphics g,int num) {

		g.drawImage(Assets.forestTile, (int) x, (int) y,width,height, null);
	}

}


