package vue;

import java.awt.Point;
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modele.*;

public class VuePions {

	public VuePions()
	{
	}

	//////////////////////		Pion Joueur		////////////////////////////////

	/**
	  Constructeur des pions explorateur pour la vue
	 * @param explo, le pion a initialise
	 * @param tileMap, la panneau sur lequel ajoute le pion
	 */
	public void init_cercle(Explorateur explo, AnchorPane tileMap)
 	{
 		Circle cercle = explo.getCercle();
 		set_couleur_cercle(cercle, explo);		 
 		tileMap.getChildren().add(cercle);
 	}
	
	/**
	 * Place le pion exlorateur sur le plateau
	 * @param explo, l'explorateur a placer
	 * @param pointCase, le centre de la case dans laquelle on place le joueur
	 */
	public void placer_explorateur_plateau(Explorateur explo, Point pointCase) 
 	{
 		Circle cercle = explo.getCercle();
 		Point pointCercle;
 		pointCercle = get_centre_cercle(pointCase, explo.getPosition());
 		cercle.setCenterX(pointCercle.getX());
 		cercle.setCenterY(pointCercle.getY());
 		cercle.setRadius(4.0);
 		cercle.setVisible(true);
 	}
 	/**
 	 * Deplace le pion explorateur sur le plateau 
 	 * @param explo, l'explorateur a deplacer
 	 * @param pointCase, le centre de la case dans laquelle on deplace le joueur
 	 */
 	public void deplacer_explorateur_plateau(Explorateur explo, Point pointCase)
 	{
 		explo.getCercle().setVisible(false);
 		placer_explorateur_plateau(explo, pointCase);
 	}
 	/**
 	 * Permet de donner la couleur du cercle du pion 
 	 * en fonction de la couleur de l'explorateur
 	 * @param cercle, le cercle dont on modifie la couleur
 	 * @param explo, l'explorateur a qui est associ� le cercle
 	 */
 	public void set_couleur_cercle(Circle cercle, Explorateur explo)
	{
		switch(explo.getCouleur())
		{
			case BLEU:
				cercle.setFill(Color.BLUE);
				cercle.setStroke(Color.BLUE);
				break;
			case ROUGE :
				cercle.setFill(Color.RED);
				cercle.setStroke(Color.RED);
				break;
			case JAUNE :
				cercle.setFill(Color.YELLOW);
				cercle.setStroke(Color.YELLOW);
				break;
			case VERT :
				cercle.setFill(Color.GREEN);
				cercle.setStroke(Color.GREEN);
				break;
		}
	}
 	
	///////////////////////		Pion Creature	////////////////////////////////

 	/**
	 * Constructeur des pions creature(Baleine,requin, serpent de mer)
	 * pour la vue 
	 * @param creature, la creature a initialisee
	 * @param tileMap, le panneau sur lequel on ajoute le pion
	 */
 	public void init_rectangle(Pion creature, AnchorPane tileMap)
 	{
 		Rectangle rect = creature.getRectangle();
 		set_img_rectangle(creature);
 		tileMap.getChildren().add(rect);
 	}
 	
 	/**
 	 * Permet de mettre une image sur les pions creature en fonction de la creature
 	 * @param creature, le pion auquel on ajoute une image
 	 */
 	public void set_img_rectangle(Pion creature) {
		Rectangle rect = creature.getRectangle();
		String chemin = null;
		if(creature instanceof Baleine) {
			chemin ="images/baleineP-shade.png";
		}
		else if(creature instanceof SerpentMer) {
			chemin = "images/seaSerpentP-shade.png";
		}
		else if (creature instanceof Requin) {
			chemin = "images/requinP-shade.png";
		}
		File file = new File(chemin);
        String localUrl = file.toURI().toString();

        Image img = new Image(localUrl);
        rect.setFill(new ImagePattern(img, 0, 0, 1, 1, true));
        
	}

	/**
 	 * Place le pion creature sur le plateau
	 * @param creature, la creature a placer
	 * @param pointCase, le centre de la case dans laquelle on place la creature
 	 */
 	public void placer_creature_plateau(Pion creature, Point pointCase)
 	{
 		Rectangle rect = creature.getRectangle();
 		Point pointRectangle;
 		double taille;
 		taille =20;
 		pointRectangle = get_hg_rectangle(pointCase,creature.getPosition(),taille);
 		rect.setX(pointRectangle.getX()-(taille/4));
 		rect.setY(pointRectangle.getY()-(taille/4));
 		rect.setWidth(taille); 
 		rect.setHeight(taille);
 		rect.setArcWidth(5.0); 
 		rect.setArcHeight(5.0); 
 		rect.setStrokeWidth(2);
 		rect.setVisible(true);
 	}
 	/**
 	 * Deplace le pion creature sur le plateau 
 	 * @param explo, la creature a deplacer
 	 * @param pointCase, le centre de la case dans laquelle on deplace la creature
 	 */
 	public void deplacer_creature_plateau(Pion creature, Point pointCase)
 	{
 		creature.getRectangle().setVisible(false);
 		placer_creature_plateau(creature, pointCase);
 	}
	/**
	 * Calcule le point haut gauche des pions creature 
	 * en fonction de leur position dans la case
	 * @param centreCase, le centre de la case dans laquelle est la creature
	 * @param position, la position de la creature dans la case
	 * @param taille, la taille du rectangle associ� a la creature
	 * @return le point haut gauche du rectangle a placer
	 */
	public Point get_hg_rectangle(Point centreCase, int position, double taille)
	{
		Point hg= new Point(0,0);
		double x,y;
		x = get_centre_cercle(centreCase,position).getX();
		y = get_centre_cercle(centreCase,position).getY();
		switch(position)
		{
			case 1 :
				hg.setLocation(x, y-(taille/4));
				break;
			case 2 :
				hg.setLocation(x-(taille/2), y-(taille/4));			
				break;
			case 3 :
				hg.setLocation(x, y-(taille/4));
				break;
			case 4 :
				hg.setLocation(x-(taille/2), y-(taille/4));
				break;
			case 5 :
				hg.setLocation(x-(taille/4), y);			
				break;
			case 6 :
				hg.setLocation(x-(taille/4), y-(taille/2));
				break;
			default:
				hg.setLocation(x,y);
		}
		return hg;
	}
	
	
	////////////////////////	Commun		////////////////////////////////////

	/**
	 * Calcule le point centrale des pions a placer dans une case 
	 * en fonction de leur position dans la case
	 * @param centreCase, le centre de la case dans laquelle le pion est plac�
	 * @param position, la position du pion dans la case
	 * @return le centre du pion a placer
	 */
	public Point get_centre_cercle(Point centreCase, int position)
	{
		Point centreCercle = new Point(0,0);
		double x,y;
		x = centreCase.getX();
		y = centreCase.getY();
		switch(position)
		{
			case 1 :
				centreCercle.setLocation(x-20, y-11);
				break;
			case 2 :
				centreCercle.setLocation(x+20,y-11);			
				break;
			case 3 :
				centreCercle.setLocation(x-20,y+11);
				break;
			case 4 :
				centreCercle.setLocation(x+20,y+11);
				break;
			case 5 :
				centreCercle.setLocation(x,y-24);			
				break;
			case 6 :
				centreCercle.setLocation(x, y+24);
				break;
			default:
				centreCercle.setLocation(x,y);
		}
		return centreCercle;
	}
	 	
}





