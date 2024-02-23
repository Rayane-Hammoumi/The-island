package modele;
import java.util.*;

import controleur.Controleur;

/**
 * Plateau est la classe qui permet de creer le plateau et d'initialiser la partie
 * 
 *
 */
public class Plateau{
	/**
	 * plateau est un tableau de Case qui représente le plateau d'hexagone
	 */
	public static Case[][] plateau = new Case[13][12];
	//private List<Tuile> terrains;
	
	/**
	 * Cette méthode permet de délimiter ainsi que initiliaser les cases du plateau
	 */
	 public void setNull_plateau() {
		 int i,j;
		 for(i=0; i<13;i++) {
			 for(j=0;j<12;j++) {
				 plateau[i][j] = new Case("empty");
			 }
		 }
		 
		plateau[0][0] = null;
		plateau[0][1] = null;
		plateau[0][9] = null;
		plateau[0][10] = null;
		plateau[0][11] = null;
		plateau[1][0] = null;
		plateau[1][11] = null;
		plateau[2][11] = null;
		plateau[3][0] = null;
		plateau[3][11] = null;
		plateau[4][11] = null;
		plateau[6][11] = null;
		plateau[8][11] = null;
		plateau[9][0] = null;
		plateau[9][11] = null;
		plateau[10][11] = null;
		plateau[11][0] = null;
		plateau[11][11] = null;
		plateau[12][0] = null;
		plateau[12][1] = null;
		plateau[12][9] = null;
		plateau[12][10] = null;
		plateau[12][11] = null;
	}
	 
	 /**
	  * Cette méthode permet d'initialiser les cases du plateau avec des coordonnées
	  */
	  public void generer_plateau() {
			this.setNull_plateau();
			String idLetter = "abcdefghijklm";
			String idCase;
			int i,j,k;
			for(i=0; i<13;i++) {
				k=0;
				for(j=0;j<12;j++) {
					if(plateau[i][j]!=null){
						k++;
						idCase = idLetter.charAt(i) + String.valueOf(k);
						plateau[i][j] = new Case(idCase); 
					}
				}
			}
	 }
	 
	 /**
	  * Cette méthode ajoute les voisins de chaque case a la liste des voisins de chaque cases
	  */
	 public void ajout_voisin_plateau() {
		int i,j;
		for(i=0; i<13;i++) {
				for(j=0;j<12;j++) {
					if(plateau[i][j]!=null){
						//Ajout voisin du dessus
						if(i>0) {
							if(i%2 == 0) {
								//paire
								if(i > 0 && j > 0 && plateau[i-1][j] != null) {
									plateau[i][j].ajouter_voisin(plateau[i-1][j]);
								}
								if(i > 0 && plateau[i-1][j+1] != null) {									
									plateau[i][j].ajouter_voisin(plateau[i-1][j+1]);
								}
							}else {
								// impaire
								if(i > 0 && j>0 && plateau[i-1][j-1] != null) {									
									plateau[i][j].ajouter_voisin(plateau[i-1][j-1]);
								}
								if(i > 0 && j < 11 && plateau[i-1][j] != null) {
									plateau[i][j].ajouter_voisin(plateau[i-1][j]);
								}
							}
						}
						//Ajout voisin du dessous
						if(i<12) {
							if(i%2 == 0) {
								// impaire
								if(i < 12 && j < 11 && plateau[i+1][j+1] != null) {
									plateau[i][j].ajouter_voisin(plateau[i+1][j+1]);								}
							}else {
								// paire
								if(j > 0 && plateau[i+1][j-1] != null) {
									plateau[i][j].ajouter_voisin(plateau[i+1][j-1]);
								}
							}
							if( plateau[i+1][j] != null) {
								plateau[i][j].ajouter_voisin(plateau[i+1][j]);	
							}
						}
						
						//Set voisin des cï¿½tï¿½s
						if(j==0) {
							if(plateau[i][j+1] != null) {
								plateau[i][j].ajouter_voisin(plateau[i][j+1]);
							}
						} else if(j==11) {
							plateau[i][j].ajouter_voisin(plateau[i][j-1]);
						} else {
							if(plateau[i][j-1] != null) {									
								plateau[i][j].ajouter_voisin(plateau[i][j-1]);
							}
							if(plateau[i][j+1] != null) {									
								plateau[i][j].ajouter_voisin(plateau[i][j+1]);
							}
						}
					}
				}
			}
		}
	
