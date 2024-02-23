package controleur;
import modele.*;
import vue.*;

import java.io.File;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * 
 * La classe Controleur qui lie le modele et la vue.
 *
 */
public class Controleur extends Application{
	/**
	 * stage de type Stage
	 */
	private Stage stage;
	
	/**
	 * nbJoueurs de type int le nombre de joueur qui jouent
	 */
	private int nbJoueurs;
	
	/**
	 * comboBox de type ComboBox pour selectionner le nombre de joueur
	 */
	private ComboBox comboBox;
	
	/**
	 * tabTextField un tableau de TextField
	 */
	private TextField tabTextField[];
	
	/**
	 * p de type Plateau repr�sente le plateau
	 */
	public static Plateau p = new Plateau();
	
	/**
	 * tabJoueurs un tableau de Joueur
	 */
	private static Joueur tabJoueurs[];
	
	/**
	 * mainPionclique de type int repr�sente l'index du pion de la main choisi par le clic
	 */
	private static int mainPionclique = -1;
	
	/**
	 * phaseDeJeu de type int represente la phase de jeu
	 */
	private  static int phaseDeJeu = 0;
	
	/**
	 * RetireTuile de type boolean 
	 */
	private static boolean RetireTuile = false;
	
	/**
	 * wait_clic de type boolean repr�sente l'attente d'un clic
	 */
	private static boolean wait_clic = false;
	
	/**
	 * skip_clic de type boolean repr�sente si l'on a passer
	 */
	private static boolean skip_clic = false;
	
	/**
	 * clic_indexI de type int repr�sente les coordonn�es i du clic
	 */
	private static int clic_indexI = -1;
	
	/**
	 * clic_indexJ de type int repr�sente les coordonn�es j du clic
	 */
	private static int clic_indexJ = -1;
	
	/**
	 * est_retirable de type boolean verifier si une tuile est retirable
	 */
	private static boolean est_retirable = false;
	
	/**
	 * est_passable de type boolean repr�sente si l'on peut passer
	 */
	private static boolean est_passable = true;
	
	/**
	 * creature_clique de type Pion est le pion que l'utilisateur a cliqu�
	 */
	private static Pion creature_clique = null;
	
	/**
	 * joueur_clique de type Explorateur repr�sente l'explorateur sur lequel le joueur a cliqu�
	 */
	private static Explorateur joueur_clique = null;
	
	/**
	 * joueur_clique2 de type Circle
	 */
	private static Circle joueur_clique2 = null;
	
	/**
	 * main_clic de type int repr�sente l'index de la main choisi par le clic
	 */
	public static int main_clic=-1;
	
	/**
	 * tileMap de type AnchorPane
	 */
	private static AnchorPane tileMap = new AnchorPane();
	
	/**
	 * joueurActif de type int est le joueur courant
	 */
	private static int joueurActif = 1;
	
	/**
	 * vue de type Vue
	 */
    private  Vue vue = new Vue(tileMap, p);
    
    /**
     * vuePions de type VuePions
     */
    private VuePions vuePions = new VuePions();
    
    /**
     * nombre_deplacements_restants de type int repr�sente le nombre de d�placement possible
     */
	public static int nombre_deplacements_restants=3; //A RESET CHAQUE TOUR, chaque joueur a 3 deplacements par tour
	
	/**
	 * passe de type Rectangle
	 */
	Rectangle passe = vue.getBouttonPasse().getBouttonPasse();
	
	/**
	 * effet_en_cours de type boolean nous indique si un effet est en cours
	 */
	public static boolean effet_en_cours = false;
	
	/**
	 * de_clic de type boolean nous indique si le de a ete cliqu�
	 */
	private static boolean de_clic = false;
	
	/**
	 * resultat_de de type int repr�sente le resultat du d�
	 */
	private static int resultat_de=0;
	
	/**
	 * changement_phase de type boolean
	 */
	private static boolean changement_phase = true;
	
	/**
	 * creatureCliquable de type boolean nous indique si une cr�ature est cliquable
	 */
	private static boolean creatureCliquable = true;

	
	//INTIALISATION MODELE
	/**
	 * Creation et placement des serpents de mer sur le plateau 
	 * des coordonnees predefinis.
	 * @param p : Plateau sur le quel on place les serpents
	 */
	public  void placement_serpent(Plateau p) {
		
		SerpentMer serpent[] = new SerpentMer[5];
		
		for (int i =0; i<5 ;i++){
			serpent[i] = new SerpentMer();
			vuePions.init_rectangle(serpent[i],tileMap);
			set_creature_handler(serpent[i]);
		}
		
		//Serpent case : b1
		serpent[0].setCoord_index(1, 1);
		p.plateau[1][1].ajouter_pion(serpent[0]);
		vuePions.placer_creature_plateau(serpent[0], 
				vue.get_centre_hex(p.plateau[1][1].getHexagone()));
		
		//Serpent case : c11
		serpent[1].setCoord_index(2, 10);
		p.plateau[2][10].ajouter_pion(serpent[1]);
		vuePions.placer_creature_plateau(serpent[1], 
				vue.get_centre_hex(p.plateau[2][10].getHexagone()));
		
		//Serpent case : g6
		serpent[2].setCoord_index(5,6);
		p.plateau[6][5].ajouter_pion(serpent[2]);
		vuePions.placer_creature_plateau(serpent[2], 
				vue.get_centre_hex(p.plateau[6][5].getHexagone()));
		
		//Serpent case : k1
		serpent[3].setCoord_index(10,0);
		p.plateau[10][0].ajouter_pion(serpent[3]);
		vuePions.placer_creature_plateau(serpent[3], 
				vue.get_centre_hex(p.plateau[10][0].getHexagone()));
		
		//Serpent case : l10
		serpent[4].setCoord_index(11,10);
		p.plateau[11][10].ajouter_pion(serpent[4]);
		vuePions.placer_creature_plateau(serpent[4], 
				vue.get_centre_hex(p.plateau[11][10].getHexagone()));
	}
	
	/**
	 * Initialise la couleur de l'explorateur et ses explorateurs � placer
	 */
	public void setJoueurs() {
		Type_couleur[] tab_couleur = {Type_couleur.BLEU,Type_couleur.JAUNE,Type_couleur.ROUGE,Type_couleur.VERT};
		int i;
		for(i=0; i< tabJoueurs.length;i++) 
		{
			tabJoueurs[i].setCouleur(tab_couleur[i]);
			tabJoueurs[i].initialisation_explo_placer();
			tabJoueurs[i].initialisation_bateau_placer();
			for (int j= 0; j < tabJoueurs[i].getExplo_placer().size(); j++) {
				set_explo_handler(tabJoueurs[i].get_explo_placer(j));
			}
		}
	}
	//FIN INITIALISATION MODELE
	
	//EFFETS
	
	/**
	 * Gestion de l'effet imm�diat de la baleine
	 * C'est a dire le placement d'un nouveau pion baleine
	 * @param p : Plateau o� placer les baleines
	 * @param coordX : Coordonnees en X de la case ou sera place la baleine
	 * @param coordY : Coordonnees en Y de la case ou sera place la baleine
	 */
	public void effet_baleine_immediat(int coordX, int coordY) {
		Baleine bal = new Baleine();
		vuePions.init_rectangle(bal,tileMap);
		vuePions.placer_creature_plateau(bal, 
				vue.get_centre_hex(p.plateau[coordX][coordY].getHexagone()));
		p.plateau[coordX][coordY].ajouter_pion(bal);
		set_creature_handler(bal);
		bal.setCoord_index(coordX, coordY);
	}
	
	/**
	 * Cette methode represente l'action que la tuile tourbillon va effectuer
	 * @param p le plateau
	 * @param coordX la coordonnee de type int
	 * @param coordY la coordonnee de type int
	 */
	public static void effet_tourbillon(Plateau p, int coordX, int coordY) {
		List pionsCase =  p.plateau[coordX][coordY].getPions();
		for (int i=0; i < pionsCase.size(); i++) {
			Pion pion =(Pion) pionsCase.get(i);
			pion.getRectangle().setVisible(false);
			pion.getCercle().setVisible(false);
		}
		p.plateau[coordX][coordY].vider_case();
	}
	
	/**
	 * Cette methode represente l'action que la tuile requin va effectuer
	 * @param p le plateau
	 * @param coordX la coordonnee de type int
	 * @param coordY la coordonnee de type int
	 */
	public void effet_requin_immediat(Plateau p, int coordX, int coordY) {
		Requin requin = new Requin();
		
		vuePions.init_rectangle(requin,tileMap);
		vuePions.placer_creature_plateau(requin, 
				vue.get_centre_hex(p.plateau[coordX][coordY].getHexagone()));
		p.plateau[coordX][coordY].ajouter_pion(requin);
		set_creature_handler(requin);
		requin.setCoord_index(coordX, coordY);
	}
	
