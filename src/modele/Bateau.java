package modele;
import java.util.*;



/**
 * 
 * La classe Bateau represente le pion Bateau. Elle herite de la classe pion
 * @see Pion
 *
 */
public class Bateau extends Pion{

    /**
	 * pions_a_bord est une liste de pion qui repr�sente les pions a bord du bateau
	 */
    private List<Pion> pions_a_bord;
    
    /**
     * Couleur du joueur qui controle le bateau
     */
    private Type_couleur couleur;
    /**
     * Le constructeur de la classe Bateau
     */
    public Bateau() {
        nb_cases_parcourables=1;
    }

    /**
     * Cette methode permet de recuperer le nombre de pions a bord du bateau
     * @return int le nombre de pions a bord
     */
    public int nbr_pion_bateau(){
    	return pions_a_bord.size();
    }

    public Type_couleur getCouleur() 
    {
    	return couleur;
    }
    
    
    /*
    public void update_couleur() {
    	int nbr_bleu=0, nbr_vert=0, nbr_rouge=0, nbr_jaune=0;
    	for(int i =0; i<pions_a_bord.size();i++)
    	{
    		switch(pions_a_bord.get(i).getCouleur()) 
    		{
    		case ROUGE:
    			nbr_rouge++;
    			break;
    		case VERT:
    			nbr_vert++;
    			break;
    		case BLEU:
    			nbr_bleu++;
    			break;
    		case JAUNE:
    			nbr_jaune++;
    			break;
    		default:
    			System.out.println("Error");
    			break;
    		}
    	}  	
    }
    */

    
    /**
     * Cette methode permet d'ajouter un pion a la liste de pion a bord
     * @param pion le pion que l'on veut ajouter
     */
    public void ajouter_pion(Pion pion) {
    	pions_a_bord.add(pion);
        //	update_couleur();
    }
    
    /**
     * Cette methode permet de verifier si un bateau est vide
     * @return int 1 = bateau vide, 0 sinon. 
     * Pas de type booleen car bateau_est_vide() de pion renvoie 2 pour prevenir que le pion n'est pas un bateau
     */
	public int bateau_est_vide(){
        if(this.pions_a_bord.isEmpty()){
            return 1;
        }
        else{
            return 0;
        }
    }
	
	/**
     * Cette methode permet de deplacer le bateau sur le plateau
     * @param destination_i de type int represente la coordonnee de destination
     * @param destination_j de type int represente la coordonnee de destination
     * @return String un message a afficher
     */
	public String deplacer(int destination_i, int destination_j)
    { 
        int depart_i = this.getCoord_indexI();
		int depart_j = this.getCoord_indexJ();
        

        //si le bateau n'est pas vide, on vérifie si le joueur a le contrôle du bateau pour pouvoir le déplacer
        if(this.bateau_est_vide()==0)
        {
            int bleu=0, rouge=0, vert=0, jaune=0;

            for(int i=0; i<this.pions_a_bord.size(); i++)
            {
                if(this.getCouleur()==Type_couleur.BLEU)
                {
                    bleu++;
                }
                else if(this.getCouleur()==Type_couleur.ROUGE)
                {
                    rouge++;
                }
                else if(this.getCouleur()==Type_couleur.VERT)
                {
                    vert++;
                }
                else if(this.getCouleur()==Type_couleur.JAUNE)
                {
                    jaune++;
                }
            }
            //en cas d'égalité, il n'y a aucun joueur majoritaire donc tout le monde contrôle le bateau.
            if(bleu==rouge || bleu == vert || bleu== jaune || rouge== vert || rouge == jaune || vert == jaune)
            {
                
            }//sinon on recherche la couleur majoritaire
            else
            {
                List<Integer> couleurs_a_bord = new LinkedList<Integer>(); 

                couleurs_a_bord.add(bleu);
                couleurs_a_bord.add(rouge);
                couleurs_a_bord.add(vert);
                couleurs_a_bord.add(rouge);
    
                Type_couleur couleur_majoritaire=null;
    
                if(Collections.max(couleurs_a_bord)==bleu)
                {
                    couleur_majoritaire=Type_couleur.BLEU;
                }
                else if(Collections.max(couleurs_a_bord)==jaune)
                {
                    couleur_majoritaire=Type_couleur.JAUNE;
                }
                if(Collections.max(couleurs_a_bord)==vert)
                {
                    couleur_majoritaire=Type_couleur.VERT;
                }
                if(Collections.max(couleurs_a_bord)==rouge)
                {
                    couleur_majoritaire=Type_couleur.ROUGE;
                }
                Type_couleur couleur_joueur_actif=this.getCouleur();
    
                if(couleur_majoritaire!=null && couleur_joueur_actif!=couleur_majoritaire)
                {
                    return "Vous ne possédez pas le contrôle du bateau. Choisissez un autre pion";
                }
            }

        }
        else if(!Plateau.plateau[depart_i][depart_j].getVoisins().contains(Plateau.plateau[destination_i][destination_j]))
        {
            return "Choisissez une case adjacente";
        }
        
        else if(Plateau.plateau[destination_i][destination_j].getTerrain() != null)
        {
            return "Le bateau ne peut pas se d�placer hors des cases de mer";
        }
        else if(Plateau.plateau[depart_i][depart_j]==null)
        {
            return "Il n'y a pas de case à cet endroit";
        }
        else if(Plateau.plateau[destination_i][destination_j].listePions.size()>=3)
        {
         return "La case est pleine, deplacement impossible";
        }
        
        else {
    
            Plateau.plateau[depart_i][depart_j].retirer_bateau();
            if(Plateau.plateau[destination_i][destination_j].contient_type("Baleine ") && (Plateau.plateau[destination_i][destination_j].contient_type("Requin ") || Plateau.plateau[destination_i][destination_j].contient_type("SerpentMer ")))
        {
            return "le creatures de mer ont fait couler le bateau et ses passagers";
        }
            Plateau.plateau[destination_i][destination_j].ajouter_bateau();
            this.setCoord_index(destination_i, destination_j);
            
        }
 
        return "Deplacement reussi";
    }

    //si la case contient un bateau, 
	/**
	 * Cette methode permet de recuperer une liste qui contient les pions qui se trouvent a bord
	 * @return List de pion
	 */
	public List<Pion> get_Pions_a_bord()
	{
		return this.pions_a_bord;
	}
	
	/**
	 * Cette methode permet de retirer un pion de la liste des pions a bord
	 */
    public void retirer_pions_a_bord()
    {
        for(int i=0; i<this.pions_a_bord.size(); i++)
        {
            this.pions_a_bord.remove(i);
        }
    }
}   
    
