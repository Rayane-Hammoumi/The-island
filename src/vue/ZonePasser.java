package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ZonePasser{
	/**
	 * rectangle qui sert de boutton pour passer
	 */
	Rectangle bouttonPasse;
	/**
	 * consutructure vide qui permet d'acceder aux mehtodes de passer
	 */
	public ZonePasser() {
		}
	/**
	 * consutructeur du boutton passer sur la vue
	 * @param tileMap scene sur laquelle le boutton passer est dessiner
	 */
	public ZonePasser(AnchorPane tileMap) {
		init_passe(tileMap);
		}
	
	/**
	 * fonction permettant de retourner l'image du boutton passer en fonction de sa disponibnilite
	 * @param check boolean indiquant si le boutton doit etre eteint ou allumer
	 * @return la nouvelle image du boutton
	 */
	public Image get_skip_img(Boolean check) {
		String filePath2 ;
		if(check == true) {
			filePath2 = "images/skip.png";	
		}
		else {
			filePath2 = "images/skipG.png";
		}
        
        
        File file2 = new File(filePath2);
        String localUrl2 = file2.toURI().toString();

        Image tile = new Image(localUrl2);
       
        return tile;
		
	}
	
	/**
	 * fonction qui dessine le bouton pour paser sur la scene
	 * @param tileMap scene sur laquelle le boutton passer est initialiser
	 */
	public void init_passe(AnchorPane tileMap)
	{
		Rectangle passe = new Rectangle();
		passe.setX(960);
		passe.setY(160);
		passe.setWidth(100); 
        passe.setHeight(50);
        passe.setArcWidth(25.0); 
        passe.setArcHeight(25.0); 
        passe.setStrokeWidth(2);
		passe.setStroke(Color.BLACK);
		passe.setStrokeWidth(3);
		passe.setFill(new ImagePattern(get_skip_img(true), 0, 0, 1, 1, true));
        
		tileMap.getChildren().add(passe);	
		this.bouttonPasse=passe;
	}
	/**
	 * fonction qui retourne le rectangle du boutton passe
	 * @return retourne le placeholder du button passe
	 */
	public Rectangle getBouttonPasse() {
		return bouttonPasse;
	}

}

