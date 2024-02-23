package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import modele.Plateau;
import modele.Terrain;


public class Grille{

	/**
	 * plateau de jeu apres mise a jour
	 */
	Plateau nouveauPlateau;
	
	/**
	 * fonction pour acceder au nouveauPlateau
	 * @return le nouveau plateau de jeu
	 */
	public Plateau getNouveauPlateau()
	{
		return nouveauPlateau;
	}
	
	/**
	 * constructeur de la grille
	 * @param tileMap scene sur laquelle la grille est dessiner
	 * @param pl plateau de jeu back end
	 */
	public Grille(AnchorPane tileMap, Plateau pl)
	{
			init_grille(tileMap,pl);
	}
	/**
	 * constructeur vide pour avoir acces aux methodes de grille
	 */
	public Grille()
	{
	}
	//###getters & setters de grille###

	//###fonctions utiliser par init grille###
	/**
	 * fonctionne qui retourne la longueur de la ligne J en fonction des cases null du plateau
	 * @param j l'indice j p�ur lequelle on veut determiner la longueure non nul
	 * @return la taille de j sans les cases null
	 */
	public int longLine(int j)
	{
		int nb=0;
		
		switch(j) 
		{
			case 12:
			case 0:
				nb= 7;
				break;
			case 3:
			case 9:
			case 11:
			case 1:
				nb= 10;
			    break;
			case 4:
			case 6:
			case 8:
			case 10:
			case 2:
				nb= 11;
				break;
			case 5:
			case 7:
			    nb= 12;
			    break;
		}
		
		return nb;
	}	
	/**
	 * fonction qui retourne le decalage de l'axe X des hexagone
	 * @param j position j dans le tableau plateau
	 * @param decalage decalage initale de l'hexagone
	 * @return retourne le nouveau decalage pour que une ligne sur deux les hex soient bien decaler
	 */
	public double offsetX(int j,int decalage)
	{
		double nb=decalage;
		
		switch(j) 
		{
			case 12:
			case 0:
			    nb= nb*3.5;
				break;
			case 3:
			case 9:
			case 11:
			case 1:
			    nb= nb*2;
			    break;
			case 4:
			case 6:
			case 8:
			case 10:
			case 2:
			    nb= nb*1.5;
				break;
			case 5:
			case 7:
			    nb= nb*1;
			    break;
		}
		
		return nb;
	}		
	/**
	 * On a inverser le tableau de la vue en IJ et le plateau du modele en IJ cette fonction permet de palier a ca
	 * @param j indice j que l'on doit transformer pour qu'il deviennent l'indice I du plateau cote modele
	 * @return l'indice I du coter modele
	 */
    public int set_iImp(int j)
    {
    	int nb=0;
    	
    	switch(j) 
		{
			case 12:
			case 0:
				nb= 2;
				break;
			case 3:
			case 9:
			case 11:
			case 1:
				nb= 1;
			    break;
			case 4:
			case 6:
			case 8:
			case 10:
			case 2:
			case 5:
			case 7:
			    nb= 0;
			    break;
		}
    	
    	
    	return nb;
    }
 	
    /**
     * rempli l'hexagone en fonction de son type de case
     * @param i position i de l'hexagone
     * @param j position j de l'hexagone
     * @param hexagon hexagone a remplir
     * @param pl plateau de jeu cote modele
     */
    public void set_tile_fill(int i,int j,Polygon hexagon,Plateau pl)
    {

        String filePath2;

        if(pl.plateau[i][j].getTerrain() == Terrain.MONTAGNE)
        {

	    		filePath2 = "images/tileMontagne.png";
	    		hexagon.setStroke(Color.LIGHTGREY);
	    		hexagon.setStrokeWidth(2);
		        File file2 = new File(filePath2);
		        String localUrl2 = file2.toURI().toString();
		        Image tile = new Image(localUrl2);
	            hexagon.setFill(new ImagePattern(tile, 0, 0, 1, 1, true));

	   }
	   else if(pl.plateau[i][j].getTerrain() == Terrain.PLAGE) 
	   {
	    		filePath2 = "images/tilePlage.png";
	    		hexagon.setStroke(Color.BEIGE);
	    		hexagon.setStrokeWidth(2);
		        File file2 = new File(filePath2);
		        String localUrl2 = file2.toURI().toString();
		        Image tile = new Image(localUrl2);

	            hexagon.setFill(new ImagePattern(tile, 0, 0, 1, 1, true));

	    }
	    else if(pl.plateau[i][j].getTerrain() == Terrain.FORET) 
	    {
	    	filePath2 = "images/tileForet.png";
		    hexagon.setStroke(Color.FORESTGREEN);
		    hexagon.setStrokeWidth(2);
	        File file2 = new File(filePath2);
	        String localUrl2 = file2.toURI().toString();
	        Image tile = new Image(localUrl2);

            hexagon.setFill(new ImagePattern(tile, 0, 0, 1, 1, true));
		}
        
	    else {
		hexagon.setFill(Color.TRANSPARENT);
	    }
	
	}
	
 	//###dessine la grille de jeu###
 	/**
 	 * fonctionion qui dessine la grille de jeu sur la vue
 	 * @param tileMap scene sur laquelle la grille est desinner
 	 * @param pl plateau de jeu en fonction de laquelle la grille est dessiner
 	 */
    public void init_grille(AnchorPane tileMap, Plateau pl)
    {

 	int decalage = 54; //ex 45
 	int decalage2= 64; //ex 55 new 65
 	double decalageX;
 	double ratio = 0.75;
 	double decalageY = (decalage2* ratio);

 	for(int j=0;j<13;j++) {
 	        	
 	        	decalageX=offsetX(j,decalage);

 		        for (int i=0;i<longLine(j);i++)
 		        {
 		        	//set des caracteristiques de l'hexagone
 		            Polygon hexagon = new Polygon(); // tu recup l'hexa	
 					hexagon.setStrokeWidth(2);
 					hexagon.setStroke(Paint.valueOf("#000000") );   
	
 		            double x=-29.0, y=20.0;		            
 		            double x1=decalageX,x2= decalageX+(decalage/2),x3=decalageX+decalage;
 		            double y1= (decalageY*j)+((3.0/11.0)*decalage2),y2=(decalageY*j);
 		            double y3=(decalageY*j)+((8.0/11.0)*decalage2),y4=(decalageY*j)+decalage2;

 					//pos de l'hexagone
 					hexagon.getPoints().addAll(new Double[]{        
 					   x+x1, y+y1, // point en bas/gauche x,y
 					   x+x2, y+y2,// point bas/milieu          
 					   x+x3, y+y1, // point en bas/droite
 					   x+x3, y+y3,// point haut/droite  
 					   x+x2, y+y4,// point haut/milieu          
 					   x+x1, y+y3, // point haut/gauche
 					}); 						
 		            decalageX= decalageX+decalage;
 		            int iTmp;
 		            iTmp = i+ set_iImp(j);

 					pl.plateau[j][iTmp].setIndexI(j);
					pl.plateau[j][iTmp].setIndexJ(iTmp);
 					
 		             		            
 					if(pl.plateau[j][iTmp]!=null){
 						
 						pl.plateau[j][iTmp].setHexagone(hexagon);
 			            //set_tile_handler(j,iTmp,hexagon);
 			            set_tile_fill(j,iTmp,hexagon,pl);

 					}
 		            tileMap.getChildren().add(hexagon);
 		            
 		        }   
 	}
 	
 	this.nouveauPlateau= pl;
 	}

 	
 	
}


