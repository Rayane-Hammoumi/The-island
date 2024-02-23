package modele;
import java.util.*;
import controleur.Controleur;
import javafx.scene.shape.Polygon;

/**
 * La classe Case represente une case du plateau. 
 * 
 *
 */
public  class Case {

	/**
	 * idCase de type String est l'identifiant de la case
	 */
	protected String idCase;

	/**
	 * voisins est une liste de Case qui contient les voisins de la case
	 */
	protected List<Case> voisins;

	/**
	 * listePions est une liste de Pion qui contient les Pion qui se trouvent sur la case
	 */
	protected List<Pion> listePions;

	/**
	 * hexagone de type Polygone
	 */
	protected Polygon hexagone;
	
	/**
	 * indexI de type int
	 */
	protected int indexI;
	
	/**
	 * indexJ de type int
	 */
	protected int indexJ;
	
	
	/**
	 * Cette methode renvoie l'attribut indexI
	 * @return indexI de type int
	 */
	public int getIndexI() {
		return indexI;
	}
	
	/**
	 * Cette methode permet de modifier indexI
	 * @param indexI la nouvelle valeur de type int
	 */
	public void setIndexI(int indexI) {
		this.indexI = indexI;
	}
	
	/**
	 * Cette methode renvoie l'attribut indexJ
	 * @return indexJ de type int
	 */
	public int getIndexJ() {
		return indexJ;
	}
	
	/**
	 * Cette methode permet de modifier indexJ
	 * @param indexJ la nouvelle valeur de type int
	 */
	public void setIndexJ(int indexJ) {
		this.indexJ = indexJ;
	}
	
	/**
	 * Cette methode permet de verifier si un voisin est une case mer
	 * @return true s'il y a une case mer, false sinon
	 */
	public boolean voisinMer() {
		for(int i = 0; i<voisins.size();i++) {
			if(voisins.get(i).getTerrain() == null) {
				return true;
			}
		}
		return false;
	}

	public String printVoisin() {
		String ret = "";
		for(int i=0; i<voisins.size();i++) {
			ret += voisins.get(i).getidCase()+" ";
		}
		return ret;
	}
	
	/**
	 * Le constructeur de la classe Case
	 */
	public Case() {
		this.voisins = new ArrayList<>();
		listePions = new ArrayList<>();
	}
	
	/**
	 * Le constructeur de la classe Case. Cette methode permet d'instancier un objet Case avec un identifiant
	 * @param id de type String : l'identifiant
	 */
	public Case(String id) {
		this.idCase = id;
		this.voisins = new ArrayList<>();
		listePions = new ArrayList<>();
	}
	
	/**
	 * Le constructeur de la classe Case. Cette methode permet d'instancier un objet Case avec ses attributs
	 * @param idCase l'identifiant de la case de type string
	 * @param voisins la liste de case voisine 
	 * @param listePions la liste de Pion sur la case 
	 * @param hexagone de type Polygon
	 */
	public Case(String idCase, List<Case> voisins, List<Pion> listePions,Polygon hexagone){
		this.idCase = idCase;
		this.voisins = voisins;
		this.listePions = listePions;
		this.hexagone = hexagone;
	}
	
	/**
	 * Cette methode permet de recuperer l'attribut idCase
	 * @return l'identifiant de type string
	 */
	public String getidCase(){
		return this.idCase;
	}
	
	/**
	 * Cette methode permet de recuperer la liste de voisins
	 * @return la liste de voisins
	 */
	public List<Case> getVoisins(){
		return voisins;
	}
	
	/**
	 * Cette methode permet de recuperer le terrain de la case
	 * @return Terrain
	 */
	public Terrain getTerrain() {
		return null;
	}

	/**
	 * Cette methode permet de recuperer l'effet de la case
	 * @return Effet
	 */
	public Effet getEffet() {
		return null;
	}
	
	/**
	 * Cette methode permet de modifier l'identifiant d'une case
	 * @param idCase le nouvel identifiant de type string
	 */
	public void setidCase(String idCase) {
		this.idCase = idCase;
	}
	
	/**
	 * Cette methode permet de modifier la liste des voisins d'une case
	 * @param voisins la nouvel liste des voisins
	 */
	public void setVoisins(List<Case> voisins) {
		this.voisins = voisins;
	}
	
	// Gestion Voisins
	/**
	 * Cette methode permet d'ajouter un voisin a la liste des voisins
	 * @param c le voisin a rajouter de type Case
	 */
	public void ajouter_voisin(Case c) {
		voisins.add(c);
	}

