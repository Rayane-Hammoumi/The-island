package modele;

import javafx.scene.shape.Rectangle;
import controleur.Controleur;

/**
 * 
 * La classe SerpentMer correspond au pion SerpentMer. Elle herite de pion
 * @see pion
 *
 */
public class SerpentMer extends Pion{
	/**
	 * rectangle de type rectangle
	 */
    private Rectangle rectangle;
    
    /**
	 * Le constructeur de la classe SerpentMer
	 */
    public SerpentMer() {
    	rectangle= new Rectangle();
        nb_cases_parcourables=1;
    }
    
    /**
	 * Le constructeur de la classe SerpentMer a partir d'un pion
	 */
    public SerpentMer(Pion p) {
    	this.rectangle= p.getRectangle();
    	this.nb_cases_parcourables=p.nb_cases_parcourables;
    	this.position = p.nb_cases_parcourables;
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
    
  //retire du jeu les explorateurs et le bateau fréquenté de la case s'il y en a un 
    /**
     * Cette methode permet au serpent de vider une case
     * @param coordonnee1 de type int
     * @param coordonnee2 de type int
     */
    public void serpent_vide_case(int coordonnee1, int coordonnee2)
    {
    	if(Controleur.p.plateau[coordonnee1][coordonnee2]==null) {
    		return;
    	}
    	for(int i=0; i<Controleur.p.plateau[coordonnee1][coordonnee2].listePions.size();i++)  
    	{
    		 if(Plateau.plateau[coordonnee1][coordonnee2].listePions.get(i)instanceof Explorateur)
             {
    			Plateau.plateau[coordonnee1][coordonnee2].listePions.get(i).getCercle().setVisible(false);
 				Plateau.plateau[coordonnee1][coordonnee2].listePions.remove(i);
             }
             else if(Plateau.plateau[coordonnee1][coordonnee2].listePions.get(i).bateau_est_vide()==0)
             {
                 Plateau.plateau[coordonnee1][coordonnee2].listePions.remove(i);
             }
    	}
    }
    
    /**
     * Cette methode permet de deplacer le serpent de mer sur le plateau
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
     */
    public String deplacer(int destination_i, int destination_j)
    {
        if(Plateau.plateau[destination_i][destination_j].getTerrain() != null)
            {
                return "Le serpent ne peut pas se deplacer hors des cases de mer";
            } 
       
        String instruction=super.deplacer(destination_i, destination_j);

       this.serpent_vide_case(destination_i, destination_j);

       return instruction;

   }
    
    /**
     * Cette methode permet de deplacer le serpent de mer sur le plateau
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