package vue;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import modele.*;
import java.awt.Point;
import java.io.File;
import javafx.scene.image.Image;


public class Vue {
	/**
	 * polygone transparent qui sert de comparaison pour savoir si un hexagone n'est pas une tuile
	 */
    public Polygon transHex = new Polygon(); // hexagone transparent	
	/**
	 * zone ou le text d'instruction est affiché 
	 */
    ZoneText zoneText; //zone ou ecrire
	 /**
	  * zone ou la main du joueur est afficher
	  */
     VueMainJoueur main; // main du joueur
     /**
      * zone ou l'hexagone de previsualisation et ou le dé sont afficher
      */
     ZonePreview zonePreview;
     /**
      * zone ou le plateau de jeu est afficher
      */
     Grille grilleJeu;
     /**
      * zone ou le boutton passer est afficher
      */
     ZonePasser bouttonPasse;

     /**
      * scene sur laquelle la vue est dessiner
      */
     AnchorPane tileMap;
     
     /**
      * constructeur pour faire appel aux methodes de la vue sans en crée une
      */
     public Vue() 
     {     
 	 }
 	/**
 	 * Consutrcteur de la vue qui initilase toutes les variables de la vue
 	 * @param tileMap scene sur laquelle on dessine la vuie
 	 * @param pl plateau de jeu
 	 */
	public Vue(AnchorPane tileMap, Plateau pl) 
	{
        
        this.tileMap=tileMap;
        new Fenetre(this.tileMap);
        this.grilleJeu = new Grille(this.tileMap,pl);
        this.bouttonPasse= new ZonePasser(this.tileMap);
        this.zoneText = new ZoneText(this.tileMap);    
        this.zonePreview = new ZonePreview(this.tileMap);
        this.main = new VueMainJoueur(this.tileMap);  
       
	}
	
	/**
	 * On retourne la scene sur laquelle est afficher la vue
	 * @return la scene sur laquelle la vue est afficher
	 */
	public AnchorPane getTileMap() 
	{
		return tileMap;
	}

	/**
	 * On retourne la zone de la main pour acceder a ces methodes
	 * @return la zone de la main
	 */
	public VueMainJoueur getMain() 
	{
		return main;
	}

	
	/**
	 * On retour la zone du boutton passer pour pouver acceder a ses methodes
	 * @return la zone du boutton pour passer
	 */
	public ZonePasser getBouttonPasse() 
	{
		return bouttonPasse;
	}
	
	/**
	 * On retourne la grille de jeu mise a jour
	 * @return la grille de jeu mise a jour
	 */
	public Case[][] getPlateauUpdated() 
	{

		return grilleJeu.getNouveauPlateau().plateau;
	}
	
	
	/**
	 * On retour la zone de previsuliation pour pouver acceder a ses methodes
	 * @return la zonedePrevisulation
	 */
    public ZonePreview getZonePreview() 
    {
		return zonePreview;
	}

	/**
	 * fonction qui affiche le message sur la vue en dessous de instruction
	 * @param Message string du message a afficher
	 */
    public void changeText(String Message) 
    {
        this.zoneText.getTextInstruction().setText(Message);
    }    	
	
    /**
     * Fonction qui revoit le centre d'un hexagone
     * @param hex hexagone dont on veut le centre
     * @return un point contenant le centre de l'hexagone
     */
    public Point get_centre_hex(Polygon hex) 
    {
 		double x,y;
 		
 		x = hex.getPoints().get(2);
 		y = hex.getPoints().get(9) - hex.getPoints().get(3);
 		y = y/2 + hex.getPoints().get(3);
 		
 		return new Point((int)x,(int)y);
 	}

    /**
     * on prend un effet et on retourne l'image correspondante à cette effet
     * @param effet l'effet dont on cherche l'image
     * @return on retourne l'image de l'effet passer en paramettre
     */
 	public Image get_effet_image(Type_effet effet) 
{
        String filePath2;
        switch(effet) {
        case  VOLCAN:
        	filePath2 = "images/volcano.png";
        	break;
        case BALEINE_IMMEDIAT:
        	filePath2 = "images/baleine.png";
        	break;
        case TOURBILLON:
        	filePath2 = "images/tempest.png";
        	break;
        case REQUIN_IMMEDIAT:
        	filePath2 = "images/requin.png";
        	break;
        case BATEAU_IMMEDIAT:
        	filePath2 = "images/boat.png";
        	break;
        case BATEAU_DIFFERE:
        	filePath2 = "images/boat3.png";
        	break;
        case DAUPHIN:
        	filePath2 = "images/dolphin3.png";
        	break;
        case ANTI_BALEINE:
        	filePath2 = "images/baleineKill.png";
        	break;
        case ANTI_REQUIN:
        	filePath2 = "images/requinKill.png";
        	break;
        case BALEINE_DIFFERE:
        	filePath2 = "images/baleineSwitch.png";	
        	break;
        case SERPENT_MER:
        	filePath2 = "images/seaSerpentSwitch.png";
        	break;
        case REQUIN_DIFFERE:
        	filePath2 = "images/requinSwitch.png";
        	break;
        default:
        	filePath2 = "images/mysteryTile.png";
        	break;
        }

        
        
        File file2 = new File(filePath2);
        String localUrl2 = file2.toURI().toString();

        Image tile = new Image(localUrl2);
        
        return tile;
	}
	
	
}