	 //FONCTION TEST 
	 public void print_plateau() {
		 int i,j;
		 for(i=0; i<13;i++) {
			 for(j=0;j<12;j++) {
				 if (plateau[i][j] == null){
					 System.out.print("na"); 
				 } else {
					 System.out.print(plateau[i][j].getidCase()); 
				 }
				 System.out.print(" ");
				 	
			 }
			 System.out.println("");
		 }
	 }
	 
	 public void print_plateau_tuiller() {
		 int i,j;
		 for(i=0; i<13;i++) {
			 for(j=0;j<12;j++) {
				 if (plateau[i][j] == null){
					 System.out.print("na"); // si null
				 } else if(plateau[i][j] instanceof Tuile){
					 if(plateau[i][j].getTerrain()==null) {
						 System.out.print("Er");
					 } else if(plateau[i][j].getTerrain()==Terrain.PLAGE){
						 System.out.print("Pl");
						 //System.out.print(plateau[i][j].getHexagone());

					 } else if(plateau[i][j].getTerrain()==Terrain.FORET) {
						 System.out.print("Fr");
						 //System.out.print(plateau[i][j].getHexagone());

					 } else if(plateau[i][j].getTerrain()==Terrain.MONTAGNE) {
						 System.out.print("Mo");
						 //System.out.print(plateau[i][j].getHexagone());

					 } else {
						 System.out.print("In");
						//System.out.print(plateau[i][j].getHexagone());


					 }
				 }else {
					 System.out.print("Ca "); // si case basique
					 //System.out.print(plateau[i][j].getHexagone());

				 }
				 System.out.print(" ");
				 	
			 }
			 System.out.println("");
		 }
	 }	 
	 
	 public void print_voisins() {
		 int i,j;
		 for(i=0; i<13;i++) {
			 for(j=0;j<12;j++) {
				 if (plateau[i][j] != null){
					 System.out.println("#"+ plateau[i][j].getidCase()+"#"); 
					 plateau[i][j].lister_voisin(); 
				 }	
				 System.out.println("\n----------");
			 }
			 System.out.println("#####");
		 }
	 }
	 
	 public void affiche_plateau_avec_pion() {
		 int i,j;
		 for(i=0; i<13;i++) {
			 for(j=0;j<12;j++) {
				 if (plateau[i][j] == null){
					 System.out.print("na"); // si null
				 } 
				 else {
					 List<Pion> lp = plateau[i][j].getPions();
					 if(!(lp.isEmpty())) {
						 System.out.print("PP");
					 }
					 else
						 System.out.print("TT"); 
				 }
				 System.out.print(" ");
				 	
			 }
			 System.out.println("");
		 }
	 }
	
	
	 //TERRAIN
	 /**
	  * Cette méthode permet de generer les Tuiles au centre du plateau
	  */
	 public void generer_tuile_plateau() {
		 int i,j, indexTemp;
		 List<Tuile> terrain = new ArrayList<>();
		 Tuile terrainTemp;
		 int[][] loopInt = new int[][]{{4,7},{3,7},{2,9},{2,8},{2,9},{3,7},{4,7}}; //{colonne de dï¿½part,colonne de fin}
		 terrain.addAll(Tuile.creation_tuiles_plage());
		 terrain.addAll(Tuile.creation_tuiles_foret());
		 terrain.addAll(Tuile.creation_tuiles_montagne());
		 System.out.println("Here:"+loopInt[0][0]);
		 for(i=3;i<=9;i++) {
			 for(j=loopInt[i-3][0];j<=loopInt[i-3][1];j++) {
				 if(i==6 && j==5) {
					 //Piï¿½ce central en mer
					 continue;
				 }
				 indexTemp = new Random().nextInt(terrain.size());
				 terrainTemp = terrain.get(indexTemp);
				 terrain.remove(terrainTemp);
				 terrainTemp.setidCase(plateau[i][j].getidCase());
				 terrainTemp.setVoisins(plateau[i][j].getVoisins());
				 plateau[i][j] = terrainTemp;
				 System.out.println("coord Terrain : "+i+", "+j);
				 System.out.println("Terrain : "+plateau[i][j].getTerrain());
			 }
		 }
	 }
	 
	 	/**
	 	 * Cette méthode nous indique s'il y a la presence d'un terrain passé en parametre sur le plateau
	 	 * @param terrain de type Terrain
	 	 * @return true si le terrain a ete trouve, false sinon
	 	 */
		public boolean presence_terrain(Terrain terrain) {
			for(int i= 0; i<Controleur.p.plateau.length; i++) {
				for(int j=0; j<Controleur.p.plateau[0].length;j++) {
					if(Controleur.p.plateau[i][j] != null) {
						if(Controleur.p.plateau[i][j].getTerrain() == terrain) {
							return true;
						}					
						}
				
				}
			}
			return false;
		}
		
