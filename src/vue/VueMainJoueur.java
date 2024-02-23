package vue;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import modele.Type_couleur;
import javafx.scene.text.Text;
import modele.Joueur;
import modele.Type_effet;
public class VueMainJoueur{
    /**
     * tableau des hexagon de la main du joueur
     */
	Polygon hexHand[] = new Polygon[6];
	/**
	 * tableau des pions dans la main du joueur
	 */
    Circle mainPion[] = new Circle[10];
    /**
     * tableau des valeurs a afficher pour chaque pion
     */
    Text valeurPion[] = new Text[10];
    
    /**
     * constructeur de la classe vueMainJoueur qui initalisation les placeholder des pions et de la main
     * @param tileMap scene sur laquelle la main du joueur est affiche
     */
	public VueMainJoueur(AnchorPane tileMap)
	{
		init_hand(tileMap);
		initPion(tileMap);
	}

	/**
	 * fonction qui initialise les 6 placeholder d'hexagone de la main 
     * @param tileMap scene sur laquelle la main du joueur est affiche
	 */
 	public void init_hand(AnchorPane tileMap) 
 	{
        double ratio=2.5 ;
        double decalage = 193.75; //ex 45
        double decalage2= 212.5; //ex 55 new 65
        double decalageX = (decalage/ratio)+15;
        double decalageY = (decalage2/ratio)+15;
        int k=0;
        
		for(int j=0;j<2;j++) {
		        	
			        for (int i=0;i<3;i++)
			        {
			        	//set des caracteristiques de l'hexagone
			            Polygon hexagon = new Polygon(); // tu recup l'hexa	
			            hexHand[k] = hexagon;
			            hexHand[k].setStrokeWidth(2);
			            hexHand[k].setStroke(Paint.valueOf("#000000") );   
			            //hexHand[k].setFill(new ImagePattern(get_mystery_tile(), 0, 0, 1, 1, true));
			            hexHand[k].setVisible(false);
			            double x=765.0, y=475.0;		            
			            ratio=2.3;
	
						//pos de l'hexagone
			            hexHand[k].getPoints().addAll(new Double[]{ 
						   x+(154.0/ratio)+(decalageX*i), y+0/ratio+(decalageY*j) , // point haut/gauche
						   x+193.75/ratio+(decalageX*i), y+(106.25/ratio)+(decalageY*j) , // point en bas/gauche x,y
						   x+154.0/ratio+(decalageX*i), y+212.5/ratio+(decalageY*j),// point bas/milieu          
						   x+38.0/ratio+(decalageX*i), y+212.5/ratio+(decalageY*j), // point en bas/droite
						   x+0.00/ratio+(decalageX*i), y+106.25/ratio+(decalageY*j),// point haut/droite  
						   x+38.0/ratio+(decalageX*i), y+0.0+(decalageY*j),// point haut/milieu          
						}); 
			            
			            tileMap.getChildren().add(hexHand[k]);
			            //int f=k;

			            k++;

			        }
		}
 	}
 	

 	
 	/**
	 * fonction qui initialise les 10 placeholder des pions de la main 
     * @param tileMap scene sur laquelle la main du joueur est affiche
 	 */
 	public void initPion(AnchorPane tileMap)
 	{
 		
 		//un cercle
 		//on affiche
 		//un text en dessous
 		
 		for(int i=0;i<10;i++) {

 	 		mainPion[i] =new Circle();
 	 		valeurPion[i] = new Text();
 	 		
 			int decalageX =60;
 			int decalageY =80;
 			
 			if(i>4) {
 				mainPion[i].setCenterX(778.0+((i-5)*decalageX));
 				mainPion[i].setCenterY(510.0+decalageY);
 				valeurPion[i].setX(771.0+((i-5)*decalageX)); 
 				valeurPion[i].setY(500+decalageY+60);
 			}
 			else {
 				mainPion[i].setCenterX(778.0+(i*decalageX));
 				mainPion[i].setCenterY(510.0);
 				valeurPion[i].setX(771.0+(i*decalageX)); 
 				valeurPion[i].setY(560.0);  		 		
 			}
 			
 			mainPion[i].setRadius(25.0);
 	 		mainPion[i].setVisible(true);
 	 		valeurPion[i].setFill(Color.WHITE); 	 		
 	 		valeurPion[i].setStroke(Color.BLACK);
 	 		valeurPion[i].setText("2");
 	        valeurPion[i].setWrappingWidth(10);
 	        valeurPion[i].setTextAlignment(TextAlignment.LEFT);
 	        valeurPion[i].setFont(Font.loadFont("file:images/brokencode.ttf", 22));
 	        
 	        
 	        tileMap.getChildren().add(valeurPion[i]);
 	        tileMap.getChildren().add(mainPion[i]);
 	 		
 		}
 		
 	}
 		
