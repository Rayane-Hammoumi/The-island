package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Fenetre {

	/***
	 * constructeur de la fenetre sur laquelle s'affiche le jeu
	 * @param tileMap scene que la fenetre vas contenir
	 */
	public Fenetre(AnchorPane tileMap)
	{
		init_fenetre(tileMap);	
		}
	/**
	 * fonction qui initalise la fenetre sur laquelle la scene sera afficher	
	 * @param tileMap scene que doit contenir la fenetre
	 */
	public void init_fenetre(AnchorPane tileMap)
	{

     	// lien vers le background.
        String filePath = "images/bg.png";
        File file = new File(filePath);
        String localUrl = file.toURI().toString();
        Image img = new Image(localUrl);        
        BackgroundImage bImg = new BackgroundImage(img,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundPosition.DEFAULT,
                                                   BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);       
        tileMap.setBackground(bGround);	        
}
}