	/**
	 * Cette methode represente l'action que la tuile bateau va effectuer
	 * @param p le plateau
	 * @param coordX la coordonnee de type int
	 * @param coordY la coordonnee de type int
	 */
	public void effet_bateau_immediat(Plateau p, int coordX, int coordY) 
	{
		Bateau bateau = new Bateau();
		int i, cpt_nageur=0;
		List<Pion> liste_pion = p.plateau[coordX][coordY].getPions();
		
		for(i=0; i<liste_pion.size(); i++) 
		{
			if(liste_pion.get(i) instanceof Explorateur) 
			{
				cpt_nageur++;
			}
		}
		
		if(cpt_nageur<4) 
		{
			//on met tout les nageurs sur le bateau
			for(i=0; i<liste_pion.size(); i++) {
				if(liste_pion.get(i) instanceof Explorateur) 
				{
					bateau.ajouter_pion(liste_pion.get(i));;
					p.plateau[coordX][coordY].retirer_pion(liste_pion.get(i));
				}
			}
		} 
		else 
		{
			while(true) {
				if(wait_clic) 
				{
					wait_clic = false;
					if(skip_clic) 
					{
						skip_clic = false;
						break;
					}
					if(joueur_clique != null) 
					{
						if(joueur_clique.getCoord_indexI() == coordX &&
								joueur_clique.getCoord_indexJ() == coordY) 
						{
							bateau.ajouter_pion(joueur_clique);
							p.plateau[coordX][coordY].retirer_pion(liste_pion
									.get(liste_pion.indexOf(joueur_clique)));
						}
					}
				}

				
			}
		}

		
		p.plateau[coordX][coordY].ajouter_pion(bateau);
	}

	

	/**
	 * Cette methode represente l'action que la tuile de defense requin en rouge va effectuer
	 * @param coordX la coordonnee de type int
	 * @param coordY la coordonnee de type int
	 * @param requin type Requin
	 */
	public void defense_requin_rouge(int coordX, int coordY, Requin requin) {
		List<Pion> liste_pion = Plateau.plateau[coordX][coordY].getPions();
		requin.getRectangle().setVisible(false);
		liste_pion.remove(requin);
	}
	
	/**
	 * Cette methode represente l'action que la tuile de defense baleine en rouge va effectuer
	 * @param coordX la coordonnee de type int
	 * @param coordY la coordonnee de type int
	 * @param baleine type Baleine
	 */
	public void defense_baleine_rouge(int coordX, int coordY, Baleine baleine) {
		List<Pion> liste_pion = Plateau.plateau[coordX][coordY].getPions();
		baleine.getRectangle().setVisible(false);
		liste_pion.remove(baleine);
	}
	
	/**
	 * Deplace une creature en fonction du r�sultat du d�
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean de_creature(int dest_i, int dest_j)
	{
		boolean ret = false;
		if(creature_clique ==null) {
			return ret;
		}
		
		System.out.println("<de_creature>");
		if(dest_i != -1 && dest_j != -1 && nombre_deplacements_restants > 0) {
			System.out.println("<Entre boucle>");
			String res = creature_clique.deplacer_limite(dest_i, dest_j);
			for(int k=0;k<Plateau.plateau[creature_clique.getCoord_indexI()][creature_clique.getCoord_indexJ()].getVoisins().size();k++) {
				System.out.println(Plateau.plateau[creature_clique.getCoord_indexI()][creature_clique.getCoord_indexJ()].getVoisins().get(k).getidCase());
			
			}
			if(res.equals("Deplacement reussi")) {
				System.out.println("<Deplacement Reussi>");
				nombre_deplacements_restants--;
				//on met a jour la vue
				vue.changeText(tabJoueurs[joueurActif-1].getPseudo() + " Deplacez un pion"
						+ "\n "+nombre_deplacements_restants+ " deplacements possible restant");
				vuePions.placer_creature_plateau(creature_clique, vue.get_centre_hex(p.plateau[dest_i][dest_j].getHexagone()));
				ret = true;
			} else {
				vue.changeText(res);
			}
			if(nombre_deplacements_restants == 0) {
				System.out.println("<Plus de deplacement. Reset.>");
				nombre_deplacements_restants = 3;
			}
			return ret;
		} 
		return ret;
	}
	/**
	 * Deplace un requin apr�s un appel au niveau du d�
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean de_creature_requin(int dest_i,int dest_j) {
		boolean result = false;
		if(estPresent_requin())
		{
			if(creature_clique instanceof Requin) 
			{
				result = de_creature(dest_i,dest_j);
			} else {
				vue.changeText("Veuillez selectionner un requin.");
			}
		} else {
			vue.changeText("Il n'y a pas de requin pr�sent sur le plateau.");
		}
		return result;
	}
	
	/**
	 * Deplace une baleine apr�s un appel au niveau du d�
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean de_creature_baleine(int dest_i,int dest_j) {
		boolean result = false;
		if(estPresent_baleine())
		{
			if(creature_clique instanceof Baleine) 
			{
				result = de_creature(dest_i,dest_j);
			} else {
				vue.changeText("Veuillez selectionner une baleine.");
			}
		} else {
			vue.changeText("Il n'y a pas de baleine pr�sent sur le plateau.");
		}
		return result;
	}
	
	/**
	 * Deplace un serpent de mer apr�s un appel au niveau du d�
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean de_creature_serpentMer(int dest_i,int dest_j) {
		boolean result = false;
		if(estPresent_serpentMer())
		{
			if(creature_clique instanceof SerpentMer) 
			{
				result = de_creature(dest_i,dest_j);
			} else {
				vue.changeText("Veuillez selectionner un Serpent de Mer.");
			}
		} else {
			vue.changeText("Il n'y a pas de Serpent de Mer pr�sent sur le plateau.");
		}
		return result;
	}
	
	/**
	 * Deplacement d'un nageur avec l'utilisation d'une tuile dauphin rouge
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean tuileRouge_dauphin(int dest_i, int dest_j)
	{
		if(estPresent_nageur()) 
		{
			if(joueur_clique != null && joueur_clique.est_nageur())
			{
				if(joueur_clique.getCouleur() == tabJoueurs[joueurActif-1].getCouleur()) {
					vue.changeText("Success");
					return true;
				} else {
					vue.changeText("Veuillez choisir un nageur de votre couleur.");
				}
			} else {
				vue.changeText("Veuillez choisir un nageur.");
			}
		} else {
			vue.changeText("Pas de nageur pr�sent. Vous ne pouvez pas utiliser la tuile.");
		}
		
		return false;
	}
	
	/**
	 * Deplacement d'un bateau avec l'utilisation d'une tuile rouge
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean tuileRouge_bateau(int dest_i, int dest_j) 
	{
		if(estPresent_bateau()) {
			if(creature_clique != null && creature_clique instanceof Bateau) {
				if(creature_clique.getCouleur() == tabJoueurs[joueurActif-1].getCouleur()) {
					if(dest_i != -1 && dest_j != -1 && nombre_deplacements_restants > 0) {
						String res = creature_clique.deplacer(dest_i, dest_j);
						for(int k=0;k<Plateau.plateau[joueur_clique.getCoord_indexI()][joueur_clique.getCoord_indexJ()].getVoisins().size();k++) {
							System.out.println(Plateau.plateau[joueur_clique.getCoord_indexI()][joueur_clique.getCoord_indexJ()].getVoisins().get(k).getidCase());
						
						}
						if(res.equals("Deplacement reussi")) {
							nombre_deplacements_restants--;
							//on met a jour la vue
							vue.changeText(tabJoueurs[joueurActif-1].getPseudo() + " Deplacez un pion"
            						+ "\n "+nombre_deplacements_restants+ " deplacements possible restant");
							vuePions.placer_explorateur_plateau(joueur_clique, vue.get_centre_hex(p.plateau[dest_i][dest_j].getHexagone()));
							
							if(nombre_deplacements_restants == 0) {
								nombre_deplacements_restants = 3;
							}
							return true;
						}
					}
				}
			} else {
				vue.changeText("Veuillez selectionner un bateau.");
			}
		} else {
			vue.changeText("Il n'y a pas de bateau pr�sent sur le plateau.");
		}
		
		return false;
	}
	
	
	/**
	 * Deplacement d'une creature avec l'utilisation d'une tuile rouge
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean tuileRouge_creature(int dest_i,int dest_j)
	{
		boolean result = false;
		System.out.println("Je suis ici");
		if(!p.plateau[dest_i][dest_j].getPions().isEmpty()) {
			vue.changeText("La case doit �tre inocup�e.");
			System.out.println(" mais pas l�.");
			return result;
		}
		
		String instruction = creature_clique.deplacer(dest_i, dest_j);


		vue.changeText(instruction);
		if(instruction=="Deplacement reussi")
		{
			result=true;
			vuePions.deplacer_creature_plateau(creature_clique,
						vue.get_centre_hex(
								p.plateau[dest_i][dest_j].getHexagone()));
		}
		return result;
	}
	
	/**
	 * Deplacement d'une baleine avec l'utilisation d'une tuile rouge
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean tuileRouge_creature_baleine(int dest_i,int dest_j) 
	{
		boolean result = false;
		if(estPresent_baleine())
		{
			if(creature_clique instanceof Baleine) 
			{
				result = tuileRouge_creature(dest_i,dest_j);
			} else {
				vue.changeText("Veuillez selectionner une baleine.");
			}
		} else {
			vue.changeText("Il n'y a pas de baleine presente sur le plateau.");
		}
		return result;

	}
		
	/**
	 * Deplacement d'un requin avec l'utilisation d'une tuile rouge
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean tuileRouge_creature_requin(int dest_i,int dest_j) {
		boolean result = false;
		if(estPresent_requin())
		{
			if(creature_clique instanceof Requin) 
			{
				result = tuileRouge_creature(dest_i,dest_j);
			} else {
				vue.changeText("Veuillez selectionner un requin.");
			}
		} else {
			vue.changeText("Il n'y a pas de requin pr�sent sur le plateau.");
		}
		return result;
	}
	
	/**
	 * Deplacement d'un serpent de mer avec l'utilisation d'une tuile rouge
	 * @param dest_i
	 * @param dest_j
	 * @return vrai si le d�placement est r�ussi
	 */
	public boolean tuileRouge_creature_serpentmer(int dest_i,int dest_j) {
		boolean result = false;
		if(estPresent_serpentMer())
		{
			
			
			if(creature_clique instanceof SerpentMer) 
			{
				result = tuileRouge_creature(dest_i,dest_j);
			} else {
				vue.changeText("Veuillez selectionner un serpent de mer.");
			}
		} else {
			vue.changeText("Il n'y a pas de serpent de mer pr�sent sur le plateau.");
		}
		return result;
	}
	