 	/**
 	 * fonction qui retourne le tableau de placeholder de la main
 	 * @return tableau des placeholder d hexagone de la main
 	 */
	public Circle[] getMainPion()
	{
		return mainPion;
	}
	/**
 	 * fonction qui retourne le tableau de placeholder de la main
 	 * @return tableau des placeholder de pion de la main
 	 */
	public Polygon[] getHexHand()
	{
		return hexHand;
	}

	/**
	 * fonction qui permet de set le tableau de placehodler de la main pions de la main
	 * @param mainPion tableau de la main du pion du joueur
	 */
	public void setMainPion(Circle[] mainPion)
	{
		this.mainPion = mainPion;
	}

	/**
	 * fonction qui redessine la main de pion du joueur
	 * @param tabJoueur tableau contenant les Joueurs
	 * @param noJoueur le numero du joueur dont on veut mettre la main a jour
	 */
	public void update_main_pion(Joueur[] tabJoueur, int noJoueur)
	{
			int nJoueur=noJoueur-1;
		
			for(int i=0;i<10;i++) {
				Type_couleur couleur = tabJoueur[nJoueur].get_explo_placer(i).getCouleur();
				int valeur = tabJoueur[nJoueur].get_explo_placer(i).getValeur();
				
				if(!(tabJoueur[nJoueur].get_explo_placer(i).is_pion_place())) {
					if(couleur == Type_couleur.ROUGE) {
						mainPion[i].setFill(Color.RED);
						mainPion[i].setStroke(Color.RED);

						valeurPion[i].setText(Integer.toString(valeur));
					}
					else if(couleur == Type_couleur.BLEU) {
						mainPion[i].setFill(Color.BLUE);
						valeurPion[i].setText(Integer.toString(valeur));
						mainPion[i].setStroke(Color.BLUE);

					}
					else if(couleur == Type_couleur.JAUNE) {
						mainPion[i].setFill(Color.YELLOW);
						valeurPion[i].setText(Integer.toString(valeur));
						mainPion[i].setStroke(Color.YELLOW);

					}		
					else {
						mainPion[i].setFill(Color.GREEN);
						valeurPion[i].setText(Integer.toString(valeur));
						mainPion[i].setStroke(Color.GREEN);

					}
					mainPion[i].setVisible(true);
					valeurPion[i].setVisible(true);
				}
				else {
					mainPion[i].setVisible(false);
					valeurPion[i].setVisible(false);
				}

			}
			//on recup la couleur
			//on recup la valeur
			// on boucle sur 10
			// on update
		}

	/**
	 * fonction qui redessine la main de cartes du joueur
	 * @param tabJoueur tableau contenant les Joueurs
	 * @param noJoueur le numero du joueur dont on veut mettre la main a jour
	 */
	public void update_main_joueur(Joueur[] tabJoueurs, int joueurActif)
	{
		for(int i=0;i< tabJoueurs[joueurActif].getListEffetMain().size();i++)
		{
			Type_effet effet_main = tabJoueurs[joueurActif].getListEffetMain().get(i);

			Vue v = new Vue();
			hexHand[i].setFill(new ImagePattern(v.get_effet_image(effet_main), 0, 0, 1, 1, true));  
			hexHand[i].setStroke(Color.RED);
			hexHand[i].setStrokeWidth(5);
			hexHand[i].setVisible(true);
		}
	
		for(int j=tabJoueurs[joueurActif].getListEffetMain().size();j<6;j++)
		{
			hexHand[j].setVisible(false);
		}
	}

}
