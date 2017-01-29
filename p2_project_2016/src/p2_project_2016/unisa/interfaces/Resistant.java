package p2_project_2016.unisa.interfaces;

/**
 * Intefacce per gli elementi danneggiabili con un valore di resistenza
 * @author tulli
 *
 */
public interface Resistant extends Damageable {
	/**
	 * Restituisce il valore di resistenza di un elemento del simulatore
	 * @return int Valore di resistenza
	 */
	public int getResistance();
}
