package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * La classe effet correspond aux effets des différents tuiles
 *
 */
public class Effet{
	/**
	 * effet de type Type_effet l'effet de la tuile
	 */
	private Type_effet effet; 
	
	/**
	 * couleur de type Type_couleur la couleur de l'effet
	 */
	private Type_couleur couleur;
	
	/**
	 * Cette méthode permet de récupérer l'effet 
	 * @return effet de type Type_effet 
	 */
	public Type_effet getEffet() {
		return effet;
	}
	
	/**
	 * Cette méthode permet de récupérer la couleur 
	 * @return couleur la couleur de l'effet
	 */
	public Type_couleur getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Cette méthode permet de modifier la couleur de l'effet
	 * @param couleur de type Type_couleur la nouvelle couleur
	 */
	public void setCouleur(Type_couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Cette méthode permet de modifier l'effet
	 * @param effet de type Type_effet le nouvel effet
	 */
	public void setEffet(Type_effet effet) {
		this.effet = effet;
	}
	
	/**
	 * Le constructeur de la classe Effet
	 * @param effet de type Type_effet
	 * @param couleur de type Type_couleur
	 */
	public Effet(Type_effet effet, Type_couleur couleur) {
		this.effet = effet;
		this.couleur = couleur;
	}
	
	 // TUILE TERRAIN
	/**
	 * Cette méthode permet de créer les tuiles avec les effets plages
	 * @return List Effet la liste des effets
	 */
	 public static List<Effet> creation_effets_plage(){
		 int i;
		 List<Effet> liste_effet = new ArrayList<Effet>();
		 for(i=0; i<3;i++) {			 
			 liste_effet.add(new Effet(Type_effet.BALEINE_IMMEDIAT,Type_couleur.VERT));
		 }
		 for(i=0; i<3;i++) {			 
			 liste_effet.add(new Effet(Type_effet.REQUIN_IMMEDIAT,Type_couleur.VERT));
		 }
		 liste_effet.add(new Effet(Type_effet.BATEAU_IMMEDIAT,Type_couleur.VERT));
		 for(i=0; i<2;i++) {			 
			 liste_effet.add(new Effet(Type_effet.BALEINE_DIFFERE,Type_couleur.ROUGE));
		 }
		 for(i=0; i<3;i++) {			 
			 liste_effet.add(new Effet(Type_effet.DAUPHIN,Type_couleur.ROUGE));
		 }
		 liste_effet.add(new Effet(Type_effet.SERPENT_MER,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.REQUIN_DIFFERE,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.BALEINE_DIFFERE,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.ANTI_REQUIN,Type_couleur.ROUGE));
		 return liste_effet;
	 }
	 
	 /**
	  * Cette méthode permet de créer les tuiles avec les effets foret
	  * @return List Effet la liste des effets
	  */
	 public static  List<Effet> creation_effets_foret(){
		 int i;
		 List<Effet> liste_effet = new ArrayList<>();
		 for(i=0; i<2;i++) {			 
			 liste_effet.add(new Effet(Type_effet.BALEINE_IMMEDIAT,Type_couleur.VERT));
		 }
		 for(i=0; i<2;i++) {
			 liste_effet.add(new Effet(Type_effet.REQUIN_IMMEDIAT,Type_couleur.VERT));
		 }
		 for(i=0; i<3;i++) {
			 liste_effet.add(new Effet(Type_effet.BATEAU_IMMEDIAT,Type_couleur.VERT));
		 }
		 for(i=0; i<3;i++) {
			 liste_effet.add(new Effet(Type_effet.TOURBILLON,Type_couleur.VERT));
		 }
		 liste_effet.add(new Effet(Type_effet.DAUPHIN,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.SERPENT_MER,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.REQUIN_DIFFERE,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.BALEINE_DIFFERE,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.ANTI_REQUIN,Type_couleur.ROUGE));
		 for(i=0; i<2;i++) {
			 liste_effet.add(new Effet(Type_effet.ANTI_BALEINE,Type_couleur.ROUGE));
		 }
		 return liste_effet;
	 }
	 
	 /**
	  * Cette méthode permet de créer les tuiles avec les effets montagne
	  * @return List Effet la liste des effets
	  */
	 public static List<Effet> creation_effets_montagne(){
		 int i;
		 List<Effet> liste_effet = new ArrayList<>();
		 liste_effet.add(new Effet(Type_effet.REQUIN_IMMEDIAT,Type_couleur.VERT));
		 for(i=0; i<4;i++) {
			 liste_effet.add(new Effet(Type_effet.TOURBILLON,Type_couleur.VERT));
		 }
		 liste_effet.add(new Effet(Type_effet.VOLCAN,Type_couleur.VERT));
		 liste_effet.add(new Effet(Type_effet.ANTI_REQUIN,Type_couleur.ROUGE));
		 liste_effet.add(new Effet(Type_effet.ANTI_BALEINE,Type_couleur.ROUGE));
		 
		 return liste_effet;
	 }
	 

	 
}
