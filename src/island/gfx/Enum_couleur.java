package island.gfx;

public enum Enum_couleur {
	
	Rouge("rouge"),
	Jaune("jaune"),
	Bleu("bleu"),
	Vert("vert");

  private String name = "";

   
  /*** Constructor ***/
  Enum_couleur(String name){
    this.name = name;

  }
  
  public String toString(){
    return name;
  }

}