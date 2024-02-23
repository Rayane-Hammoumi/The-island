package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class ZonePreview{
	/**
	 * hexagone de previsualisation pour afficher une version plus grandes des elements du terrain
	 */
	public Polygon hexPreview;
	/**
	 * rectangle de dé afficher sur la vue
	 */
	public Rectangle de;
	/**
	 * fonction qui retourne l'hexagone de previsualisation
	 * @return le placeholder de lhexagone de previsualisation
	 */
	public Polygon getHexPreview() {
		return hexPreview;
	}

	/**
	 * constructeur de la zone de previsulation qui initialise etr affiche l'hexagone de preview et le de
	 * @param tileMap scene ou on veut dessiner la zone
	 */
	public ZonePreview(AnchorPane tileMap) {
		init_dice_hex(tileMap);
	}
	/**
	 * consutrcteur vide pour acceder aux mehtode de zonePreview
	 */
	public ZonePreview() {
	}
	/**
	 * fonction qui permet d'obtenir l'image de la tuile mystere
	 * @return l'image de la tuile mystere
	 */
	public Image get_mystery_tile() {
        String filePath2 = "images/mysteryTile.png";
        File file2 = new File(filePath2);
        String localUrl2 = file2.toURI().toString();

        Image tile = new Image(localUrl2);
        
        return tile;
	}
	/**
	 * fonction qui retourne la nouvelle image du de en fonction du resultat de lancer
	 * @param random resultat obetenu par le de
	 * @return retourne l'image du de 
	 */
	public Image get_dice_img(double random) {
        String filePath2;
        if (random == 3) {
    		 filePath2 = "images/de2.png";
        }
        else if(random==2) {
    		 filePath2 = "images/de1.png";
        }
        else {
    		 filePath2 = "images/de3.png";
        }
       
        
        
        File file2 = new File(filePath2);
        String localUrl2 = file2.toURI().toString();

        Image tile = new Image(localUrl2);
        
        return tile;
	}
 	/**
 	 * fonction qui retourne l'image neutre du de (de avec un '?')
	 * @return retourne l'image du de 
 	 */
	public Image set_dice_img() {
        String filePath2;
  
    	filePath2 = "images/de.png";

        
        
        File file2 = new File(filePath2);
        String localUrl2 = file2.toURI().toString();

        Image tile = new Image(localUrl2);
        
        return tile;
	}
	
 	/**
 	 * fonction qui initalise visuellement  hexagone de previsualisation & le de
 	 * @param tileMap la scene sur lequelle l hexagone & le de est afficher
 	 */
	public void init_dice_hex(AnchorPane tileMap) 
	{
		this.hexPreview = new Polygon(); // tu recup l'hexa	
		//hexa mystere & dice
        double x=749.0, y=160.0;     
        //pos de l'hexagone mystere
        hexPreview.getPoints().addAll(new Double[]{        
    			(129.0/107.0)*x, y, // point en haut/droite x,y
    			(942.75/749.0)*x, (266.25/160.0)*y,// point milieu/droite          
    		    (903.0/749.0)*x, (372.5/160.0)*y, // point en bas/droite x,y
    		    (787.0/749.0)*x, (372.5/160.0)*y, // point en bas/gauche x,y
    		    x, (266.25/160.0)*y,// point milieu/gauche   
    		    (787.0/749.0)*x, y, // point en haut/gauche x,y
    		}); 
        
        hexPreview.getLayoutX();



        
        hexPreview.setFill(new ImagePattern(get_mystery_tile(), 0, 0, 1, 1, true));
        hexPreview.setStrokeWidth(3);
        hexPreview.setStroke(Paint.valueOf("#454194") );
        tileMap.getChildren().add(hexPreview);
	     
        //dï¿½
        int value=85;
        this.de = new Rectangle();
        de.setX(965); 
        de.setY(230); 
        de.setWidth(value); 
        de.setHeight(value);
        de.setArcWidth(25.0); 
        de.setArcHeight(25.0);  

        
        de.setFill(new ImagePattern(set_dice_img(), 0, 0, 1, 1, true));      
		de.setStrokeWidth(2);
		de.setStroke(Paint.valueOf("#000000") );
  
        
        
        tileMap.getChildren().add(de);		
	}

	/**
	 * fonction qui retourne le placehodler du de
	 * @return le rectangle du de afficher sur le terrain
	 */
	public Rectangle getDe() {
		return de;
	}
	
}
