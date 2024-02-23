package modele;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Polygon;

/**
 * 
 * La classe Tuile correspond a une Tuile. Elle h�rite de Case
 *
 */
public class Tuile extends Case{
	//private String idCase = super.getidCase();
	//private List<Case> voisins = super.getVoisins();
	/**
	 * description de type string repr�sente la description de la tuile
	 */
	private String description;
	
	/**
	 * terrain de type Terrain le type de terrain de la tuile
	 */
	private Terrain terrain;
	
	/**
	 * effet de type Effet l'effet de la tuile
	 */
	private Effet effet;
	
	/**
	 * Cette methode permet de r�cup�rer l'attribut terrain de la tuile
	 * @return terrain de type Terrain
	 */
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	/**
	 * Cette methode permet de r�cup�rer l'attribut effet de la tuile
	 * @return effet de type Effet
	 */
	public Effet getEffet() {
		return this.effet;
	}
	
	/**
	 * Cette methode permet de modifier le terrain de la tuile
	 * @param terrain de type Terrain le nouveau terrain
	 */
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	/**
	 * Cette methode permet de modifier l'effet de la tuile
	 * @param effet de type Effet le nouvel effet
	 */
	public void setEffet(Effet effet) {
		this.effet = effet;
	}
	
	/**
	 * Le constructeur de la classe Tuile
	 * @param terrain de type Terrain
	 * @param effet de type Effet
	 */
	public Tuile(Terrain terrain, Effet effet){
		this.terrain = terrain;
		this.effet = effet;
	}
	
	/**
	 * Le constructeur de la classe Tuile
	 * @param idCase de type String l'identifiant de la tuile
	 * @param terrain de type Terrain le terrain de la tuile
	 * @param effet de type Effet l'effet de la tuile
	 * @param voisins la liste des voisins de la tuile
	 * @param listePions la liste des pions de la tuile
	 * @param hexagone de type Polygon
	 */
	public Tuile(String idCase, Terrain terrain, Effet effet,
			List<Case> voisins, List<Pion> listePions,Polygon hexagone){
		
		this.idCase = idCase;
		this.terrain = terrain;
		this.effet = effet;
		this.voisins = voisins;
		this.listePions = listePions;
		this.hexagone = hexagone;
	}
	
	/**
	 * Cette methode permet de creer les tuiles plage
	 * @return une liste de tuile
	 */
	public static  List<Tuile> creation_tuiles_plage(){
		int i;
		List<Tuile> liste_tuile = new ArrayList<>();
		List<Effet> liste_effet = Effet.creation_effets_plage();
		for(i=0; i<liste_effet.size(); i++) {
			liste_tuile.add(new Tuile(Terrain.PLAGE,liste_effet.get(i)));			
		}
		return liste_tuile;
	}
	
	/**
	 * Cette methode permet de creer les tuiles foret
	 * @return une liste de tuile
	 */
	public static  List<Tuile> creation_tuiles_foret(){
		int i;
		List<Tuile> liste_tuile = new ArrayList<>();
		List<Effet> liste_effet = Effet.creation_effets_foret();
		for(i=0; i<liste_effet.size(); i++) {
			liste_tuile.add(new Tuile(Terrain.FORET,liste_effet.get(i)));
		}
		return liste_tuile;
	}
	
	/**
	 * Cette methode permet de creer les tuiles montagne
	 * @return une liste de tuile
	 */
	public static  List<Tuile> creation_tuiles_montagne(){
		int i;
		List<Tuile> liste_tuile = new ArrayList<>();
		List<Effet> liste_effet = Effet.creation_effets_montagne();
		for(i=0; i<liste_effet.size(); i++) {
			liste_tuile.add(new Tuile(Terrain.MONTAGNE,liste_effet.get(i)));	
		}
		return liste_tuile;
	}

	public static void main(String args[]){
		
	}
}

