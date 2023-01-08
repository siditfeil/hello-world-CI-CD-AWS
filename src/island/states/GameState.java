//cette classe répresente l'etat de notre jeu 
//dans cette classe on fait l'affichage de toutes les entités du jeu
//la partie logique du jeu 

package island.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import island.Game;
import island.entities.creatures.Bateau;
import island.entities.creatures.Player;
import island.entities.creatures.Serpent;
import island.gfx.Assets;
import island.gfx.Box;
import island.gfx.Frontieres;
import island.gfx.MyMap;
import island.gfx.NumBox;
import island.gfx.Point;
import island.input.MouseManager;
import island.tiles.BeachTile;
import island.tiles.ForestTile;
import island.tiles.MountainTile;
import island.tiles.Tile;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;


public class GameState extends State {
	
	//déclaration des variables
	//////////////////////////////////////////////////////////////////////
	private Player[] joueurJaune,joueurRouge,joueurVert,joueurBleu;
	private Player[] explorateurs;
	
	private MountainTile[] mntTile;
	private ForestTile[]   frstTile;
	private BeachTile[] bchTile;
	private Serpent[] serpent;
	private Point pJ,pB,p0,p1,p2,p3,p4,p5,p6,p7;
	private Point pRouge,pJaune,pVert,pBleu;
	private Bateau bateau1Rouge;
	private  Bateau bateau2Rouge;
	private Bateau bateau1Jaune,bateau2Jaune;
	private Bateau bateau1Vert,bateau2Vert;
	private Bateau bateau1Bleu,bateau2Bleu;
	
	private Bateau[] tabBateau;
	//private Point tabPoints[];
	private int x,y;
	private int h=36,s=70;
	
	
	private Box[][] cases;
	private Point [] tabPoint;
	private int ind=0;
	private int res1= -1;
	private int res2=-1;
	private boolean bool=false;
	
	

	ArrayList<Point> listeDesPointsHG;
	
	ArrayList<MyMap>  tabNumMap;
	HashMap<Integer,NumBox> hmap ;

	/////////////////////////////////////////////////////////
	
	
	//constructeur de la classe GameState
	public GameState(Game game){
		super(game);
		init_Plateau();
		
	}
	
	//********************************************************** les updates ********************************************************************
	@Override
	public void tick() {


		//update des serpent
		for(int i=0;i<5;i++)
		{
			serpent[i].tick();
			int e=PointToNumBox((int)serpent[i].getX()+15,(int)serpent[i].getY()+70,listeDesPointsHG).getLigne();
			int f=PointToNumBox((int)serpent[i].getX()+15,(int)serpent[i].getY()+70,listeDesPointsHG).getColonne();
			cases[e][f].setSerpent_de_mer(true);
		}
		
		//updates des tuiles et mise à jour de la matrice cases qui permet de stocker les informations
		//de chaque (type de tuile ,la présence d'un serpent ou non ,baleine .....)
		
		for(int i=0;i<8;i++)
		{
			//tuiles de mer
			bchTile[i].tick();
			int a=PointToNumBox((int)bchTile[i].getX()+35,(int)bchTile[i].getY()+35,listeDesPointsHG).getLigne();
			int b=PointToNumBox((int)bchTile[i].getX()+35,(int)bchTile[i].getY()+35,listeDesPointsHG).getColonne();
			cases[a][b].setTypeTuile(1);
			
			//tuiles de foret 
			frstTile[i].tick();
			 a=PointToNumBox((int)frstTile[i].getX()+35,(int)frstTile[i].getY()+35,listeDesPointsHG).getLigne();
			 b=PointToNumBox((int)frstTile[i].getX()+35,(int)frstTile[i].getY()+35,listeDesPointsHG).getColonne();
			 cases[a][b].setTypeTuile(2);
			 
			
			 //tuiles de montagne
			if(i<8)
			{
				mntTile[i].tick();
				a=PointToNumBox((int)mntTile[i].getX()+35,(int)mntTile[i].getY()+35,listeDesPointsHG).getLigne();
				b=PointToNumBox((int)mntTile[i].getX()+35,(int)mntTile[i].getY()+35,listeDesPointsHG).getColonne();
				cases[a][b].setTypeTuile(3);
			}
				
		}
		
		//updates des joueurs
		
		for(int i=0;i<40;i++)
		{
			explorateurs[i].tick();
		}
		
		//updates des bateaux
		for(int i=0;i<8;i++)
		{
			tabBateau[i].tick();
		}
		
		//$$$$$$$$$$$$$$ Appel des fonctions 
		
		bouger(game.getMouseManager());
		//parcouriCasesContenantDesJoueurs(tabNumMap);
		
	}
	
