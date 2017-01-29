package p2_project_2016.unisa.interfaces;
/**
 * Arma di un elemento 
 * @author tulli
 */
public interface Weapon{
	/**
	 * Restistuisce il danno che provoca l'arma
	 * @return quantità di danno inflitto
	 */
	public int damage();
	/**
	 * Prezzo da pagare per fare fuoco
	 * @return int Costo da pagare per usare l'arma
	 */
	public int energyCost();
	
}
