package vue;

import java.awt.Point;
import java.io.File;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *Classe reprséentant le menu où les joueurs rentre leur pseudo
 */
public class MenuPseudo extends Menu{

	/**
	 * Affiche le bouton pour jouer 
	 *@param menu, panneau du menu sur lequel on ajoute les éléments
	 */
	public Rectangle affiche_bouton(AnchorPane menu)
	{
		Rectangle bouton = new Rectangle();
		String cheminFichier = "images/img_jouer.png";
        File fichier = new File(cheminFichier);
        String url = fichier.toURI().toString();
        Image img = new Image(url); 
        
		bouton.setX(460);
		bouton.setY(615);
		bouton.setWidth(180); 
        bouton.setHeight(50);
        bouton.setArcWidth(25.0); 
        bouton.setArcHeight(25.0); 
				
		bouton.setFill(new ImagePattern(img, 0, 0, 1, 1, true));
        
		menu.getChildren().add(bouton);	
		return bouton;
	}

	/**
	 * Affiche les champs texte où les joueurs entre leur pseudo
	 * @param nbJoueur, le nombre de joeur pour la partie
	 * @param menu, panneau du menu sur lequel on ajoute les éléments
	 * @return les champs texte contenant les pseudos des joueurs
	 */
	public TextField[] affiche_textFiel(int nbJoueur, AnchorPane menu) {
		TextField tabTextField[] = new TextField [nbJoueur];
		Text tabTexte[] =new Text[nbJoueur];
		Point tabPoints[] = new Point[nbJoueur];
		tabPoints = calcul_points(nbJoueur);
		
		for(int i =0; i<nbJoueur;i++)
		{
			tabTexte[i]= new Text();
			tabTexte[i].setText("Joueur "+ (i+1));
			tabTexte[i].setX(tabPoints[i].getX()); 
			tabTexte[i].setY(tabPoints[i].getY()); 
			tabTexte[i].setTextAlignment(TextAlignment.CENTER );
			tabTexte[i].setFont(Font.loadFont("file:images/brokencode.ttf",20));
			tabTexte[i].setFill(Color.valueOf("#5f280f"));
			
			tabTextField[i]= new TextField();
			tabTextField[i].setLayoutX(tabPoints[i].getX()+30);
			tabTextField[i].setLayoutY(tabPoints[i].getY()+10);
			tabTextField[i].setPrefHeight(30);
			tabTextField[i].setPrefWidth(300);
			
			menu.getChildren().add(tabTexte[i]);
			menu.getChildren().add(tabTextField[i]);
		}
		
		return tabTextField;
	}

	/**
	 * Calcule les points pour afficher les champs texte 
	 * en fonction du nombre de joueurs
	 * @param nbJoueur, le nombre de joueur de la partie
	 * @return les points des champs texte  
	 */
	private Point[] calcul_points(int nbJoueur) {
		Point tabPoints[] = new Point[nbJoueur];
		switch(nbJoueur) {
		case 2:
			tabPoints[0]= new Point(370,250);
			tabPoints[1]= new Point(370,350);
			break;
		case 3:
			tabPoints[0]= new Point(370,250);
			tabPoints[1]= new Point(370,350);
			tabPoints[2]= new Point(370,450);
			break;
		case 4:
			tabPoints[0]= new Point(370,150);
			tabPoints[1]= new Point(370,250);
			tabPoints[2]= new Point(370,350);
			tabPoints[3]= new Point(370,450);
			break;
		}
		return tabPoints ;
	}
}
