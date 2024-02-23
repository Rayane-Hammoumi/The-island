package modele;

import controleur.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 
 * La classe Joueur correspond aux joueurs
 *
 */
public class Joueur{
	/**
	 * pseudo de type string le pseudo du joueur
	 */
	private String pseudo;
	
	/**
	 * main la liste de Tuile qui est la main du joueur
	 */
	private List<Tuile> main = new ArrayList<>();
	
	/**
	 * score de type int, le score du joueur
	 */
	private int score;

	/**
	 * Couleur du joueur
	 */
	private Type_couleur couleur;

	/**
	 * Liste des explorateurs à placer sur le plateau
	 */
	private List<Explorateur> explo_placer = new ArrayList<>();
	
	/**
	 * Liste des bateaux à placer sur le plateau
	 */
	private List<Bateau> bateau_placer = new ArrayList<>();
	
	/**
	 * Cette methode permet de recuperer un explorateur de la liste des explorateurs
	 * @param index un entier : l'indice a laquelle se trouve l'explorateur
	 * @return Explorateur l'explorateur recherche
	 */
	public Explorateur get_explo_placer(int index) 
	{
		return explo_placer.get(index);
	}
	
	/**
	 * Cette methode permet de supprimer un bateau de la liste des bateau
	 * @param index un entier : l'indice a laquelle se trouve le bateau
	 */
	public void delete_bateau_placer(int index) 
	{
		bateau_placer.remove(index);
	}
	
	/**
	 * Cette methode permet de recuperer un bateau de la liste des bateau
	 * @param index un entier : l'indice a laquelle se trouve le bateau
	 * @return Bateau le bateau recherche
	 */
	public Bateau get_bateau_placer(int index) 
	{
		System.out.println("trest");
		return bateau_placer.get(index);
	}
	
	/**
	 * Cette methode permet de supprimer un explorateur de la liste des explorateurs
	 * @param index un entier : l'indice a laquelle se trouve l'explorateur
	 */
	public void delete_explo_placer(int index) 
	{
		explo_placer.get(index).place_pion();
		//get_explo_placer(index).getValeur()
		//explo_placer.remove(index);
	}

	/**
	 * Cette methode permet de supprimer une tuile que l'on vient d'utiliser de la main
	 * @param index un entier : l'indice a laquelle se trouve la tuile
	 */
	public void delete_tuile_utilise(int index) {
		main.remove(index);
	}
	
	/**
	 * Le constructeur de la classe Joueur
	 * @param pPseudo le pseudo du joueur
	 */
	public Joueur(String pPseudo){
		pseudo = pPseudo;
	}
	
	/**
	 * Cette méthode permet d'augmenter le score du joueur quand un pion explorateur atteint une ile
	 * @param explo l'explorateur
	 */
	public void sauver_joueur(Pion explo)
	{
		score += explo.getValeur();
	}
	
	/**
	 * Cette méthode permet de récupérer la couleur du joueur
	 */
	public Type_couleur getCouleur() 
	{
		return couleur;
	}
	
	/**
	 * Cette méthode permet de modifier la couleur du joueur
	 */
	public void setCouleur(Type_couleur couleur) 
	{
		this.couleur = couleur;
	}
	
	/**
	 * Cette méthode permet de récupérer la liste des explorateurs a placer
	 * @return
	 */
	public List<Explorateur> getExplo_placer() {
		return explo_placer;
	}

	/**
	 * Cette méthode permet d'initialiser les explorateurs. Ces explorateurs sont stockes dans la liste des explorateurs explo_placer
	 */
	public void initialisation_explo_placer() {
		explo_placer.add(new Explorateur(1,this.couleur));
		explo_placer.add(new Explorateur(1,this.couleur));
		explo_placer.add(new Explorateur(1,this.couleur));
		explo_placer.add(new Explorateur(2,this.couleur));
		explo_placer.add(new Explorateur(2,this.couleur));
		explo_placer.add(new Explorateur(3,this.couleur));
		explo_placer.add(new Explorateur(3,this.couleur));
		explo_placer.add(new Explorateur(4,this.couleur));
		explo_placer.add(new Explorateur(5,this.couleur));
		explo_placer.add(new Explorateur(6,this.couleur));
	}
	
	/**
	 * Cette méthode permet d'initialiser les bateau. Ces bateau sont stockes dans la liste des bateau bateau_placer
	 */
	public void initialisation_bateau_placer() {
		for(int i =0;i<2;i++) 
		{
			bateau_placer.add(new Bateau());
		}
	}
	
