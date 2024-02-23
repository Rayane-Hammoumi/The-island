package modele;
import static java.lang.Math.abs;
import controleur.Controleur;


import javafx.scene.shape.Rectangle;

/**
 * 
 * La classe Requin correspond au pion requin. Elle herite de pion
 * @see pion
 */
public class Requin extends Pion{
	/**
	 * rectangle de type rectangle
	 */
	private Rectangle rectangle;
	
	/**
	 * Le constructeur de la classe Requin
	 */
    public Requin() {
    	rectangle= new Rectangle();
        nb_cases_parcourables=2;

    }
    
	/**
	 * Le constructeur de la classe Requin a partir d'un pion
	 */
    public Requin(Pion p) {
        this.nb_cases_parcourables= p.nb_cases_parcourables;
        this.position = p.nb_cases_parcourables;
        this.rectangle = p.getRectangle();
        this.coord_indexI = p.coord_indexI;
        this.coord_indexJ = p.coord_indexJ;
    }
    
    /**
     * Cette methode permet de recuperer le rectangle
	 * @return rectangle de type Rectangle
     */
    public Rectangle getRectangle()
    {
    	return rectangle;
    }
    
    /**
     * Cette methode correspond a l'action que va effectuer le requin
     * @param coordonnee1 un entier : les coordonnees de la case
     * @param coordonnee2 un entier : les coordonnees de la case
     */
	public void tue_explorateurs(int coordonnee1, int coordonnee2)
	{
		if(Controleur.p.plateau[coordonnee1][coordonnee2].nageur_present()) {
			for(int i=0;i<Controleur.p.plateau[coordonnee1][coordonnee2].listePions.size();i++) {
	            if(Plateau.plateau[coordonnee1][coordonnee2].listePions.get(i)instanceof Explorateur)
	            {
	            	Plateau.plateau[coordonnee1][coordonnee2].listePions.get(i).getCercle().setVisible(false);
					Plateau.plateau[coordonnee1][coordonnee2].listePions.remove(i);
					System.out.println("Le requin a mangé l'explo");
					
	            }	
			}
		}
    
	}
	
	/**
     * Cette methode permet de deplacer le requin sur le plateau
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
     */
	public String deplacer(int destination_i, int destination_j)
    {    
        if(Plateau.plateau[destination_i][destination_j].getTerrain() != null)
        {
            return "Le requin ne peut pas se dÃ©placer hors des cases de mer";
        }
            
        
       int deltaX=this.coord_indexI-destination_i;
       int deltaY=this.coord_indexJ-destination_j;
       int decalage1 = 0;
       int decalage2 = 0;
       
       if(this.coord_indexI > destination_i) {
    	   decalage1 = -1;
       }
       else if(this.coord_indexI < destination_i) {
    	   decalage1 = +1;
       }
       
       if(this.coord_indexJ > destination_j) {
    	   decalage2 = -1;
       }
       else if(this.coord_indexJ < destination_j) {
    	   decalage2 = +1;
       }
       
       
       if(abs(deltaX)+abs(deltaY)==2 && 
        Plateau.plateau[this.coord_indexI+decalage1][this.coord_indexJ+decalage2].contient_type("Explorateur")) 
       { //si le requin doit se deplacer de 2 cases
          
            destination_i = this.coord_indexI+decalage1;
            destination_j = this.coord_indexJ+decalage2;
       }
       
       String instruction=super.deplacer(destination_i, destination_j);

       this.tue_explorateurs(destination_i, destination_j);

       return instruction;
       
    }
	
	/**
     * Cette methode permet de deplacer le requin sur le plateau
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
     */
    public String deplacer_limite(int destination_i, int destination_j)
    {
        int depart_i = this.getCoord_indexI();
		int depart_j = this.getCoord_indexJ();

		if(!Plateau.plateau[depart_i][depart_j].getVoisins().contains(Plateau.plateau[destination_i][destination_j]))
        {
            return "Choisissez une case adjacente";
        }
	   else
       {
           String instruction=this.deplacer(destination_i, destination_j);

            return instruction;
       }
       
    }
	
}
