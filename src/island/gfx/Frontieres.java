package island.gfx;

import java.awt.Color;
import java.awt.Graphics;

import island.input.MouseManager;

public class Frontieres {
	
	private Graphics g;
	private int x,y;
	
	public Frontieres() {
	}
	
	
	public void tracer(Graphics g,int height,int width,float r,float s)
	{
		for(int i=0;i<13;i++) {
			g.drawLine(0,(int) (141+(r+(s/2))*i),width,(int) (141+(r+(s/2))*i));
		}
	}
	
	public void afficherCoord(MouseManager mouseManager,Graphics g)
	{
		g.setColor(Color.BLUE);
		
		if(mouseManager.isLeftPressed() )
		{
			x =mouseManager.getMouseX();
			y =mouseManager.getMouseY();
		}
			
		g.fillRect(x,y,5,5);
		System.out.println(mouseManager.getMouseX()+"   " + mouseManager.getMouseY());
	}

}