	/**
	 * Cette méthode permet de choisir une tuile grâce a des coordonnées
	 * @param p le plateau de type Plateau
	 * @param coordX de type int
	 * @param coordY de type int
	 * @return un entier  1 = VOLCAN, 2 = BALEINE_IMMEDIAT, 3 = TOURBILLON, 4 = REQUIN_IMMEDIAT, 5 = BATEAU_IMMEDIAT
	 */
	public int selectionner_tuile(int coordX, int coordY) {
		Tuile tuile_select = Controleur.p.selection_tuile(coordX, coordY); //Controleur.p.selection_tuile(coordX,coordY);
		if(tuile_select == null) {
			return -1;
		}
		
		int ret = 0;
		if(tuile_select.getEffet().getCouleur() == Type_couleur.ROUGE){
			//Effet differe: on ajoute à la main
			System.out.println(""+tuile_select.getEffet().getEffet());
			main.add(tuile_select);
			return ret;
		} else {
			switch(tuile_select.getEffet().getEffet()) {
			case VOLCAN:
				System.out.println("Volcan");
				ret = 1;
				break;
			case BALEINE_IMMEDIAT:
				System.out.println("Baleine immédiat");
				ret = 2;
				break;
			case TOURBILLON:
				System.out.println("Tourbillon");
				ret = 3;
				break;
			case REQUIN_IMMEDIAT:
				System.out.println("Requin immédiat");
				ret = 4;
				break;
			case BATEAU_IMMEDIAT:
				System.out.println("Bateau immédiat");
				ret = 5;
				break;
			default:
				System.out.println("Nothing");
				break;
			}	
		}
		Controleur.p.plateau[coordX][coordY].update_voisin();
		return ret;
	}
	
	/**
	 * Cette méthode permet d'ajouter une tuile passé en parametre a la main du joueur
	 * @param t de type Tuile, la tuile a ajouté a la main
	 */
	public void addMain(Tuile t) {
		main.add(t);
	}
	
	/**
	 * Cette méthode permet savoir si tout les pions explorateurs du joueur ont été placé
	 * @return true s'il ont été placé, false sinon
	 */
	public boolean is_explo_placer_empty() {
		for(int i=0;i<explo_placer.size();i++) {
			if(!explo_placer.get(i).is_pion_place()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Cette méthode permet de récupérer le pseudo du joueur
	 * @return pseudo de type String
	 */ 
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Cette méthode permet de récupérer le nombre d'explorateur a placé
	 * @return
	 */
	public int get_size_explo_placer() {
		return explo_placer.size();
	}
	
	/**
	 * Cette méthode permet de recuperer l'effet d'une tuile
	 * @param index de type int
	 * @return Type_effet 
	 */
	public Type_effet utiliser_tuile(int index) {
		return main.get(index).getEffet().getEffet();
	}
	
	/**
	 * Cette méthode permet de récuperer les effets de la main du joueur
	 * @return List de Type_effet la lsite contenant les effets
	 */
	public List<Type_effet> getListEffetMain(){
		List<Type_effet> list_effet = new ArrayList<Type_effet>();
		for(int i=0; i<this.main.size();i++) {
			list_effet.add(this.main.get(i).getEffet().getEffet());
		}
		
		return list_effet;
	}
	
	/**
	 * Cette méthode indique si la main contient seulement des tuiles de defense
	 * @return true si la main contient seulement des tuiles de defense, false sinon
	 */
	public boolean main_seulement_defense() {
		boolean juste_defense=true;
		for(int i=0; i<this.main.size();i++) {
			if(this.main.get(i).getEffet().getEffet() !=  Type_effet.ANTI_BALEINE) {
				if(this.main.get(i).getEffet().getEffet() !=  Type_effet.ANTI_REQUIN) {
					juste_defense = false;	
				}
			}
		}
		return  juste_defense;
	}
	
	/**
	 * Cette methode permet de recuperer la main du joueur
	 * @return la liste de Tuile qui est la main du joueur
	 */
	public List<Tuile> getMain() {
		return main;
	}
	
	/**
	 * Cette methode indique si la main du joueur est vide
	 * @return true si la main est vide, false sinon
	 */
	public boolean main_vide() {
		return main.isEmpty();
	}
	
	
	
	

}