	/**
	 * appliquer l'effet d'une  tuile selectioner par le joueur si les conditions sont remplites pour 
	 * appliquer le-dit effet, sinon il affiche les conditions a remplir pour activer l'effet souhaiter 
	 * @param effet : effet de la tuile selectionner dans la main
	 * @param i index i de l  hexagone cliquer
	 * @param j index j de l  hexagone  cliquer
	 * @return retourne  si l'effet c'est bien  appliquer
	 */
	public boolean appliquer_effet_main(Type_effet effet, int  i,  int j) {
		boolean effet_appliquer = false;
		if(p.plateau[i][j] == null) 
		{
			vue.changeText("EFFET NULL");//DEBUG
			return effet_appliquer;
		}
		
		switch(effet) {
			case DAUPHIN :
				effet_appliquer = tuileRouge_dauphin(i,j);
				break;
			case BATEAU_DIFFERE :
				effet_appliquer = tuileRouge_bateau(i,j);
				break;
			case SERPENT_MER :
				effet_appliquer = tuileRouge_creature_serpentmer(i,j);
				break;
			case REQUIN_DIFFERE :
				effet_appliquer = tuileRouge_creature_requin(i,j);
				break;
			case BALEINE_DIFFERE :
				effet_appliquer = tuileRouge_creature_baleine(i,j);
				break;
			default:
				effet_appliquer = false;
				break;
		}
		
		return effet_appliquer;
	}

	
	
	//FIN EFFET
	
	/**
	 * Deplacement g�n�ral d'un pion
	 * @param pion
	 */
	public void deplacer(Pion pion) {
		for(int i=0; i<nombre_deplacements_restants;i++) {
			if(clic_indexI != -1 && clic_indexJ != -1)
			{
				String res = pion.deplacer(clic_indexI,clic_indexJ);	
				if(res !=  "Deplacement reussi" || res !="\n La case de "
						+ "destination contient un requin ou un serpent de mer."
						+ " L'explorateur s'est fait croquer.\n" )
				{
					i--;
				} 			
			} else {
				i--;
			}
		}
	}

	
	
	
	
	//MENU
	
	/**
	 * Affiche la sc�ne d'accueil 
	 * @return Scene
	 */
	public Scene sceneMenuAccueil()
	{
		AnchorPane menu = new AnchorPane();
        Scene content = new Scene(menu, 1100, 700);
        Rectangle bouton;
        
        Menu accueil= new Menu();
        
        accueil.affiche_img_menu(menu);
        accueil.affiche_texte(menu);
        comboBox = accueil.affiche_comboBox(menu);
        bouton= accueil.affiche_bouton(menu);
        bouton_event(bouton, true);
        return content;
	}
	
	
	/**
	 * Affiche la sc�ne menu 
	 * @return
	 */
	public Scene sceneMenuPseudo()
	{
		AnchorPane menu = new AnchorPane();
		Rectangle bouton;
        Scene content = new Scene(menu, 1100, 700);
        
        MenuPseudo menuPseudo = new MenuPseudo();
        menuPseudo.affiche_img_menu(menu);
        
        tabTextField = menuPseudo.affiche_textFiel(nbJoueurs, menu);
        bouton = menuPseudo.affiche_bouton(menu);
        bouton_event(bouton, false);
                
        return content;
	}
	
	/**
	 * Affichage de la sc�ne du jeu 
	 * @return
	 */
	public Scene sceneJeu()
	{

		Scene content = new Scene(tileMap, 1100, 700);
        p.plateau= vue.getPlateauUpdated();
        set_tile_handler(vue);
        set_passe_handler(vue);
        set_de_handler(vue);
        set_pion_handler(vue);
        set_main_handler();
        setJoueurs();
        placement_serpent(p);
        
		vue.changeText(tabJoueurs[joueurActif-1].getPseudo() +" Veuillez placer un pion  sur le plateau.");
		vue.getMain().update_main_pion(tabJoueurs, joueurActif);
        

        return content;
	}
	

	

	//######## EVENT HANDLER de la vue
	
