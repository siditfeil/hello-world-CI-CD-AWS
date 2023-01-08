package island.gfx;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static  BufferedImage plateau,bgGround,beachTile,forestTile,mountainTile,imagePrincipale,jouer,quitter,regles;
	public static BufferedImage imgPlayer,imgBeachTile,imgForestTile,imgMountainTile,imgImagePrincipale,imgJouer,imgQuitter,imgRegles;
	public static Shape hxgn1,hxgn2,hxgn3,hxgn4,hxgn5,hxgn6;
	public static BufferedImage bateau;
	public static BufferedImage joueurRouge,joueurJaune,joueurVert,joueurBleu;
	public static BufferedImage serpent;
	public static BufferedImage de;
	
	
	
	public static void init() {
		bgGround     = ImageLoader.loadImage("/textures/GameState/ground.png");
		plateau = ImageLoader.loadImage("/textures/GameState/interess.png");
		imagePrincipale =ImageLoader.loadImage("/textures/MenuState/Principale.png");
		jouer =ImageLoader.loadImage("/textures/MenuState/jouer.png");
		quitter =ImageLoader.loadImage("/textures/MenuState/quitter.png");
		regles  =ImageLoader.loadImage("/textures/MenuState/Regles.png");
		
		joueurRouge    = ImageLoader.loadImage("/textures/joueurRouge.png");
		joueurJaune    = ImageLoader.loadImage("/textures/joueurJauneTest.png");
		joueurVert    = ImageLoader.loadImage("/textures/joueurVert.png");
		joueurBleu    = ImageLoader.loadImage("/textures/joueurBleu.png");
		
		imgBeachTile = ImageLoader.loadImage("/textures/GameState/plageTuile.png");
		imgForestTile = ImageLoader.loadImage("/textures/GameState/foretTuile.png");
		imgMountainTile = ImageLoader.loadImage("/textures/GameState/montagneTuile.png");
		
		hxgn1 = getPointedShape(6, 40);
		hxgn2 = getPointedShape(6,40);
		hxgn3 = getPointedShape(6,40);
		//mountainTile = getTexturedImage(imgMountainTile, hxgn1,-120,-118);
		//forestTile  = getTexturedImage(imgForestTile, hxgn2,-120+130,-118+110);
		forestTile  = getTexturedImage(imgForestTile, hxgn1,0,0);
		mountainTile= getTexturedImage(imgMountainTile, hxgn2,0,0);
		beachTile= getTexturedImage(imgBeachTile, hxgn3,0,0);
		
		//bateau 
		bateau = ImageLoader.loadImage("/textures/bateau.png");
		
		//Serpent de mer
		serpent = ImageLoader.loadImage("/textures/serpentDeMer.png");
		
		//dé
		de = ImageLoader.loadImage("/textures/de.png");
	}
	
	
	
	public static BufferedImage getTexturedImage(
			BufferedImage src, Shape shp, int x, int y) {
		Rectangle r = shp.getBounds();
		// create a transparent image with 1 px padding.
		BufferedImage tmp = new BufferedImage(
				r.width+2,r.height+2,BufferedImage.TYPE_INT_ARGB);
		// get the graphics object
		Graphics2D g = tmp.createGraphics();
		// set some nice rendering hints
		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(
				RenderingHints.KEY_COLOR_RENDERING, 
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		// create a transform to center the shape in the image
		AffineTransform centerTransform = AffineTransform.
				getTranslateInstance(-r.x+1, -r.y+1);
		// set the transform to the graphics object
		g.setTransform(centerTransform);
		// set the shape as the clip
		g.setClip(shp);
		// draw the image
		g.drawImage(src, x, y, null);
		// clear the clip
		g.setClip(null);
		// draw the shape as an outline
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1f));
		g.draw(shp);
		// dispose of any graphics object we explicitly create
		g.dispose();

		return tmp;
	}

	public static Shape getPointedShape(int points, int radius) {
		double angle = Math.PI * 2 / points;

		GeneralPath p = new GeneralPath();
		for (int ii = 0; ii < points; ii++) {
			double a = angle * ii;

			double y = (Math.cos(a) * radius) + radius;
			double x = (Math.sin(a) * radius) + radius;
			if (ii == 0) {
				p.moveTo(x, y);
			} else {
				p.lineTo(x, y);
			}
		}
		p.closePath();

		return p;
	}

}