	//***************************************************************render***************************************************************
	//la méthodes render s'occupe de l'affichage de toute les entités de notre jeu
	@Override
	public void render(Graphics g) {
		
		//afficher le background du plateau
		g.drawImage(Assets.bgGround,0,0,game.getWidth(),game.getHeight(),null);
		g.drawImage(Assets.plateau,400,100,950,740,null);
		

		
	
		
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		
		//Affichage des tuiles

		for(int i=0;i<16;i++)
		{
			bchTile[i].render(g,0);
			frstTile[i].render(g,0);
			if(i<8)
				mntTile[i].render(g,0);
		}
		
		
		
		
		
		//affichage des bateaux
		
		for(int i=0;i<8;i++)
		{
			tabBateau[i].render(g,0);
		}
		
		
		//affichage des joueurs
		for(int i=0;i<40;i++)
		{
			
			explorateurs[i].render(g,explorateurs[i].getNumCouleurJoueur());
		}
		
		//affichage des  serpents de mer
		for(int i=0;i<5;i++)
			serpent[i].render(g, 0);
		
	
		
		//afficher les coord pour debuguer
		//afficherCoord(game.getMouseManager(),g);
		
	}
	
	//****************************************initialisation du plateau***********************************************************//
	
	//cette fonction permet d'initialiser le jeu avec toutes les entités présentes dans la plateau du jeu à l'instant du lancement
	
