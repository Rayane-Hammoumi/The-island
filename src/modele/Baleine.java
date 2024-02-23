package modele;
import javafx.scene.shape.Rectangle;
import controleur.Controleur;

/**
 * 
 * La classe Baleine represente le pion baleine. Elle herite de la classe pion
 * @see Pion
 */
public class Baleine extends Pion{

    /**
	 * rectangle de type Rectangle
	 */
	private Rectangle rectangle;

    /**
	 * Le constructeur de la classe Baleine
	 */
    public Baleine() {
    	rectangle= new Rectangle();
        nb_cases_parcourables=3;
    }
    
    /**
	 * Le constructeur de la classe Baleine � partir d'un pion
	 */
    public Baleine(Pion p)
    {
        this.nb_cases_parcourables= p.nb_cases_parcourables;
        this.position = p.nb_cases_parcourables;
        this.rectangle = p.getRectangle();
        this.coord_indexI = p.coord_indexI;
        this.coord_indexJ = p.coord_indexJ;
    }

    /**
     * Cette methode permet de recuperer l'attribut rectangle
     * @return rectangle de type Rectangle
     */
    public Rectangle getRectangle()
    {
    	return rectangle;
    }
    
    /**
     * Cette methode est l'action que va effectuer la baleine
     * @param coordonnee1 de type int
     * @param coordonnee2 de type int
     */
    public void coule_bateau_frequente(int coordonnee1, int coordonnee2)
    {//verifie si une case contient un bateau frequente, si oui, retire le bateau du jeu et les pions a bord deviennent nageurs

    	for(int i=0; i<=Controleur.p.plateau[coordonnee1][coordonnee2].listePions.size();i++) {
    		if(Controleur.p.plateau[coordonnee1][coordonnee2].bateau_present()) 
    		{
    			if(Controleur.p.plateau[coordonnee1][coordonnee2].listePions.get(i).bateau_est_vide()==0)
                {//sinon si le pion est un bateau frequente
                    Controleur.p.plateau[coordonnee1][coordonnee2].listePions.get(i).retirer_pions_a_bord();
                    
                    if(Controleur.p.plateau[coordonnee1][coordonnee2].contient_type("Requin"))
                    {//Les nageurs, ils meurent s'il y a un requin
                        Controleur.p.plateau[coordonnee1][coordonnee2].listePions.get(i).retirer_pions_a_bord();
                    }

                    else{//les pions à bord deviennent nageurs
                        Controleur.p.plateau[coordonnee1][coordonnee2].listePions.addAll(Controleur.p.plateau[coordonnee1][coordonnee2].listePions.get(i).get_Pions_a_bord());
                        Controleur.p.plateau[coordonnee1][coordonnee2].listePions.get(i).retirer_pions_a_bord();
                    }
                }
        	}		
    	}	
    }
    //à appeler plusieurs fois, 
    
    /**
     * Cette methode permet de deplacer la baleine sur le plateau
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
     */
    public String deplacer(int destination_i, int destination_j)
    {
        if(Controleur.p.plateau[destination_i][destination_j].getTerrain() != null)
            {
                return "La baleine ne peut pas se deplacer hors des cases de mer";
            } 

            String instruction=super.deplacer(destination_i, destination_j);

            this.coule_bateau_frequente(destination_i, destination_j);

           return instruction;

	}

    //permet de se deplacer uniquement dans une case adjacente
    public String deplacer_limite(int destination_i, int destination_j)
    {
        int depart_i = this.getCoord_indexI();
		int depart_j = this.getCoord_indexJ();
        
        if(!Controleur.p.plateau[depart_i][depart_j].getVoisins().contains(Controleur.p.plateau[destination_i][destination_j]))
        {
            return "Choisissez une case adjacente";
        }
        else {

            String instruction=this.deplacer(destination_i, destination_j);

            return instruction;
        }
    }
    
}
