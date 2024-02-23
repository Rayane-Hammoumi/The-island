package modele;
/**
 * 
 * Cette classe represente le des, on peut obtenir une baleine, un requin et un serpent de mer
 *
 */
public class De {
	/**
	 * Cette methode permet de generer un nombre aleatoire
	 * @return int le nombre aleatoire
	 */
	static public int tirer_monstre() {
		double random = Math.floor(Math.random() * (3) + 1);
		return (int)random;
	}
	
}