		/**
		 * Cette méthode appelle presence_terrain et envoie le terrain plage en parametre
		 * @return true si le terrain a ete trouve, false sinon
		 */
		public boolean presence_plage() {
			return presence_terrain(Terrain.PLAGE);
		}
		
		/**
		 * Cette méthode appelle presence_terrain et envoie le terrain foret en parametre
		 * @return true si le terrain a ete trouve, false sinon
		 */
		public boolean presence_foret() {
			return presence_terrain(Terrain.FORET);
		}
		
	 
	 /**
	  * Cette méthode permet d'accéder a une case du plateau grâce a l'id passé en parametre
	  * @param id de type String. L'id d'une case
	  * @return La Case du plateau
	  */
	 	public Case chercheCaseAvecId(String id) {
			for(int i=0; i<13; i++) {
				for(int j=0; j<12; j++) {
					if(plateau[i][j] != null && id.equals(plateau[i][j].getidCase())) {
					//	System.out.println("Trouver["+i+"]["+j+"]"+plateau[i][j].getClass().getSimpleName());
						return plateau[i][j];
					}
						
				}
			}
		//	System.out.println("pas trouver");
			return new Case();
			 
	 	}
		
	 	/**
	 	 * Cette méthode permet de determiner si une case de coordonnée X et Y est voisin a une tuile au centre du plateau
	 	 * @param coordX de type int la coordonnée X du plateau
	 	 * @param coordY de type int la coordonnée Y du plateau
	 	 * @return un boolean true sila case est voisin, false sinon
	 	 */
	 	public boolean estVoisinATuile(int coordX, int coordY) {
			 
			 int[][] loopInt = new int[][]{{3,7},{4,8},{1,9},{1,10},{1,9},{3,10},{3,9}, {3,8}, {3,7}};
			 for(int i=2;i<=10;i++) {
				 for(int j=loopInt[i-2][0];j<=loopInt[i-2][1];j++) {
					 System.out.println("tuile["+i+"]["+j+"] et "+coordX+" "+coordY);
					 if(coordX == i && coordY == j) {
						 System.out.println("oui");
						 return true;
					 }
						 
				 }

			 }
			 return false;
		 }

	 	
	 
 	/**
 	 *  Cette methode permet de placer un explorateur sur le plateau
 	 * @param explo de type Explorateur : l'explorateur que l'on veut passer
 	 * @param coordI la coordonnee ou l'on doit placer l'explorateur
 	 * @param coordJ la coordonnee ou l'on doit placer l'explorateur
 	 * @param index l'indice a laquelle se trouve l'explorateur dans la liste des explorateurs
 	 */
	public void placer_explorateur(List<Explorateur> explo, int coordI, int coordJ, int index) {
		plateau[coordI][coordJ].ajouter_pion(explo.get(index));
		explo.get(index).setValeur(0);	
	}
	 	
 
	/**
	 * Cette méthode permet de récupérer une tuile du plateau grâce a des coordonnées
	 * et de la remplacer par une case
	 * @param coordX de type int la coordonnée X du plateau
	 * @param coordY de type int la coordonnée Y du plateau
	 * @return la tuile a la coordonnée coordX et coordY du plateau
	 */
	public Tuile selection_tuile(int coordX, int coordY){
		if(plateau[coordX][coordY] instanceof Tuile) {
			Tuile ret = new Tuile(plateau[coordX][coordY].getidCase(),
					plateau[coordX][coordY].getTerrain(),
					plateau[coordX][coordY].getEffet(),
					plateau[coordX][coordY].getVoisins(),
					plateau[coordX][coordY].listePions,
					plateau[coordX][coordY].hexagone);
			Case remplacement = new Case(plateau[coordX][coordY].getidCase(),
					plateau[coordX][coordY].getVoisins(),
					plateau[coordX][coordY].listePions,
					plateau[coordX][coordY].hexagone);
			plateau[coordX][coordY] = remplacement;
			
			System.out.println("We were over due");
			return ret;

		}
		return null;
	}
		 
		
	 
	 // MAIN
	public static void main(String args[]){
		Plateau p = new Plateau();
		p.generer_plateau();
		p.ajout_voisin_plateau();
		p.print_plateau();
		p.print_voisins();
		//p.generer_tuile_plateau();
		//p.print_plateau_tuiller();
	
	}
}