	public void init_Plateau()
	{
		//la tableau cases sert à stocker les informations dans chaque hexagone au cours du jeu 
		cases = new Box[12][12];
		
		
		hmap = new HashMap<Integer, NumBox>();
		//les points 
		pJ = new Point(768,232);
		
		//des bateau
		pRouge = new Point(623,877);
		pJaune = new Point(130,430);
		pVert = new Point(623,18);
		pBleu = new Point(1554,430);
		
		//p0 = new Point(669-35,107+2);
		p0 = new Point(773-35,267+2);
		p1 = new Point(738-35,320+2);
		p2 = new Point(634-35,373+2);
		p3 = new Point(668-35,427+2);
		//p4 = new Point(669-35+2*70,87+20);
		//p5 = new Point(669-35+2*70,87+20+212);
		
		
		//initialisation de tab map qui permet de stocker les information suivantes :
		//numBox pour savoir le numéro de ligne et colonne de la case
		// numCouleur qui permet de savoir la couleur du joueur 
		//index joueur qui permet de saavoir quel joueur est present dans quelle case
		tabNumMap =new ArrayList<MyMap>();
		
		
		//initialisation des  tuiles 
		mntTile = new MountainTile[8];
		bchTile = new BeachTile[16];
		frstTile= new ForestTile[16];
		
		//initialisation des tableaux des joueurs
		joueurRouge = new Player[10];
		joueurJaune = new Player[10];
		joueurVert = new Player[10];
		joueurBleu = new Player[10];
		
		//initialisation d'un  tableau des jouers qui régroupe tous les joueurs dans un seul tableau
		explorateurs = new Player[40];
		
		//tableau des serpents
		serpent = new Serpent[5];
		
		//initialisation des cases de plateau à vide ,car au debut du jeu il y a rien dans les cases (ni joueur,ni serpent ,ni tuile ......)
		
		for(int i=0;i<12;i++)
		{
			for(int j=0;j<12;j++)
			{
				cases[i][j]=new Box(i,j,false,0,false,false,false) ;
			}
		}
		
		//remplir une liste des points hauts gauches de toutes les cases dans le plateau
		listeDesPointsHG= new ArrayList<Point>(); 
		tabPoint = new Point[200];
		
		for(int k=0;k<13;k++)
		{
			//les cases paires
			if(k%2==0)
			{
				for(int i=0;i<12;i++)
				{
					tabPoint[ind]=new Point(497+70*i,124+(2*36+34)*k/2);
					listeDesPointsHG.add(tabPoint[ind]);
					ind++;
				}
			}
			//les cases impaires
			else
			{
				for(int i=0;i<12;i++)
				{
					tabPoint[ind]=new Point(462+70*i,177+(2*36+34)*(k/2));
					listeDesPointsHG.add(tabPoint[ind]);
					ind++;
				}		
					

			}
		}
		
		
		//instanciation du  tableau explorateur;
		
		
		//joueur
		for(int i=0;i<5;i++)
		{
			if(i<3)
			{
				joueurRouge[i]= new Player(game,pRouge.getX()+80+i*30,pRouge.getY()-5,false,0,1);
				joueurJaune[i]= new Player(game,pJaune.getX()+80+i*30,pJaune.getY()-5,false,1,1);
				joueurVert[i]= new Player(game,pVert.getX()+80+i*30,pVert.getY()-5,false,2,1);
				joueurBleu[i]= new Player(game,pBleu.getX()-180+i*30,pBleu.getY()-5,false,3,1);
			}
			
			else
			{
				joueurRouge[i]= new Player(game,pRouge.getX()+80+i*30,pRouge.getY()-5,false,0,2);
				joueurJaune[i]= new Player(game,pJaune.getX()+80+i*30,pJaune.getY()-5,false,1,2);
				joueurVert[i]= new Player(game,pVert.getX()+80+i*30,pVert.getY()-5,false,2,2);
				joueurBleu[i]= new Player(game,pBleu.getX()-180+i*30,pBleu.getY()-5,false,3,2);
			}
			
		}
		
		for(int i=5;i<10;i++)
		{
			if(i<7)
			{
				joueurRouge[i]= new Player(game,pRouge.getX()+80+(i-5)*30,pRouge.getY()+40-5,false,0,3);
				joueurJaune[i]= new Player(game,pJaune.getX()+80+(i-5)*30,pJaune.getY()-5+40,false,1,3);
				joueurVert[i]= new Player(game,pVert.getX()+80+(i-5)*30,pVert.getY()-5+40,false,2,3);
				joueurBleu[i]= new Player(game,pBleu.getX()-180+(i-5)*30,pBleu.getY()-5+40,false,3,3);
			}
			else
			{
				joueurRouge[i]= new Player(game,pRouge.getX()+80+(i-5)*30,pRouge.getY()-5+40,false,0,4+(i-7));
				joueurJaune[i]= new Player(game,pJaune.getX()+80+(i-5)*30,pJaune.getY()-5+40,false,1,(i-7));
				joueurVert[i]= new Player(game,pVert.getX()+80+(i-5)*30,pVert.getY()-5+40,false,2,(i-7));
				joueurBleu[i]= new Player(game,pBleu.getX()-180+(i-5)*30,pBleu.getY()-5+40,false,3,(i-7));
			}
		}
		
		//le tableay explorateur 
		for(int i=0;i<40;i++)
		{
			if(i<10)
				explorateurs[i]=new Player(game,joueurRouge[i].getX(),joueurRouge[i].getY(),false,joueurRouge[i].getNumCouleurJoueur(),joueurRouge[i].getNumeroSurPion());
			if(i>=10 && i<20)
				explorateurs[i]=new Player(game,joueurJaune[i-10].getX(),joueurJaune[i-10].getY(),false,joueurJaune[i-10].getNumCouleurJoueur(),joueurJaune[i-10].getNumeroSurPion());
			
			if(i>=20 && i<30)
				explorateurs[i]=new Player(game,joueurVert[i-20].getX(),joueurVert[i-20].getY(),false,joueurVert[i-20].getNumCouleurJoueur(),joueurVert[i-20].getNumeroSurPion());
			
			if(i>=30 && i<40)
				explorateurs[i]=new Player(game,joueurBleu[i-30].getX(),joueurBleu[i-30].getY(),false,joueurBleu[i-30].getNumCouleurJoueur(),joueurBleu[i-30].getNumeroSurPion());
					
		}
		
	
		
		//serpent
		serpent[0] = new Serpent(game,861,440-40);
		serpent[1] = new Serpent(game,540,170-40);
		serpent[2] = new Serpent(game,510,650-40);
		serpent[3] = new Serpent(game,1206,225-40);
		serpent[4] = new Serpent(game,1170,709-40);
		
		//Tuiles de montagne
		mntTile[0]= new MountainTile(game,p1.getX()+s,p1.getY(),0);
		mntTile[1]= new MountainTile(game,p1.getX()+3*s,p1.getY(),0);
		mntTile[2]= new MountainTile(game,p3.getX()+s,p3.getY(),0);
		mntTile[3]= new MountainTile(game,p3.getX()+2*s,p3.getY(),0);
		mntTile[4]= new MountainTile(game,p3.getX()+4*s,p3.getY(),0);
		mntTile[5]= new MountainTile(game,p2.getX()+6*s,p2.getY()+h+Tile.DEFAULT_TILE_HEIGHT,0);
		mntTile[6]= new MountainTile(game,p1.getX()+s,p1.getY()+2*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		mntTile[7]= new MountainTile(game,p1.getX()+3*s,p1.getY()+2*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		
		
		//Tuiles de plage
		bchTile[0]= new BeachTile(game,p0.getX(),p0.getY(),0);
		bchTile[1]= new BeachTile(game,p0.getX()+2*s,p0.getY(),0);
		bchTile[2]= new BeachTile(game,p1.getX()+2*s,p1.getY(),0);
		bchTile[3]= new BeachTile(game,p1.getX()+4*s,p1.getY(),0);
		bchTile[4]= new BeachTile(game,p2.getX(),p2.getY(),0);
		bchTile[5]= new BeachTile(game,p2.getX()+3*s,p2.getY(),0);
		bchTile[6]= new BeachTile(game,p2.getX()+4*s,p2.getY(),0);
		bchTile[7]= new BeachTile(game,p3.getX(),p3.getY(),0);
		bchTile[8]= new BeachTile(game,p3.getX()+5*s,p3.getY(),0);
		bchTile[9]= new BeachTile(game,p3.getX()+6*s,p3.getY(),0);
		bchTile[10]= new BeachTile(game,p2.getX()+s,p2.getY()+h+Tile.DEFAULT_TILE_HEIGHT,0);
		bchTile[11]= new BeachTile(game,p2.getX()+4*s,p2.getY()+h+Tile.DEFAULT_TILE_HEIGHT,0);
		bchTile[12]= new BeachTile(game,p2.getX()+7*s,p2.getY()+(h+Tile.DEFAULT_TILE_HEIGHT),0);
		bchTile[13]= new BeachTile(game,p1.getX()+2*s,p1.getY()+2*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		bchTile[14]= new BeachTile(game,p0.getX(),p0.getY()+3*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		bchTile[15]= new BeachTile(game,p0.getX()+3*s,p0.getY()+3*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		
		
		
		//Tuiles Forêt
		frstTile[0]= new ForestTile(game,p0.getX()+s,p0.getY(),0);
		frstTile[1]= new ForestTile(game,p0.getX()+3*s,p0.getY(),0);
		frstTile[2]= new ForestTile(game,p1.getX(),p1.getY(),0);
		frstTile[3]= new ForestTile(game,p2.getX()+s,p2.getY(),0);
		frstTile[4]= new ForestTile(game,p2.getX()+2*s,p2.getY(),0);
		frstTile[5]= new ForestTile(game,p2.getX()+5*s,p2.getY(),0);
		frstTile[6]= new ForestTile(game,p2.getX()+6*s,p2.getY(),0);
		frstTile[7]= new ForestTile(game,p2.getX()+7*s,p2.getY(),0);
		frstTile[8]= new ForestTile(game,p2.getX()+s,p2.getY()+(h+Tile.DEFAULT_TILE_HEIGHT),0);
		frstTile[9]= new ForestTile(game,p2.getX()+2*s,p2.getY()+(h+Tile.DEFAULT_TILE_HEIGHT),0);
		frstTile[10]= new ForestTile(game,p2.getX()+3*s,p2.getY()+(h+Tile.DEFAULT_TILE_HEIGHT),0);
		frstTile[11]= new ForestTile(game,p2.getX()+5*s,p2.getY()+(h+Tile.DEFAULT_TILE_HEIGHT),0);
		frstTile[12]= new ForestTile(game,p1.getX(),p1.getY()+2*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		frstTile[13]= new ForestTile(game,p1.getX()+4*s,p1.getY()+2*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		frstTile[14]= new ForestTile(game,p0.getX()+s,p0.getY()+3*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		frstTile[15]= new ForestTile(game,p0.getX()+2*s,p0.getY()+3*(h+Tile.DEFAULT_TILE_HEIGHT),0);
		
		
		
		//les Bateau
		tabBateau= new Bateau[8];
		
		bateau1Rouge = new Bateau(game,pRouge.getX(),pRouge.getY(),false);
		bateau1Rouge.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau1Rouge.setHeight(30);
		tabBateau[0]=new Bateau(game,pRouge.getX(),pRouge.getY(),false);
		tabBateau[0].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[0].setHeight(30);
		
		bateau2Rouge = new Bateau(game,pRouge.getX(),pRouge.getY()+40,false);
		bateau2Rouge.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau2Rouge.setHeight(30);
		tabBateau[1]= new Bateau(game,pRouge.getX(),pRouge.getY()+40,false);
		tabBateau[1].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[1].setHeight(30);
		
		bateau1Jaune = new Bateau(game,pJaune.getX(),pJaune.getY(),false);
		bateau1Jaune.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau1Jaune.setHeight(30);
		tabBateau[2]= new Bateau(game,pJaune.getX(),pJaune.getY(),false);
		tabBateau[2].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[2].setHeight(30);
		
		
		bateau2Jaune = new Bateau(game,pJaune.getX(),pJaune.getY()+40,false);
		bateau2Jaune.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau2Jaune.setHeight(30);
		tabBateau[3]= new Bateau(game,pJaune.getX(),pJaune.getY()+40,false);
		tabBateau[3].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[3].setHeight(30);
		
		
		bateau1Vert = new Bateau(game,pVert.getX(),pVert.getY(),false);
		bateau1Vert.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau1Vert.setHeight(30);
		tabBateau[4]= new Bateau(game,pVert.getX(),pVert.getY(),false);
		tabBateau[4].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[4].setHeight(30);
		
		bateau2Vert = new Bateau(game,pVert.getX(),pVert.getY()+40,false);
		bateau2Vert.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau2Vert.setHeight(30);
		tabBateau[5]= new Bateau(game,pVert.getX(),pVert.getY()+40,false);
		tabBateau[5].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[5].setHeight(30);
		
		
		bateau1Bleu = new Bateau(game,pBleu.getX(),pBleu.getY(),false);
		bateau1Bleu.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau1Bleu.setHeight(30);
		tabBateau[6]=new Bateau(game,pBleu.getX(),pBleu.getY(),false);
		tabBateau[6].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[6].setHeight(30);
		
		bateau2Bleu = new Bateau(game,pBleu.getX(),pBleu.getY()+40,false);
		bateau2Bleu.setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		bateau2Bleu.setHeight(30);
		tabBateau[7]= new Bateau(game,pBleu.getX(),pBleu.getY()+40,false);
		tabBateau[7].setWidth(Tile.DEFAULT_TILE_HEIGHT-8);
		tabBateau[7].setHeight(30);
	}
	
	
	
	
	//*******************************************************les fonctions utiles *******************************************************************
	
	//fonction qui nous permet de récuperer les coordonnées de la sourie et ça nous permet de debuguer
	public void afficherCoord(MouseManager mouseManager,Graphics g)
	{
		g.setColor(Color.BLUE);
		
		if(mouseManager.isLeftPressed() )
		{
			x =mouseManager.getMouseX();
			y =mouseManager.getMouseY();
			//System.out.println(x+"   " + y);
		}
			
		
		System.out.println(mouseManager.getMouseX()+"   " + mouseManager.getMouseY());
	}
	
	
	//la fonction qui transforme un point en NumBox qui répresente le numéro de ligne et de colonne de la case à laquelle
	//appartient ce point
	public NumBox PointToNumBox(int x,int y,ArrayList<Point> liste)
	{
		int l,c;
		Point p;
		p=new Point(x,y);
		
		
		l=RetournerColonne(p, liste);
		if(l%2==0)
			c=(p.getX()-497)/70;
		else
			c=(p.getX()-462)/70;
		NumBox numB = new NumBox(l,c);
		return numB;
		
		
	}
	
	//une fonction qui nous permet de verifier si un point donné est dans une case dont son point 
	//haut gauche est dans la liste des points 
	public boolean estDansCase(Point p1,Point p2,int wid,int hei)
	{
		boolean a = (p2.getX()>p1.getX() && p2.getX()<p1.getX()+wid);
		boolean b = (p2.getY()>p1.getY() && p2.getY()<p1.getY()+hei);
		if(a && b)
			return true;
		else 
			return false;
	}
	
	//récupere le numéro de la colonne de la case qui contient un point passé en parmétre
	public int RetournerColonne(Point p,ArrayList<Point> liste)
	{
		Point pRet =new Point(-1000,-1000) ;
		int res=-10000;
		for(int i=0;i<liste.size();i++)
		{
			if (estDansCase(liste.get(i),p,70,34))
			{
				pRet.setX(liste.get(i).getX());
				pRet.setY(liste.get(i).getY());
				res=i;
			}
			
		}
		
		return (res/12);
	}
	
	
	//******************************************************** la partie logique du jeu ************************
	//la méthode qui s'occupe du déplacement des entités
	public void bouger(MouseManager mouseManager)
	{
		Point pClicGauche;
		Point pClicDroit;
		NumBox numB1;
		NumBox numB2;

		//ici on récupére le clic gauche qui permet de selectionner un élement à déplacer 
		pClicGauche=recupererPointClicLeft(game.getMouseManager());
		
		//ici on récupére le clic droit qui permet de selectionner la case dans laquelle on veut ajouter un élement
		pClicDroit=recupererPointClicRight(game.getMouseManager());
		

		//on verifie  qu'il y a eu un clic gauche
		if(pClicGauche.getX() != -1)
		{
			//verification si le clic est sur un pion
			for(int i=0;i<40;i++)
			{
			
				if(estSurJoueur((int)explorateurs[i].getX(),(int)explorateurs[i].getY(),pClicGauche.getX(),pClicGauche.getY()))
				{
					res1=i;
					bool=true;
					break;
				}
				else
					res1=-1;	
			}
			
			//verification si le clic est sur un bateau
			for(int i=0;i<8;i++)
			{
				if(estSurBateau((int)tabBateau[i].getX(),(int)tabBateau[i].getY(),pClicGauche.getX(),pClicGauche.getY()))
				{
					res2=i;
					break;
				}
				else
					res2=-1;
			}
			
		}
		
		
		//verification qu'il y a eu un clic droit de la souris
		if(pClicDroit.getX() != -1)
		{
			//si le clic gauche était sur un joueur
			if(res1>=0 && bool)
			{
				//déplacement du joueur et mise à jour de sa case
				explorateurs[res1].setX(pClicDroit.getX());
				explorateurs[res1].setY(pClicDroit.getY());
				numB1=PointToNumBox(pClicDroit.getX(),pClicDroit.getY(),listeDesPointsHG);
				
				MyMap map = new MyMap(numB1, explorateurs[res1].getNumCouleurJoueur(),res1);
				tabNumMap.add(map);
				res1=-1;
				
			}
			
			//si le clic est sur un bateau
			if(res2>=0)
			{
				//verification si il y a des joueurs sur la même case que la tabeau 
				//puis deplacement du bateau
				tabBateau[res2].setX(pClicDroit.getX());
				tabBateau[res2].setY(pClicDroit.getY());
				numB1=PointToNumBox(pClicDroit.getX(),pClicDroit.getY(),listeDesPointsHG);
				//cases[numB.getLigne()][numB.getColonne()].setBateau(true);
				res2=-1;
				
				
			}
		}
			
	}
		
			
	
	
	
	
	//verifie si un point donné est dans   le plateau de jeu
	public boolean estDansPlateauJeu(Point p)
	{
		boolean a = (p.getX()>400&& p.getX()<950);
		boolean b = (p.getY()>100 && p.getY()<740);
		if(a && b)
			return true;
		else 
			return false;
	}
	
	//Verifie si le clic est sur un joueur
	public boolean estSurJoueur(int x0,int y0,int xClic,int yClic)
	{
		boolean a = (xClic>x0 & xClic<x0+18);
		boolean b = (yClic>y0 && yClic<y0+35);
		if(a && b)
			return true;
		else 
			return false;
	}
	
	//verifie si un clic est sur un bateau
	public boolean estSurBateau(int x0,int y0,int xClic,int yClic)
	{
		boolean a = (xClic>x0 && xClic<x0+Tile.DEFAULT_TILE_HEIGHT-8);
		boolean b = (yClic>y0 && yClic<y0+30);
		if(a && b)
			return true;
		else 
			return false;
	}

	
	
	//fonction qui permet de retourner la position du clic gauche de la sourie 
		public island.gfx.Point recupererPointClicLeft(MouseManager mouseManager)
		{
			island.gfx.Point p;
			p=new island.gfx.Point(-1,-1);
			
			if(mouseManager.isLeftPressed())
			{
				int xClic =game.getMouseManager().getMouseX();
				int yClic=game.getMouseManager().getMouseY();
			
				p.setX(xClic);
				p.setY(yClic);
				
				
				
			}
			
			
			return p;
		
		
		}
		
		//fonction qui permet de retourner la position du clic droit de la sourie 
		
		public Point recupererPointClicRight(MouseManager mouseManager)
		{
			Point p;
			p=new Point(-1,-1);
			
			if(mouseManager.isRightPressed())
			{
				int xClic =game.getMouseManager().getMouseX();
				int yClic=game.getMouseManager().getMouseY();
				p.setX(xClic);
				p.setY(yClic);
							
				
			}

			
			return p;
		
		
		}
		
		public void parcouriCasesContenantDesJoueurs(ArrayList<MyMap> map)
		{
			int s=0;
			//System.out.println("la taille est "+map.size());
			for(int i=0;i<map.size();i++)
			{
				if(map.get(i).getNumCouleurJoeur()==0)
						s++	;		
				
			}
			
			
		}
	

}