	/**
	 * Gestion du clique 
	 * @param btn
	 * @param estBtnJouer
	 */
	public void bouton_event(Rectangle btn, boolean estBtnJouer)
	{
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			nbJoueurs =  (int) comboBox.getValue();
			tabJoueurs = new Joueur[nbJoueurs];
			boolean estToutRempli = true ;
			if (estBtnJouer)
			{
				stage.setScene(sceneMenuPseudo());
			}
			else
			{
				for (int i =0 ;i< nbJoueurs; i++)
				{
					if (tabTextField[i].getText().isEmpty())
					{
						estToutRempli = false;
					}	
				}
				if(estToutRempli)
				{
					for(int i=0; i<nbJoueurs;i++)
					{
						tabJoueurs[i] = new Joueur(tabTextField[i].getText());
					}
					stage.setScene(sceneJeu());

				}
			}
    		
        });
		
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, f -> {
			DropShadow shadow = new DropShadow();
    		btn.setEffect(shadow);
        });
		
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, g -> {
    		btn.setEffect(null);
        });
	}
	//FIN MENU

	/**
	 * Changement de joueur
	 */
	public void joueur_suivant(){

	
		switch(joueurActif) {
			case(1):
				joueurActif=2;
				break;
			case(2):
				if(tabJoueurs.length<3) {
					joueurActif=1;
				}
				else {
					joueurActif = 3;
				}
				break;
			case(3):
				if(tabJoueurs.length==3) {
					joueurActif=1;
				}
				else {
					joueurActif = 4;
				}
				break;
			case(4):
				joueurActif=1;
				break;
		}

}

	/**
	 * Retourne le nombre joueur avec une main vide 
	 * @return
	 */
	public int nombre_main_vide() {
	int cptMainsVides=0;
	for(int k=0;k<tabJoueurs.length;k++) {
		if(tabJoueurs[k].is_explo_placer_empty()) {
			cptMainsVides++;
		}
	}
	return cptMainsVides;
}

	/**
	 * Pour une case donn�e, rajoute les points � son joueur
	 * @param index_i
	 * @param index_j
	 */
	public void sauver_p(int index_i, int index_j) {
		if(!p.plateau[index_i][index_j].getPions().isEmpty()) {
			for(int i=0;i<p.plateau[index_i][index_j].getPions().size();i++) {
				if(p.plateau[index_i][index_j].getPions().get(i) instanceof Explorateur) 
				{
					for(int j=0; j<tabJoueurs.length;j++) 
					{
						if(tabJoueurs[i].getCouleur()==p.plateau[index_i][index_j].getPions().get(i).getCouleur()) 
						{
							tabJoueurs[i].sauver_joueur(p.plateau[index_i][index_j].getPions().get(i));
							p.plateau[index_i][index_j].getPions().remove(i);
							return;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Ajoute les points des pions de certaine case
	 */
	public void sauver_pion() {
		
		//b1 : 1,1
		sauver_p(1,1);
		
		//c1 : 2,0
		sauver_p(2,0);

		//b10 1,10
		sauver_p(1,10);
		
		//c11 2,10
		sauver_p(2,10);
		
		//k1 10,0
		sauver_p(10,0);
		
		//l1 11 1
		sauver_p(11,1);
		
		//k11 10 10
		sauver_p(10,10);
		
		//l10 11 10
		sauver_p(11,10);
	}
	
	
	/**
	 * On cree pour chaque case du plateau un event listener du clic
	 */
	public void set_tile_handler(Vue vue) {	 		
		Grille g = new Grille();
		
	 	for(int j=0;j<13;j++) {
	        	
		        for (int i=0;i<g.longLine(j);i++)
		        {
		        	
		            int iTmp;
		            iTmp = i+ g.set_iImp(j);	            
					if(p.plateau[j][iTmp]!=null){						
						set_validation_handler(j,iTmp);
			            set_tile_handler2(j,iTmp);
					}
		            
		        }   
	 	}
	}
	
	/**
	 * Ajoute un handler pour une tuile � des coordonn�es donn�es
	 * @param i
	 * @param j
	 */
	public void set_tile_handler2(int i,int j) {
       p.plateau[i][j].getHexagone().addEventHandler(MouseEvent.MOUSE_ENTERED, g -> {
            if(clic_indexI ==-1) {

        	vue.getZonePreview().getHexPreview().setFill(p.plateau[i][j].getHexagone().getFill());
            vue.getZonePreview().getHexPreview().setStroke(p.plateau[i][j].getHexagone().getStroke());
            vue.getZonePreview().getHexPreview().setStrokeWidth(p.plateau[i][j].getHexagone().getStrokeWidth());
            }
            
            p.plateau[i][j].getHexagone().addEventHandler(MouseEvent.MOUSE_EXITED, h -> {
            	if(clic_indexI ==-1) {
            	vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.getZonePreview().get_mystery_tile(), 0, 0, 1, 1, true));
            	vue.getZonePreview().getHexPreview().setStroke(Paint.valueOf("#454194") );
            	vue.getZonePreview().getHexPreview().setStrokeWidth(3);
            	}
	       }); 
            
           
      });
	}
	/**
	 * Ajoute un event listner pour r�agir au clic dans une case[i][j]
	 * @param i indice i de la case
	 * @param j indice j de la case
	 */
	public void set_validation_handler(int i,int j) {
		
		p.plateau[i][j].getHexagone().addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> {
					if(changement_phase) {
						switch(phaseDeJeu) {
						case 0:
							vue.changeText("PHASE 0: Initialisation");
							break;
						case 1:
							vue.changeText("PHASE 1: Tuile rouges");
							break;
						case 2:
							vue.changeText("PHASE 2: Deplacement pion");
							break;
						case 3:
							vue.changeText("PHASE 3: Destruction de l'ile");
							break;
						case 4:
							vue.changeText("PHASE 4: Le de");
							break;
						
						}
						changement_phase = false;
						
						
						
					} else {
						switch(phaseDeJeu) {
						//INITIALISATION DU JEU: PLACEMENT DES PIONS
						case 0:
							//on set la  possibilit� de passer ou non
							est_passable=false;
			        		passe.setFill(new ImagePattern(vue.getBouttonPasse().get_skip_img(est_passable),0, 0, 1, 1, true));
							
							//on  verifie  qu il a deja selectioner preablement  un pion dans  sa main
							if(main_clic !=-1) 
							{
								//on  verifie  si  il a cliquer  sur une  tuile
								if(p.plateau[i][j] instanceof Tuile) {
									//on  verifie  si la case  a  deja 1 pion
									if(p.plateau[i][j].getPions().size() < 1) 
									{

										//on ajoute  le pion au plateau
										p.plateau[i][j].ajouter_pion(tabJoueurs[joueurActif-1].get_explo_placer(main_clic));
										tabJoueurs[joueurActif-1].get_explo_placer(main_clic).setCoord_index(i, j);
										//on ajoute le pion a la vue
										vuePions.init_cercle(tabJoueurs[joueurActif-1].get_explo_placer(main_clic),vue.getTileMap());
										vuePions.placer_explorateur_plateau(tabJoueurs[joueurActif-1].get_explo_placer(main_clic), vue.get_centre_hex(p.plateau[i][j].getHexagone()));
										//on retire le pion de la main
										tabJoueurs[joueurActif-1].delete_explo_placer(main_clic);
										
										//on passe au joueur suivant
										joueur_suivant();
										
										//on affiche la main du joueur suivant
										vue.getMain().update_main_pion(tabJoueurs, joueurActif);
										
										//on reset le clic
										main_clic=-1;
										
										//on verifie si ttes les mains sont vides
										if (nombre_main_vide()== tabJoueurs.length)
										{
											phaseDeJeu =1;
											changement_phase = true;
											vue.changeText("La phase d'initialisation est finie. \n Cliquez sur le plateau pour passer a la phase "+phaseDeJeu+".");

										}
										else 
										{
											//La phase n'est pas finie, on donne les instructionbs au  joueur suivant 
											vue.changeText(tabJoueurs[joueurActif-1].getPseudo() +" Veuillez placer un pion  sur le plateau.");
										}
									}
									else 
									{
										vue.changeText("La case contient deja un pion. \n Veuillez en selectionner une autre.");
									}	
								}
								else
								{
									vue.changeText("La case n'est pas une tuile. \n Veuillez en selectionner une autre.");
								}
							}
							else 
							{
								vue.changeText("Veuillez selectionner un pion avant.");
							}
							break;
							
							
							
							
						//PLACEMENT TUILE DE LA MAIN
						case 1:
							//on remet le de clean
							//on set la  possibilit� de passer ou non
							est_passable=true;
			        		passe.setFill(new ImagePattern(vue.getBouttonPasse().get_skip_img(est_passable),0, 0, 1, 1, true));
			        		//on remet la preview clean
			        		vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.getZonePreview().get_mystery_tile(), 0, 0, 1, 1, true));
			                vue.getZonePreview().getHexPreview().setStrokeWidth(3);
			                vue.getZonePreview().getHexPreview().setStroke(Paint.valueOf("#454194") );
			                vue.getZonePreview().de.setFill(new ImagePattern(vue.getZonePreview().set_dice_img(), 0, 0, 1, 1, true));      

							//valeurs de test pour voir si les tuiles sont bien ajouter/supprimer

			                //TEST SERPENT

			               
			                
							if(tabJoueurs[0].getListEffetMain().isEmpty() || tabJoueurs[joueurActif-1].main_seulement_defense()) {
								//vue.changeText("SKIP AUTOMATIQUE CAR MAIN  EST VIDE MAIS LA ON BLOCK POUR TESTER");
								vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
								changement_phase = true;
								phaseDeJeu=2;
								main_clic =-1;
								vue.changeText(tabJoueurs[joueurActif-1].getPseudo()+ " Vous ne pouvez pas placer de tuiles. \n Appuyer sur le plateau pour continuer.");
							}

							else {
								vue.changeText(tabJoueurs[joueurActif-1].getPseudo() +" Voulez vous  utiliser une tuile ? \n Vous pouvez passer cette phase.");
								vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
								//on recup l'effet utiliser et on  le retire  de la  main... if pas d'effet encore on  affiche  veuillez  choisir une  tuile  ou passer
								if(main_clic !=-1) {

									Type_effet effet_selectionner = tabJoueurs[joueurActif-1].getListEffetMain().get(main_clic);
									Boolean effet_appliquer = appliquer_effet_main(effet_selectionner,i,j);
									//effet_appliquer= true;
									if(effet_appliquer == true) {
										//on retire l'effet de la main 
										tabJoueurs[joueurActif-1].getMain().remove(main_clic);
							        	vue.getMain().getHexHand()[main_clic].setVisible(false);
							        	//cette phase est fini on passe a la prochaine
							        	phaseDeJeu=2;
							        	changement_phase = true;
										vue.changeText("L'effet a �t� retirer de la main mais pas appliquer car personne n'a coder l'application encore. Appuyer sur le platau pour passer a la phase "+phaseDeJeu+".");
							        	
										/**
										 * a des  fins de  test  on  peut changer le joueur actif
										 */
							        	//joueur_suivant();
										//on met a jour la main  sans l'effet
										vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
										
										//on reset le clic
										main_clic =-1;
										
										vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);

									}
									else {
										//on fait rien juste on attend que le mec refasse une tentative de validation en cliquant sur le grille
										//Ainsi on fait  rien et on va  directement dans "appliquer_effet_main"
									}
								}
								else {
									vue.changeText("Veuillez selectionner une tuile ou passer votre phase.");
								}
							}
							break;
							
							
							
						//DEPLACEMENT DES PIONS	
						case 2:
							//on set la  possibilitee de passer ou non
							est_passable=true;
			        		passe.setFill(new ImagePattern(vue.getBouttonPasse().get_skip_img(est_passable),0, 0, 1, 1, true));
			        		
			        		//on remet la preview clean
			        		vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.getZonePreview().get_mystery_tile(), 0, 0, 1, 1, true));
			                vue.getZonePreview().getHexPreview().setStrokeWidth(3);
			                vue.getZonePreview().getHexPreview().setStroke(Paint.valueOf("#454194") );
			                
			                System.out.println("Phase 2");


			                vue.changeText(tabJoueurs[joueurActif-1].getPseudo() + " Deplacez un pion"
			                						+ "\n "+nombre_deplacements_restants+ " deplacements possible restant");
			                				
			                                
							if((joueur_clique != null) && (joueur_clique instanceof Explorateur) && (joueur_clique.getCouleur() == tabJoueurs[joueurActif-1].getCouleur())) 
							{
								
								System.out.println("Pion choisi !");
								System.out.println(joueur_clique);
								
								
								if(i != -1 && j != -1 && nombre_deplacements_restants > 0) {
									String res = joueur_clique.deplacer(i, j);
									System.out.println("res="+res);
									System.out.println(Plateau.plateau[i][j].getidCase()+" = id case de destination ["+i+", "+j+"]");
									//System.out.println("["+joueur_clique.getCoord_indexI()+","+joueur_clique.getCoord_indexJ()+"]");
									System.out.println("Les voisins : ");
									for(int k=0;k<Plateau.plateau[joueur_clique.getCoord_indexI()][joueur_clique.getCoord_indexJ()].getVoisins().size();k++) {
										System.out.println(Plateau.plateau[joueur_clique.getCoord_indexI()][joueur_clique.getCoord_indexJ()].getVoisins().get(k).getidCase());
										
									}
									
									if(res.equals("Deplacement reussi")) {
										nombre_deplacements_restants--;
										//on met a jour la vue
										vue.changeText(tabJoueurs[joueurActif-1].getPseudo() + " Deplacez un pion"
			            						+ "\n "+nombre_deplacements_restants+ " deplacements possible restant");
										
										vuePions.placer_explorateur_plateau(joueur_clique, vue.get_centre_hex(p.plateau[i][j].getHexagone()));
										System.out.println("Deplacement Reussi!");
										System.out.println("nombre_deplacements_restants="+nombre_deplacements_restants);
									}
									
								}
								
								if(nombre_deplacements_restants == 0) {
									nombre_deplacements_restants = 3;
									phaseDeJeu =3;
									changement_phase = true;
									vue.changeText("La phase de deplacement est finie. \n Cliquez sur le plateau pour passer a la phase "+phaseDeJeu+".");
								}
								System.out.println("--------------");
								
							}



							break;
							
							
							
						//PRENDRE TUILE PLATEAU	
						case 3:
							//on set la  possibilit� de passer ou non
							est_passable=false;
			        		passe.setFill(new ImagePattern(vue.getBouttonPasse().get_skip_img(est_passable),0, 0, 1, 1, true));
							
			        		//on affiche  les  instructions
			        		vue.changeText(tabJoueurs[joueurActif-1].getPseudo()+ " veuillez selectionnier une tuile.");
			        		
			        		//si un effet etait deja en  cours  on  garde les coordonees de l'ancien clic 
			        		if(effet_en_cours==true) {
			        			
			        			//l'effet qui n'as pas encore ete fait
			        			//Type_effet effet_de_tuile2 = p.plateau[clic_indexI][clic_indexJ].getEffet().getEffet();

			        			
			        		}
			        		else {
			        			
			        			if(p.plateau[i][j].voisinMer()) {
			        				if(p.presence_plage()) 
			        				{
			        					if(p.plateau[i][j].getTerrain() == Terrain.PLAGE) 
			        					{
			        						clic_indexI=i;
			        						clic_indexJ=j;
			                    			//on  ajoute l'effet a  la  main si c  rouge et si la main est pas > 6
			                    			Type_effet effet_de_tuile = p.plateau[clic_indexI][clic_indexJ].getEffet().getEffet();


			                        		if(p.plateau[i][j].getEffet().getCouleur() == Type_couleur.VERT) {
			                                    //on affiche la tuile dans la preview
			                        			vue.getZonePreview().getHexPreview().setStroke(Color.GREEN);
			                            		vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.get_effet_image(effet_de_tuile),0, 0, 1, 1, true));
			                                    vue.getZonePreview().getHexPreview().setStrokeWidth(10);
			                                    
			                                    //on applique l'effet de la tuile vert
			                                    
			                                    if(p.plateau[i][j].getEffet().getEffet() == Type_effet.BALEINE_IMMEDIAT) {
			                                    	effet_baleine_immediat(i, j);
			                                    }
			                                    else if(p.plateau[i][j].getEffet().getEffet() == Type_effet.REQUIN_IMMEDIAT) {
			                                    	effet_requin_immediat(p, i, j);
			                                    }
			                                    else if(p.plateau[i][j].getEffet().getEffet() == Type_effet.BATEAU_IMMEDIAT) {
			                                    	effet_bateau_immediat(p, i, j);
			                                    }
			                                    
			                                    //effet_en_cours=true;
			                             		vue.changeText("On a piocher une tuile imm�diate. \n Appuyer sur le plateau pour continuer.");
			                            		phaseDeJeu=4;
			                            		changement_phase = true;
			                        		}
			                        		else
			                        		{
			                        			//tuile rouge on fait ajoute � la main et on passe � la suite
			                                    effet_en_cours=false;
			                            		vue.changeText("On a piocher une tuile rouge, elle a �t ajoutee � votre main \n Appuyer sur le plateau pour continuer.");
			                            		phaseDeJeu=4;
			                            		
			                            		//on l'ajoute � la main et on affiche sa main
			                           			tabJoueurs[joueurActif-1].selectionner_tuile(clic_indexI,clic_indexJ);
			                					vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
			                                   
			                        		}

			                        		tabJoueurs[joueurActif-1].selectionner_tuile(clic_indexI,clic_indexJ);
			            					vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
			            					
			            				
			                        		p.plateau[clic_indexI][clic_indexJ].getHexagone().setFill(Color.TRANSPARENT);
			                        		p.plateau[clic_indexI][clic_indexJ].getHexagone().setStroke(Color.BLACK);
			                        		p.plateau[clic_indexI][clic_indexJ].update_voisin();
			                        		p.plateau[i][j].update_voisin();
			        					}
			        					else 
			        					{
			                				vue.changeText(tabJoueurs[joueurActif-1].getPseudo()+" Veuillez selectionner une tuile plage.");
			        					}
			        				}
			        				else 
			        				{
			        				  if(p.presence_foret())
				        				{
				        					if(p.plateau[i][j].getTerrain() == Terrain.FORET) 
				        					{
				        						clic_indexI=i;
				        						clic_indexJ=j;
				                    			//on  ajoute l'effet a  la  main si c  rouge et si la main est pas > 6
				                    			Type_effet effet_de_tuile = p.plateau[clic_indexI][clic_indexJ].getEffet().getEffet();


				                        		if(p.plateau[i][j].getEffet().getCouleur() == Type_couleur.VERT) {
				                                    //on affiche la tuile dans la preview
				                        			vue.getZonePreview().getHexPreview().setStroke(Color.GREEN);
				                            		vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.get_effet_image(effet_de_tuile),0, 0, 1, 1, true));
				                                    vue.getZonePreview().getHexPreview().setStrokeWidth(10);
				                                    
				                                    //on applique l'effet de la tuile vert
				                                    
				                                    if(p.plateau[i][j].getEffet().getEffet() == Type_effet.BALEINE_IMMEDIAT) {
				                                    	effet_baleine_immediat(i, j);
				                                    }
				                                    else if(p.plateau[i][j].getEffet().getEffet() == Type_effet.TOURBILLON) {
				                                    	effet_tourbillon(p, i, j);
				                                    }
				                                    else if(p.plateau[i][j].getEffet().getEffet() == Type_effet.REQUIN_IMMEDIAT) {
				                                    	effet_requin_immediat(p, i, j);
				                                    }
				                                    else if(p.plateau[i][j].getEffet().getEffet() == Type_effet.BATEAU_IMMEDIAT) {
				                                    	effet_bateau_immediat(p, i, j);
				                                    }
				                                    //effet_en_cours=true;
				                             		vue.changeText("On a piocher une tuile verte, veuillez utiliser son effet. \n Appuyer sur le plateau pour continuer.");
				                            		phaseDeJeu=4;
				                            		changement_phase = true;
				                        		}
				                        		else
				                        		{
				                        			//tuile rouge on fait ajoute � la main et on passe � la suite
				                                    effet_en_cours=false;
				                            		vue.changeText("On a piocher une tuile rouge, elle a ete ajoutee a votre main. \n Appuyer sur le plateau pour continuer.");
				                            		phaseDeJeu=4;
				                            		
				                            		//on l'ajoute � la main et on affiche sa main
				                           			tabJoueurs[joueurActif-1].selectionner_tuile(clic_indexI,clic_indexJ);
				                					vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
				                                   
				                        		}

				                        		tabJoueurs[joueurActif-1].selectionner_tuile(clic_indexI,clic_indexJ);
				            					vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
				            					
				            				
				                        		p.plateau[clic_indexI][clic_indexJ].getHexagone().setFill(Color.TRANSPARENT);
				                        		p.plateau[clic_indexI][clic_indexJ].getHexagone().setStroke(Color.BLACK);
				                        		p.plateau[clic_indexI][clic_indexJ].update_voisin();
				                        		p.plateau[i][j].update_voisin();
				        					}
				        					else 
				        					{
				                				vue.changeText(tabJoueurs[joueurActif-1].getPseudo()+" Veuillez selectionner une tuile foret.");
				        					}
				        				}
				        				else
				        				{
				        					if(p.plateau[i][j].getTerrain() == Terrain.MONTAGNE) 
				        					{
				        						clic_indexI=i;
				        						clic_indexJ=j;
				                    			//on  ajoute l'effet a  la  main si c  rouge et si la main est pas > 6
				                    			Type_effet effet_de_tuile = p.plateau[clic_indexI][clic_indexJ].getEffet().getEffet();


				                        		if(p.plateau[i][j].getEffet().getCouleur() == Type_couleur.VERT) {
				                                    //on affiche la tuile dans la preview
				                        			vue.getZonePreview().getHexPreview().setStroke(Color.GREEN);
				                            		vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.get_effet_image(effet_de_tuile),0, 0, 1, 1, true));
				                                    vue.getZonePreview().getHexPreview().setStrokeWidth(10);
				                                    
				                                    //on applique l'effet de la tuile vert
				                                    
				                                    if(p.plateau[i][j].getEffet().getEffet() == Type_effet.TOURBILLON) {
				                                    	effet_tourbillon(p, i, j);
				                                    }
				                                    else if(p.plateau[i][j].getEffet().getEffet() == Type_effet.REQUIN_IMMEDIAT) {
				                                    	effet_requin_immediat(p, i, j);
				                                    }
				                                    
				                                    //effet_en_cours=true;
				                             		vue.changeText("On a piocher une tuile verte, veuillez appliquer son effet.\n Appuyer sur le plateau pour continuer.");
				                            		phaseDeJeu=4;
				                            		changement_phase = true;
				                        		}
				                        		else
				                        		{
				                        			//tuile rouge on fait ajoute � la main et on passe � la suite
				                                    effet_en_cours=false;
				                            		vue.changeText("On a piocher une tuile rouge ! La tuile est ajoutee a votre main. \n Appuyer sur le plateau pour continuer.");
				                            		phaseDeJeu=4;
				                            		
				                            		//on l'ajoute � la main et on affiche sa main
				                           			tabJoueurs[joueurActif-1].selectionner_tuile(clic_indexI,clic_indexJ);
				                					vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
				                                   
				                        		}

				                        		tabJoueurs[joueurActif-1].selectionner_tuile(clic_indexI,clic_indexJ);
				            					vue.getMain().update_main_joueur(tabJoueurs, joueurActif-1);
				            					
				            				
				                        		p.plateau[clic_indexI][clic_indexJ].getHexagone().setFill(Color.TRANSPARENT);
				                        		p.plateau[clic_indexI][clic_indexJ].getHexagone().setStroke(Color.BLACK);
				                        		p.plateau[clic_indexI][clic_indexJ].update_voisin();
				                        		p.plateau[i][j].update_voisin();
				        					}
				        					else 
				        					{
				                				vue.changeText(tabJoueurs[joueurActif-1].getPseudo()+" Veuillez selectionner une tuile montagne.");
				        					}
				        				}
			        				}
			        			}
			        			else
			        			{
			        				vue.changeText(tabJoueurs[joueurActif-1].getPseudo()+" Veuillez selectionner une tuile en bord de mer.");
			        			}
			        			
			        		}
			        		

							break;
							
							
							
						//TIRE AU DE LE MONSTRE	
						case 4:
			        		//on remet la preview clean
			        		vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.getZonePreview().get_mystery_tile(), 0, 0, 1, 1, true));
			                vue.getZonePreview().getHexPreview().setStrokeWidth(3);
			                vue.getZonePreview().getHexPreview().setStroke(Paint.valueOf("#454194") );
			                //on set la  possibilite de passer ou non
			                est_passable=false;
			                passe.setFill(new ImagePattern(vue.getBouttonPasse().get_skip_img(est_passable),0, 0, 1, 1, true));
			                boolean deplacement_fini = false;
			                String instruction;
			                
			                System.out.println("Phase 4");
			                
			                vue.changeText("Veuillez lancer le de. Et appuyer sur le plateau pour continuer");
			                if(de_clic) {
			                	
			                	if(!deplacement_fini)
			                	{
			                        switch(resultat_de) 
			                        {
			                        case 1: //Requin
			                            //selection 
			                        	System.out.println("Ta trouver un requin");
			                            if(estPresent_requin())
			                            {
			                            	 
			                            	 vue.changeText("Veuillez selectionner un requin et sa case de destination");
			                            	 System.out.println("Cherchons ce requin");
			                             
		                                	
		                                	
			                            	
			                                if(creature_clique instanceof Requin) 
			                                {
			                                	System.out.println("requin trouv� !");
			                                	String move = creature_clique.deplacer_limite(i, j);
				                            	if(move.equals("Deplacement reussi"))
				                            	{
				                            		deplacement_fini = de_creature_requin(i,j);
				                            		vuePions.deplacer_creature_plateau(creature_clique, vue.get_centre_hex(
				            								p.plateau[i][j].getHexagone()));
				                            		vue.changeText("Requin a ete deplace");
				                            	}
				                            	else if(move.equals("Choisissez une case adjacente")) {
				                            		deplacement_fini = true;
				                            		vue.changeText("La phase du de est finie. \n Cliquez sur le plateau pour passer au joueur suivant");
				                            	}
			                                }
			                                else 
			                                {
			                                	vue.changeText("Veuillez selectionner un requin et sa case de destination");
			                                	System.out.println("C'est pas un Requin NON !");	
			                                }
			                                //on change de phase pcq �a martche pas 
			                               
			                    			
			                            } 
			                            else 
			                            {
			    							/*joueur_suivant();
			    							phaseDeJeu=1;*/
			                            	deplacement_fini = true;
			                            	vue.changeText("Pas de requin present sur le plateau."+ tabJoueurs[joueurActif-1].getPseudo()+ " c'est a toi !\n clique sur le plateau pour continuer");
			                            }
			                            break;
			                        case 2: //Baleine
			                        	System.out.println("Ta trouver une baleine");
			                            if(estPresent_baleine())
			                            {
			                            	 
			                            	 vue.changeText("Veuillez selectionner une baleine et sa case de destination");
			                            	 System.out.println("Cherchons cette baleine");
			                             
		                            
			                            	
			                                if(creature_clique instanceof Baleine) 
			                                {
			                                	System.out.println("baleine trouv� !");
			                                	String move = creature_clique.deplacer_limite(i, j);
				                            	if(move.equals("Deplacement reussi"))
				                            	{
				                            		deplacement_fini = de_creature_baleine(i,j);
				                            		vuePions.deplacer_creature_plateau(creature_clique, vue.get_centre_hex(
				            								p.plateau[i][j].getHexagone()));
				                            		vue.changeText("baleine a ete deplace");
				                            	}
				                            	else if(move.equals("Choisissez une case adjacente")) {
				                            		deplacement_fini = true;
				                            		vue.changeText("La phase du de est finie. \n Cliquez sur le plateau pour passer au joueur suivant");
				                            	}
			                                }
			                                else 
			                                {
			                                	vue.changeText("Veuillez selectionner un baleine et sa case de destination");
			                                	System.out.println("C'est pas un baleine NON !");	
			                                }
			                                //on change de phase pcq �a martche pas 
			                               
			                    			
			                            } 
			                            else 
			                            {
			    							/*joueur_suivant();
			    							phaseDeJeu=1;*/
			                            	deplacement_fini = true;
			                            	vue.changeText("Pas de baleine present sur le plateau."+ tabJoueurs[joueurActif-1].getPseudo()+ " c'est a toi !\n clique sur le plateau pour continuer");
			                            }
			                            break;
			                        case 3: //Serpent de mer
			                        	System.out.println("Ta trouver un serpent de Mer");
			                            if(estPresent_serpentMer())
			                            {
			                            	 
			                            	 vue.changeText("Veuillez selectionner une baleine et sa case de destination");
			                            	 System.out.println("Cherchons ce serpent de Mer");
			                             
		                            
			                            	
			                                if(creature_clique instanceof SerpentMer) 
			                                {
			                                	System.out.println("serpent de Mer trouv� !");
			                                	String move = creature_clique.deplacer_limite(i, j);
				                            	if(move.equals("Deplacement reussi"))
				                            	{
				                            		deplacement_fini = de_creature_serpentMer(i,j);
				                            		vuePions.deplacer_creature_plateau(creature_clique, vue.get_centre_hex(
				            								p.plateau[i][j].getHexagone()));
				                            		vue.changeText("serpent de Mer a ete deplace");
				                            	}
				                            	else if(move.equals("Choisissez une case adjacente")) {
				                            		deplacement_fini = true;
				                            		vue.changeText("La phase du de est finie. \n Cliquez sur le plateau pour passer au joueur suivant");
				                            	}
			                                }
			                                else 
			                                {
			                                	vue.changeText("Veuillez selectionner un serpent de Mer et sa case de destination");
			                                	System.out.println("C'est pas un serpent de Mer NON !");	
			                                }
			                                //on change de phase pcq �a martche pas 
			                               
			                    			
			                            } 
			                            else 
			                            {
			    							/*joueur_suivant();
			    							phaseDeJeu=1;*/
			                            	deplacement_fini = true;
			                            	vue.changeText("Pas de baleine present sur le plateau."+ tabJoueurs[joueurActif-1].getPseudo()+ " c'est a toi !\n clique sur le plateau pour continuer");
			                            }
			                            
			                            break;
			                        }
			                        
			                        
			                        	if(deplacement_fini) {
			                        		phaseDeJeu =1;
			                        		changement_phase = true;
			                        		joueur_suivant();
			                        		de_clic = false;
			    							creature_clique = null;
			    							System.out.println("On s'en va");
			    							deplacement_fini = false;
			                        	}
			                    	}
			                	}
			            	}
					}
					
		
            

			
			
 			//IGNORER CETTE  PARTIE ELLLE  VA BOUGER
			if(p.plateau[i][j].getHexagone().getFill()!= Color.TRANSPARENT 
					&& est_retirable == true) 
	            {
					// on change la preview
					vue.getZonePreview().getHexPreview().setFill(
							p.plateau[i][j].getHexagone().getFill());
					//on rend la tuile transparente
					p.plateau[i][j].getHexagone().setFill(Color.TRANSPARENT);      			
					p.plateau[i][j].getHexagone().setStroke(Color.BLACK);	
	            
					
	            }
	            });	
			

			wait_clic = true;
	}

	
	//handler pion main
	/**
	 * repr�sente le gestionnaire des pions de toute les mains (permet de les rendre cliquable)
	 * @param vue de type Vue
	 */
	public void set_pion_handler(Vue vue) {
		for(int i=0;i<10;i++) {
			set_pion_handler2(vue,i);
		}		
	}
	
	//handler pion main2
	/**
	 * repr�sente le gestionnaire des pions (permet de les rendre cliquable)
	 * @param vue de type Vue
	 * @param i de type int l'index
	 */
	public void set_pion_handler2(Vue vue, int i) {
		vue.getMain().getMainPion()[i].addEventHandler(MouseEvent.MOUSE_CLICKED,e -> {
			
			if(joueur_clique2 != null)
			{	
				joueur_clique2.setStroke(joueur_clique2.getFill());
			}
			vue.getMain().getMainPion()[i].setStroke(Color.BLACK);
			vue.getMain().getMainPion()[i].setStrokeWidth(5);
			joueur_clique2=vue.getMain().getMainPion()[i];
			main_clic = i;
				});			
	}
	//handler de passer	
	/**
	 * gestionnaire du bouton passer, permet de le rendre cliquable
	 * @param vue de type Vue
	 */
	public void set_passe_handler(Vue vue) {
		//pour avoir acces aux images
		ZonePasser zp = new ZonePasser();
		//gerer le clique sur la zone passer 
		
        passe.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        	
        	//si on le dropit de skip on change le button
        	//sinon on dis que le bouton est d�j� chang� et on attend le reset
        	if (est_passable == true) {
        		est_passable= false;
        		skip_clic = true;
        		passe.setFill(new ImagePattern(zp.get_skip_img(est_passable),0, 0, 1, 1, true));
        		vue.changeText("On passe la phase de jeu \n Cliquer sur le plateau pour continuer.");
        		phaseDeJeu++;
        		changement_phase = true;
        	}
        	else {
        		vue.changeText("Tu as deja passer cette phase!\n Clique sur le plateau continuer vers la phase "+phaseDeJeu+" .");
        	}
            });
	}

	//handler du de
	/**
	 * fonction qui gere le clic sur un de
	 * @param vue vue du plateau sur laquelle on veut faire appaitre le de
	 */
	public void set_de_handler(Vue vue) {
        Rectangle de = vue.getZonePreview().getDe();
        ZonePreview zp = new ZonePreview();
        
		de.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			de_clic = true;
			
            resultat_de = (int)(Math.random() * 3) + 1;
            
			switch(resultat_de) {
			case 1: //Requin
				vue.changeText("Tu as obtenu un requin !!");
	    		de.setFill(new ImagePattern(zp.get_dice_img(1), 0, 0, 1, 1, true));
				break;
			case 2:
				vue.changeText("Tu as obtenu une baleine !!");
	    		de.setFill(new ImagePattern(zp.get_dice_img(2), 0, 0, 1, 1, true));           
				break;
			case 3:
				vue.changeText("Tu as obtenu un serpent de mer !!");
	    		de.setFill(new ImagePattern(zp.get_dice_img(3), 0, 0, 1, 1, true));           
				break;
			}
		});   
	}
	//hander de main
	
	/**
	 * Verifie les clique pour un explorateur donn�
	 * @param explo
	 */
	public void set_explo_handler(Explorateur explo)
	{
		explo.getCercle().addEventHandler(MouseEvent.MOUSE_CLICKED, g-> {
			if(joueur_clique != null)
			{	
				joueur_clique.getCercle().
					setStroke(joueur_clique.getCercle().getFill());
			}
 		explo.getCercle().setStroke(Color.BLACK);
 		joueur_clique = explo;
		});
		
		explo.getCercle().addEventHandler(MouseEvent.MOUSE_ENTERED, g -> 
		 {
			 vue.getZonePreview().getHexPreview().setFill(
					 explo.getCercle().getFill());
            vue.getZonePreview().getHexPreview().setStroke(Color.BLACK);
            vue.getZonePreview().getHexPreview().setStrokeWidth(2);
            
            
            explo.getCercle().addEventHandler(MouseEvent.MOUSE_EXITED, h -> 
            {
            	vue.getZonePreview().getHexPreview().setFill(
            			new ImagePattern(vue.getZonePreview().get_mystery_tile(),
            					0, 0, 1, 1, true));
            	vue.getZonePreview().getHexPreview().setStroke(
            			Paint.valueOf("#454194") );
            	vue.getZonePreview().getHexPreview().setStrokeWidth(3);
	       }); 	
		});
	}
	
	/**
	 * Verifie les clique pour une cr�ature donn�e
	 * @param creature
	 */
	public void set_creature_handler(Pion creature)
 	{
 		creature.getRectangle().addEventHandler(MouseEvent.MOUSE_CLICKED, h-> 
 		{
 			if(creatureCliquable == true)
 			{
	 			if(creature_clique != null)
	 			{	
	 				creature_clique.getRectangle().setStroke(Color.TRANSPARENT);
	 			}
		 		creature.getRectangle().setStroke(Color.BLACK);
		 		creature_clique = creature;
 			}
 			else {
 			}
 		});
 		
 		 creature.getRectangle().addEventHandler(MouseEvent.MOUSE_ENTERED, g -> 
 		 {
 			String chemin = null;
 			if(creature instanceof Baleine) {
 				chemin ="images/baleine-preview.png";
 			}
 			else if(creature instanceof SerpentMer) {
 				chemin = "images/seaserpent-preview.png";
 			}
 			else if (creature instanceof Requin) {
 				chemin = "images/requin-preview.png";
 			}
 			File file = new File(chemin);
 	        String localUrl = file.toURI().toString();

 	        Image img = new Image(localUrl);
 	        
 			 vue.getZonePreview().getHexPreview().setFill(
 					 new ImagePattern(img, 0, 0, 1, 1, true));
             vue.getZonePreview().getHexPreview().setStroke(Color.BLACK);
             vue.getZonePreview().getHexPreview().setStrokeWidth(2);
             
             
             creature.getRectangle().addEventHandler(MouseEvent.MOUSE_EXITED, h -> 
             {
             	vue.getZonePreview().getHexPreview().setFill(
             			new ImagePattern(vue.getZonePreview().get_mystery_tile(),
             					0, 0, 1, 1, true));
             	vue.getZonePreview().getHexPreview().setStroke(
             			Paint.valueOf("#454194") );
             	vue.getZonePreview().getHexPreview().setStrokeWidth(3);
 	       }); 	
 		});
 	}
	
	/**
	 * Ajoute pour un handler pour les effets d'une main
	 */
	public void  set_main_handler() {
		for(int i=0;i<6;i++) {
			set_main_handler2(i);
		}
	}
	
	/**
	 * Ajoute un handler pour l'effet d'une main
	 * @param i
	 */
	public void  set_main_handler2(int i) {
        vue.getMain().getHexHand()[i].addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        	if(phaseDeJeu ==1) {
		        vue.getZonePreview().getHexPreview().setFill(vue.getMain().getHexHand()[i].getFill());
		        vue.getZonePreview().getHexPreview().setStroke(Color.RED);
		        vue.getZonePreview().getHexPreview().setStrokeWidth(10);
		    	main_clic=i;
        	}
        	});
        
        
        vue.getMain().getHexHand()[i].addEventHandler(MouseEvent.MOUSE_ENTERED, g -> {
            if(main_clic==-1) {
        	vue.getZonePreview().getHexPreview().setFill(vue.getMain().getHexHand()[i].getFill());
            vue.getZonePreview().getHexPreview().setStroke(Color.RED);
            vue.getZonePreview().getHexPreview().setStrokeWidth(10);
            }
            
            vue.getMain().getHexHand()[i].addEventHandler(MouseEvent.MOUSE_EXITED, h -> {
            	if(main_clic==-1) {
            	vue.getZonePreview().getHexPreview().setFill(new ImagePattern(vue.getZonePreview().get_mystery_tile(), 0, 0, 1, 1, true));
            	vue.getZonePreview().getHexPreview().setStroke(Paint.valueOf("#454194") );
            	vue.getZonePreview().getHexPreview().setStrokeWidth(3);
            	}
	       }); 
            
           
      });
	}	
	//// ######## fin event handler
	
	//Verification Presence Monstre
	
	/**
	 * Verification de la presence d'au moins une baleine sur le plateau
	 * @param p : plateau dans le quel la presence est verifie
	 * @return vrai si au moins une baleine est presente sur le plateau p, faux sinon
	 */

	public static boolean estPresent_baleine() {
		int i,j,k;
		for(i=0;i<p.plateau.length;i++) 
		{
			for(j=0;j<p.plateau[0].length;j++) 
			{
				if(p.plateau[i][j] != null) 
				{
					for(k=0; k<p.plateau[i][j].getPions().size();k++)
					{
						if(p.plateau[i][j].getPions().get(k) instanceof Baleine)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verification de la presence d'au moins d'un requin sur le plateau
	 * @param p : plateau dans le quel la presence est verifie
	 * @return vrai si au moins un requin est present sur le plateau p, faux sinon
	 */
	public static boolean estPresent_requin() {
		int i,j,k;
		for(i=0;i<p.plateau.length;i++) 
		{
			for(j=0;j<p.plateau[0].length;j++) 
			{
				if(p.plateau[i][j] != null) 
				{

					for(k=0; k<p.plateau[i][j].getPions().size();k++)
					{
						if(p.plateau[i][j].getPions().get(k) instanceof Requin) 
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verification de la presence d'au moins d'un serpent de mer sur le plateau
	 * @param p : plateau dans le quel la presence est verifie
	 * @return vrai si au moins un serpent de mer est present sur le plateau p, faux sinon
	 */
	public static boolean estPresent_serpentMer() {
		int i,j,k;
		for(i=0;i<p.plateau.length;i++) 
		{
			for(j=0;j<p.plateau[0].length;j++) 
			{

				if(p.plateau[i][j] != null) 
				{
					for(k=0; k<p.plateau[i][j].getPions().size();k++)
					{
						if(p.plateau[i][j].getPions().get(k) instanceof SerpentMer)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * permet de verifier si un nageur est present sur le plateau
	 * @return boolean true si un nageur est pr�sent, false sinon
	 */
	public static boolean estPresent_nageur() 
	{
		int i,j,k;
		for(i=0;i<p.plateau.length;i++) 
		{
			for(j=0;j<p.plateau[0].length;j++) 
			{
				if(p.plateau[i][j] != null && !(p.plateau[i][j] instanceof Tuile)) 
				{
					for(k=0; k<p.plateau[i][j].getPions().size();k++)
					{
						if(p.plateau[i][j].getPions().get(k) instanceof Explorateur)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * permet de verifier si un bateau est present sur le plateau
	 * @return boolean true si un bateau est pr�sent, false sinon
	 */
	public static boolean estPresent_bateau() {	
		int i,j,k;
		for(i=0;i<p.plateau.length;i++) 
		{
			for(j=0;j<p.plateau[0].length;j++) 
			{

				if(p.plateau[i][j] != null) 
				{
					for(k=0; k<p.plateau[i][j].getPions().size();k++)
					{
						if(p.plateau[i][j].getPions().get(k) instanceof Bateau)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	//Fin verification
	
	
	
	/**
	 * Remet les variables liees au clic a leurs valeurs neutres.
	 */
	public static void reset_clic() {
		clic_indexI = -1;
		clic_indexJ = -1;
		wait_clic=false;
	}
	
	/**
	 * Le main de l'application
	 * @param args
	 */
	public static void main(String args[]){
		
  		// Deroul� d une partie 
  		//Generation Plateau (Modele)
  		p.generer_plateau();
  		p.generer_tuile_plateau();
  		p.ajout_voisin_plateau();
  		//p.print_plateau_tuiller(); //DEBUG
  		launch(args);
  		
  		System.out.println("On ferme le jeu");
  		//Generation Plateau (Vue)

  		//Placement des serpents de mer

  		
  		// Creation Joueurs et leur placement
  		//Joueur[] tabJoueurs = DEVELOPEMENT_creation_joueur();
  			
  		
  		//Lancement jeu
  		//lancement_jeu(,tabJoueurs);
  		
  		est_retirable = true;
  		//Fin du jeu

	}
	
	/**
	 * la m�thode start
	 * @param primaryStage de type Stage
	 */
	public void start(Stage primaryStage) throws InterruptedException {
        
		
		
		stage = primaryStage;
		Scene scene = sceneMenuAccueil();
		       
        primaryStage.setScene(scene);  
        primaryStage.setTitle("TheIsland");
        primaryStage.setScene(scene);      
        
        primaryStage.show();

	}
}
 
	