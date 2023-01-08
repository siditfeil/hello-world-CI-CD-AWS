package island.gfx;

public enum Enum_tuiles {
	
		//Tuiles a jouer immédiatement (vert)
	
		Requin_p("placer requin"),
		Baleine_p("placer baleine"),
		Bateau_p("placer bateau"),
		Tourbillon("tourbillon"),
		Volcan("volcan"),
		
		//Tuile a jouer au début du tour(rouge)
		
		Dauphin("dauphin"),
		Vent("vent"),
		Serpen_de_mer_d("deplacer serpent de mer"),
		Requin_d("deplacer requin"),
		Baleine_d("deplacer baleine"),
		
		//Tuile a jouer en dehors de votre tour en défense (rouge)
		
		Requin_x("retirer requin"),
		Baleine_x("retirer baleine"),
		
		//type de tuiles 
		
		Plage("plage"),
		Foret("foret"),
		Montagne("montagne");
		

	  private String name = "";

	   
	  /*** Constructor ***/
	  Enum_tuiles(String name){
	    this.name = name;

	  }
	   
	  public String toString(){
	    return this.name;
	  }

}
