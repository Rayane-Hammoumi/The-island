package modele;
import java.util.*;
import javafx.scene.shape.*;


/**
 * 
 * La classe Pion represente un pion
 *
 */
public class Pion {
	/**
	 * nb_cases_parcourables de type entier le nombre de deplacement que le pion peut effectuer 
	 */
	public int nb_cases_parcourables;
	/**
	 * position de type entier correspond a la position dans la case
	 */
	protected int position;//position dans la case, a changer a chaque deplacement
	
	/**
	 * coord_indexI de type int représente la coordonné i du pion dans le plateau
	 */
	protected int coord_indexI;
	
	/**
	 * coord_indexJ de type int représente la coordonné j du pion dans le plateau
	 */
	protected int coord_indexJ;
	
	
	
	/**
	 *  Le constructeur de la classe Pion
	 */
	public Pion(){
	}
	
	/**
	 * Cette methode permet de creer les different pions explorateurs
	 * @return le tableau des Explorateurs
	 */
	public Explorateur[] creation_pions_explorateur(){
		 Explorateur[] tabExplorateur = new Explorateur[40];
		 int [] valeurExplo = {1,1,1,2,2,3,3,4,5,6};
		 Type_couleur [] couleurExplo = {Type_couleur.BLEU, Type_couleur.JAUNE, Type_couleur.ROUGE, Type_couleur.VERT};
		 
		 int compt = 0;
		 for(int k = 0; k< couleurExplo.length; k++){
			 
			 for(int i=0; i< valeurExplo.length; i++){
					
				 Explorateur explo = new Explorateur(valeurExplo[i], couleurExplo[k]);
				 tabExplorateur[compt] = explo;
				 compt++;
			 }
		 
		 }
		return tabExplorateur;
	}
	
	/**
	 * Cette methode permet au pion de se deplacer
	 * @param depart_i de type int la coordonnee de depart 
	 * @param depart_j de type int la coordonnee de depart 
	 * @param destination_i de type int la coordonnee de destination 
	 * @param destination_j de type int la coordonnee de destination 
	 */
	
	/**
	 * Cette methode permet de recuperer le nombre de pion bateau
	 * @return int
	 */
	public int nbr_pion_bateau() {
		return -1;
	}
	
	/**
	 * Cette methode permet de recuperer la position du pion
	 * @return position de type int
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Cette methode permet de recuperer l'index i dans le plateau du pion
	 * @return coord_indexI
	 */

	public int getCoord_indexI() {
		return coord_indexI;
	}
	
	/**
	 * Cette methode permet de recuperer l'index j dans le plateau du pion
	 * @return coord_indexJ
	 */
	public int getCoord_indexJ() {
		return coord_indexJ;
	}
	
	/**
	 * Cette methode permet de modifier les coordonnees du pion.
	 * @param i un entier la coordonnee du pion
	 * @param j un entier la coordonnee du pion
	 */
	public void setCoord_index(int i, int j) {
		this.coord_indexI = i;
		this.coord_indexJ = j;
	}
	
	/**
	 * Cette methode permet de modifier la position du pion
	 * @param position de type int la nouvelle position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * Cette methode permet de recuperer le rectangle
	 * @return Rectangle
	 */
	public Rectangle getRectangle() {
		return null;
	}
	
	/**
	 * Cette methode permet de recuperer le cercle
	 * @return Cercle
	 */
	public Circle getCercle() {
		return null;
	}
	
	/**
	 * Cette methode permet de recuperer la couleur
	 * @return Type_couleur
	 */
	public Type_couleur getCouleur() {
		return null;
	}

	
	/**
     * Cette methode permet de deplacer le pion sur le plateau
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
     */
	public String deplacer(int destination_i, int destination_j)
    {
       if(Plateau.plateau[coord_indexI][coord_indexJ]==null)
       {
           return "Il n'y a pas de case Ã  cet endroit";
       }

	   else if(Plateau.plateau[destination_i][destination_j].listePions.size()==6)
	   {
		return "La case est pleine, deplacement impossible";
	   }
	   else {
		   
		   Plateau.plateau[coord_indexI][coord_indexJ].retirer_pion(this);
		   Plateau.plateau[destination_i][destination_j].ajouter_pion(this);
		   this.setCoord_index(destination_i, destination_j);
	   }

	   return "Deplacement reussi";
       
    }
	
	public int getValeur() {
		return 0;
	}
	
	/**
     * Cette methode permet de deplacer le pion sur le plateau
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
	
	
	public int bateau_est_vide()
	{
		return 2; //signifie que ce pion n'est pas un bateau
	}

	//retourne null quand le pion n'est pas un bateau
	public List<Pion> get_Pions_a_bord()
	{
		return null;
	}

	//ne fait rien si le pion n'est pas un bateau
	public void retirer_pions_a_bord()
	{
	}
}