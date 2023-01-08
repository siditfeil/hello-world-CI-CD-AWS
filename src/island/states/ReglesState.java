package island.states;
//cette classe permet l'affichage d'un PDF qui détaille les régles du jeu
import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import island.Game;
import island.gfx.Assets;

public class ReglesState  extends State{
	
	private File file=null;
	Desktop desktop = null;
	final static String location = System.getProperty("user.dir") + "/res/textures/regles.pdf";
	Path path;






	public ReglesState(Game game){
		super(game);

	}

	@Override
	public void tick() {
		path = Paths.get(location);
		file = new File(location);
		try {

			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}

			desktop.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}





	}

	@Override
	public void render(Graphics g) {

	}




}
