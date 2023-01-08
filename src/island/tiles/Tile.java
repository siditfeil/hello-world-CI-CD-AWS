package island.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import island.entities.Entity;

public abstract class Tile extends Entity{
	
	
	
	public static final float  DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_TILE_WIDTH = 69,
							DEFAULT_TILE_HEIGHT =70;
	protected int health;
	protected float speed;
	protected float xMove,yMove;
	protected int face;
	
	public Tile(float x, float y,int width,int height,int face) {
		super(x, y,width,height,false);
		speed = DEFAULT_SPEED;
		xMove =0;
		yMove =0;
		this.face=face;
	}
	
	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}

	/*public void move() {
		x += xMove;
		y += yMove;
		
	}
	
	//GETTERS SETTERS

	
	
	public float getxMove() {
		return xMove;
	}



	public void setxMove(float xMove) {
		this.xMove = xMove;
	}



	public float getyMove() {
		return yMove;
	}



	public void setyMove(float yMove) {
		this.yMove = yMove;
	}




	public int getHealth() {
		return health;
	}



	public void setHealth(int health) {
		this.health = health;
	}



	public float getSpeed() {
		return speed;
	}



	public void setSpeed(float speed) {
		this.speed = speed;
	}*/


	
	
}
