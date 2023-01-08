package island.gfx;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class TestImageHexa {
	
	
	public TestImageHexa() {
		
	}
	
	
	public static BufferedImage getTexturedImage(
			BufferedImage src, Shape shp, int x, int y) {
		Rectangle r = shp.getBounds();
		System.out.println("les coord sont L="+ r.width+" et h="+r.height);
		// create a transparent image with 1 px padding.
		BufferedImage tmp = new BufferedImage(
				r.width+2,r.height+2,BufferedImage.TYPE_INT_ARGB);
		
		// get the graphics object
		Graphics2D g = tmp.createGraphics();
		// set some nice rendering hints
		/*g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(
				RenderingHints.KEY_COLOR_RENDERING, 
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);*/
		// create a transform to center the shape in the image
		AffineTransform centerTransform = AffineTransform.
			getTranslateInstance(-r.x+1, -r.y+1);
		// set the transform to the graphics object
		//g.setTransform(centerTransform);
		// set the shape as the clip
		g.setClip(shp);
		// draw the image
		g.drawImage(src, 100,100,x, y, null);
		// clear the clip
		g.setClip(null);
		// draw the shape as an outline
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(1f));
		//g.draw(shp);
		// dispose of any graphics object we explicitly create
		g.dispose();

		return tmp;
	}

	public static Shape getPointedShape(int points, int radius,Point center) {
		double angle = Math.PI * 2 / points;

		GeneralPath p = new GeneralPath();
		for (int ii = 0; ii < points; ii++) {
			double a = angle * ii;

			//double x = (Math.cos(a) * radius) + radius;
			//double y = (Math.sin(a) * radius) + radius;
			double x=(center.y + radius * Math.sin(ii * 2 * Math.PI / 6D));
			double y=(center.x + radius* Math.cos(ii * 2 * Math.PI / 6D));
			System.out.println("les valeurs de x et y sont ("+x+","+y+")");
			if (ii == 0) {
				p.moveTo(x, y);
			} else {
				p.lineTo(x,y);
			}
		}
		p.closePath();

		return p;
	}

}