	//ajouter un pion a la liste des pions
	/**
	 * Cette methode permet d'ajouter un pion a la liste de pions
	 * @param p le nouveau pion a ajoutï¿½ 
	 */
	public void ajouter_pion(Pion p) {
		int pos;
		if(p != null) {
			listePions.add(p);
			if(!(p instanceof Bateau))//pour noter la position des pions sauf les bateaux
			{
				pos =listePions.indexOf(p);
				p.setPosition(pos+1);
			}
		}
	}

	/**
	 * Cette methode permet de vider la liste des pions
	 */
	public void vider_case() {
		listePions.clear();
	}

	/**
	 * Cette methode permet de retirer un pion p (donnee en parametre), de la liste de pions de la case
	 * @param p le pion a retire
	 */
	public void retirer_pion(Pion p)
	{		
		if(listePions.contains(p))
		{
			listePions.remove(p);
		}
		
		else
		{
			System.out.println("\nLa case ne contient pas le pion demandÃ©\n");
		}
	}
	
	
	/**
	 * Cette methode permet de recuperer la liste de pions de la case
	 * @return listePions la liste de pion
	 */
	public List<Pion> getPions(){
		return listePions;
	}
	
	/**
	 * Cette methode permet d'afficher les voisins de la case
	 */
	public void lister_voisin() {
		int i;
		for(i=0; i < this.voisins.size();i++) {
			System.out.print(i + " - " +this.voisins.get(i).getidCase()+ " | ");
		} 
	}
	
	/**
	 * Cette methode permet de recuperer l'hexagone de la case
	 * @return hexagone de type Polygon
	 */
	public Polygon getHexagone() {
		return hexagone;
	}

	/**
	 * Cette methode permet de modifier l'hexagone
	 * @param hexagone de type Polygon
	 */
	public void setHexagone(Polygon hexagone) {
		this.hexagone= hexagone;
	}
	
	/**
	 * Cette methode permet de verifier s'il existe bien une classe (donnee en parametre) dans la liste des pions
	 * @param nom_classe la classe que l'on veut rechercher
	 * @return un boolean true si la classe rechercher est presente dans la liste des pions, false sinon
	 */
	public boolean contient_type( String nom_classe)
	{
		int i=0;
		while(i<=this.listePions.size())
        {//on parcourt la liste de pions pour savoir si la case contient la classe demandee
            if(this.listePions.getClass().getSimpleName().equals(nom_classe))
            {
                return true;
			}
			else
            {
                i++;
            }
		}

		return false;
	}
	
	/**
	 * Cette methode permet d'ajouter un bateau
	 * @return String un message
	 */
	public String ajouter_bateau()
	{
		if(this.listePions.size()<=3)
		{
			Bateau bateau = new Bateau();
			Marin marin1 = new Marin();
			Marin marin2 = new Marin();
	
			this.ajouter_pion(bateau);
			this.ajouter_pion(marin1);
			this.ajouter_pion(marin2);

			return "Bateau placÃ©";
		}
		else
		{
			return "Il y a dejaï¿½ au minimum 3 pions dans cette case. Impossible de placer un bateau dedans. Veuillez choisir une autre destination.";
		}
		
	}
	
	/**
	 * Cette methode permet de retirer un bateau
	 */
	public void retirer_bateau()
	{
		for(int i=0; i<this.listePions.size(); i++)
        {
			if(this.listePions.get(i)instanceof Bateau)
			{
				this.retirer_pion(this.listePions.get(i));
			}
			else if(this.listePions.get(i)instanceof Marin)
			{
				this.retirer_pion(this.listePions.get(i));
			}
		}
	}
	
	/**
	 * Cette methode permet de verifier si un bateau est présent sur une case
	 * @return true s'il y a un bateau, false sinon
	 */
	public boolean bateau_present() {
		if(listePions.isEmpty()) 
		{
			return false;
		}
		
		for(int i = 0; i<listePions.size(); i++)
		{
			if(listePions.get(i) instanceof Bateau) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Cette methode permet de verifier si un nageur est présent sur une case
	 * @return true s'il y a un nageur, false sinon
	 */
	public boolean nageur_present() {
		if(listePions.isEmpty()) 
		{
			return false;
		}
		
		for(int i = 0; i<listePions.size(); i++)
		{
			if(listePions.get(i) instanceof Explorateur) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Cette methode permet de mettre a jour les voisins d'une case
	 */
	public void update_voisin() {
		for(int i=0; i< voisins.size(); i++) {
			 voisins.get(i).update_v();
		}
	}
	
	/**
	 * Cette methode permet de mettre a jour les voisins d'une case
	 */
	public void update_v() {
		for(int i=0; i< voisins.size(); i++) {
			if(Controleur.p.plateau[voisins.get(i).getIndexI()][voisins.get(i).getIndexJ()] != null) {
				voisins.set(i,Controleur.p.plateau[voisins.get(i).getIndexI()][voisins.get(i).getIndexJ()]);				
			}
		}
	}
}

