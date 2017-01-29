package p2_project_2016.unisa.interfaces;

import p2_project_2016.unisa.exception.DeadException;

/**
 * Interfaccia per gli elementi che implementano una energia e possono essere curati
 * @author tullio
 */
public interface Curable {
	/**
	 * Incrementa l'energia di un elemento
	 * @param energy Quantità di energia da aumentare
	 * @throws DeadException lanciata se un elemento termina l'energia
	 */
	public void receiveEnergy(int energy) throws DeadException,IllegalArgumentException;
	/**
	 * Restituisce l'energia di un elemento
	 * @return int Energia attuale dell'elemento
	 */
	public int getEnergy();
}
