package island.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed, rightPressed,clicked,clicked3;
	private int mouseX, mouseY;
	private int xClic2,yClic2,xClic3,yClic3;
	

	public MouseManager(){
		
	}
	
	// Getters
	
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public boolean isClicked3(){
		return clicked3;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	public int getxClic2() {
		return xClic2;
	}

	public int getyClic2() {
		return yClic2;
	}
	
	public int getxClic3() {
		return xClic3;
	}

	public int getyClic3() {
		return yClic3;
	}
	
	// Implemented methods
   
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	/*	if (e.getClickCount() == 2 && !e.isConsumed()) {
			clicked=true;
			xClic2=e.getX();
			yClic2=e.getY();
		}
		else
			clicked=false;
		
		if (e.getClickCount() == 3 && !e.isConsumed()) {
			clicked3=true;
			xClic3=e.getX();
			yClic3=e.getY();
		}
		else
			clicked3=false;*/

	
	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}