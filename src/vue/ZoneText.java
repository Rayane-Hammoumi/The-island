package vue;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class ZoneText {
	/**
	 * Text d'inscutrion afficher à l'ecran
	 */
    public Text textInstruction = new Text();      

    /**
     * constructeur de la zone de txte
     * @param tileMap scene sur laquelle on veut afficher la zone de text
     */
	public ZoneText(AnchorPane tileMap)
	{
		init_zone_text(tileMap);
	}
	
	/**
	 * Fonction permetant d'obtenir le placeholder du texte d'instruction 
	 * @return retourne le placeholder du texte d'instruction
	 */
	public Text getTextInstruction()
	{
		return textInstruction;
	}

	/**
	 * fonction permettant d'initaliser le placeholder du text d'instruction
	 * @param tileMap scene sur laquelle on veut afficher le texte
	 */
	public void init_zone_text(AnchorPane tileMap){
        textInstruction.setText("");
        textInstruction.setX(730); 
        textInstruction.setY(85); 
        textInstruction.setWrappingWidth(330);
        textInstruction.setTextAlignment(TextAlignment.CENTER);
        textInstruction.setFont(Font.loadFont("file:images/brokencode.ttf", 17));
        textInstruction.setFill(Color.valueOf("#5f280f"));
        tileMap.getChildren().add(textInstruction);

	}
}
