/*elle contiendra tout le code de base de notre jeu, 
 * elle démarrera tout, elle exécutera tout et elle fermera notre jeu (ce sera la classe Main de notre jeu)
 */

package island;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



import island.display.Display;
import island.gfx.Assets;
import island.gfx.Frontieres;
import island.gfx.ImageLoader;
import island.gfx.TestImageHexa;
import island.input.KeyManager;
import island.input.MouseManager;
import island.input.TestPane;
import island.states.GameState;
import island.states.MenuState;
import island.states.State;

public class Game implements Runnable {

	private Display display;
	public int width, height;
	public String title;
	
	
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public State gameState;
	public State menuState;
	public State regleState;
	
	//input
	private MouseManager mouseManager;
	private KeyManager keyManager;
	
	
	

	//Le constructeur de la classe Game
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		mouseManager = new MouseManager();
		keyManager   = new KeyManager();
	}
	
	//initialisation
	private void init(){
		//dans Game, nous avons besoin d'une instance de classe Display
		display = new Display(title, width, height);
		
		//ajout de lu mousemanager
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().add(new TestPane());
		
		
		
		Assets.init();
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(menuState);
	}
	
	private void tick(){
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
		
	}
	
	//Draw things to the screen
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g= bs.getDrawGraphics();
		
		//Clear screen
		g.clearRect(0,0,width,height);
		//Draw Here!
		//////////////////////////////////////////////

		
		if(State.getState() != null)
			State.getState().render(g);
			
		
        
        
        
		
		
		
		//End Drawing
		bs.show();
		g.dispose();
	}
	

	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
		
		}
		
		stop();
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	


	
	

	/*pour les threads 
	le principal avantage est de pouvoir repartir différents traitements d’un même programme en plusieurs unités distinctes pour permettre les exécutions simultanés*/
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
