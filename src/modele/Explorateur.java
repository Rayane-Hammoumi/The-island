package modele;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

import controleur.Controleur;

/**
 * 
 * La classe Explorateur correspond aux pions explorateurs. Cette classe herite de la classe Pion.
 *
 */
public class Explorateur extends Pion{
	/**
	 * valeur de type int la valeur du pion explorateur
	 */
	private int valeur;
	
	/**
	 * couleur de type Type_couleur la couleur de l'explorateur
	 */
	private Type_couleur couleur;
	
	/**
	 * cercle de type Circle
	 */
	private Circle cercle;
	
	/**
	 * pion_place de type boolean nous indique que le pion a ete placé
	 */
	private boolean pion_place = false;
	/**
	 * Le constructeur de la classe Explorateur
	 * @param pValeur de type int la valeur de l'explorateur
	 * @param pCouleur de type Type_couleur la couleur de l'explorateur
	 */
	public Explorateur(int pValeur, Type_couleur pCouleur){
		valeur = pValeur;
		couleur = pCouleur;
		cercle = new Circle();
	}
	
	public String toString(){
		return "Explorateur "+couleur+ ", de valeur : "+ valeur;
	}
	
	/**
	 * Cette methode permet d'indiquer l'etat du pion (si le pion explorateur a ete place ou non)
	 * @return pion_place de type boolean
	 */
	public boolean is_pion_place() {
		return pion_place;
	}
	
	/**
	 * Cette methode permet d'indiquer que ce pion explorateur a ete place
	 * @return true, il a été placé
	 */
	public void place_pion() {
		this.pion_place = true;
	}
	
	/**
	 * Cette methode permet de recuperer l'attribut cercle
	 * @return cercle de type Circle
	 */
	public Circle getCercle() {
		return cercle;
	}
	
	/**
	 * Cette methode permet de recuperer l'attribut couleur de l'explorateur
	 * @return couleur de type Type_couleur
	 */
	public Type_couleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Cette methode permet de recuperer l'attribut valeur de l'explorateur
	 * @return valeur de type int
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * Cette methode permet de modifier l'attribut valeur avec une nouvelle valeur passï¿½ en parametre
	 * @param value un entier : la nouvelle valeur
	 */
	public void setValeur(int value)
	{
		this.valeur = value;
	}
	
	/**
     * Cette methode permet de deplacer l'explorateur sur le plateau
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
     */
	public String deplacer(int destination_i, int destination_j)
	{
		int coord_indexI = this.getCoord_indexI();
		int coord_indexJ = this.getCoord_indexJ();
		
		System.out.println("["+coord_indexI+","+coord_indexJ+"]");
		System.out.println("Resultat de : "+Plateau.plateau[coord_indexI][coord_indexJ].getVoisins().contains(Plateau.plateau[destination_i][destination_j]));
		
		if(Plateau.plateau[coord_indexI][coord_indexJ]==null)
		{
			return "Il n'y a pas de case Ã  cet endroit";
		}
	 
		List<String> listeVoisin = new ArrayList<String>();
		
		for(int k=0;k<Plateau.plateau[coord_indexI][coord_indexJ].getVoisins().size();k++) {
			listeVoisin.add(Plateau.plateau[coord_indexI][coord_indexJ].getVoisins().get(k).getidCase());
		}
		
		if(!listeVoisin.contains(Plateau.plateau[destination_i][destination_j].getidCase()))
		{
			return "Choisissez une case adjacente";
		}
		if(Plateau.plateau[destination_i][destination_j].listePions.size()==6)
		{
		 return "La case est pleine, deplacement impossible";
		}
				 
		Plateau.plateau[coord_indexI][coord_indexJ].retirer_pion(this);
		
		if(Plateau.plateau[destination_i][destination_j].contient_type("Requin")
		|| Plateau.plateau[destination_i][destination_j].contient_type("SerpentMer"))
		{
			return "\n La case de destination contient un requin ou un serpent de mer. L'explorateur s'est fait croquer.\n";
		}

		Plateau.plateau[destination_i][destination_j].ajouter_pion(this);
		this.setCoord_index(destination_i, destination_j);
			
	 
		return "Deplacement reussi";
	}

	//est appelee
	
	/**
	 * Cette methode permet de a l'explorateur qui se trouve dans la mer de se deplacer uniquement dans la mer grace a l'effet du dauphin
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
	 */
	public String nager_avec_dauphin(int destination_i, int destination_j)
	{

		if(Plateau.plateau[destination_i][destination_j].getTerrain() != null)
            {
                return "Le dauphin ne peut pas se deplacer hors des cases de mer";
            }

		String instruction=this.deplacer(destination_i, destination_j);
	
		return instruction;
	}

	/**
	 * Cette methode permet de verifier si l'explorateur est un nageur
	 * @return boolean true si c'est un nageur, false sinon
	 */
	public boolean est_nageur() {
		if(Controleur.p.plateau[coord_indexI][coord_indexJ] instanceof Tuile) {
			return false;
		} else {
			return true;
		}
		
	}
	
}