package vue;

import java.io.File;
import javafx.collections.*;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *Classe repr�sentant le menu d'accueil du jeu
 */
public class Menu {
	
	
	/**
	 * Affiche l'image de fond du menu
	 * @param menu, panneau du menu sur lequel on ajoute les �l�ments
	 */
	public void affiche_img_menu(AnchorPane menu){
		String cheminFichier = "images/img_menu5.png";
        File fichier = new File(cheminFichier);
        String url = fichier.toURI().toString();
        Image img = new Image(url);        
        BackgroundImage bImg = new BackgroundImage(img,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundPosition.DEFAULT,
                                                   BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        menu.setBackground(bGround);	        
	}
	
	/**
	 * Affiche le texte du menu 
	 * @param menu, panneau du menu sur lequel on ajoute les �l�ments
	 */
	public void affiche_texte(AnchorPane menu){
		Text texteIsland = new Text(); 
		Text texteBienvenue = new Text();
		
		texteIsland.setText("The Island");
		texteIsland.setX(260); 
		texteIsland.setY(330); 
		texteIsland.setTextAlignment(TextAlignment.CENTER );
		texteIsland.setFont(Font.loadFont("file:images/brokencode.ttf", 90));
		texteIsland.setFill(Color.valueOf("#5f280f"));
		
		texteBienvenue.setText("Bienvenue !");
		texteBienvenue.setX(320); 
		texteBienvenue.setY(450); 
		texteBienvenue.setTextAlignment(TextAlignment.CENTER );
		texteBienvenue.setFont(Font.loadFont("file:images/brokencode.ttf", 70));
		texteBienvenue.setFill(Color.valueOf("#5f280f"));
		
        menu.getChildren().add(texteIsland);
        menu.getChildren().add(texteBienvenue);
	}
	
	/**
	 * Affiche la comboBox, qui permet de choisir le nombre de joueurs
	 * @param menu, panneau du menu sur lequel on ajoute les �l�ments
	 * @return la comboBox du choix du nombre de joueurs
	 */
	public ComboBox affiche_comboBox(AnchorPane menu) {
		ComboBox<Integer> comboBox = new ComboBox<Integer>();
		Text texteBox = new Text();
		ObservableList<Integer> liste ;
		liste= FXCollections.observableArrayList(2,3,4);
		
		texteBox.setText("Choisissez le nombre de joueurs :");
		texteBox.setX(210); 
		texteBox.setY(560); 
		texteBox.setTextAlignment(TextAlignment.CENTER );
		texteBox.setFont(Font.loadFont("file:images/brokencode.ttf", 30));
		texteBox.setFill(Color.valueOf("#5f280f"));
		
		
		comboBox.setItems(liste);
		comboBox.setLayoutX(820);
		comboBox.setLayoutY(530);
		comboBox.setPrefWidth(30);
		comboBox.setPrefHeight(40);
		comboBox.getSelectionModel().select(2);
		
		menu.getChildren().add(texteBox);
		menu.getChildren().add(comboBox);
		
		return comboBox;
	}
	
	/**
	 * Affiche le bouton continuer du menu
	 * @param menu, panneau du menu sur lequel on ajoute les �l�ments
	 * @return le bouton continuer qui permet de changer de menu
	 */
	public Rectangle affiche_bouton(AnchorPane menu) {
		Rectangle bouton = new Rectangle();
		String cheminFichier = "images/img_continuer.png";
        File fichier = new File(cheminFichier);
        String url = fichier.toURI().toString();
        Image img = new Image(url); 
        
		bouton.setX(380);
		bouton.setY(615);
		bouton.setWidth(336); 
        bouton.setHeight(50);
        bouton.setArcWidth(25.0); 
        bouton.setArcHeight(25.0); 
		bouton.setFill(new ImagePattern(img, 0, 0, 1, 1, true));
        
		menu.getChildren().add(bouton);	
		
		return bouton;
	}
}
