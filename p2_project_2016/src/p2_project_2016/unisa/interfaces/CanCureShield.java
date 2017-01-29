package p2_project_2016.unisa.interfaces;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;

/**
 * Interfaccia per gli elementi che possono curare lo scudo degli elementi ShieldRestorable
 * @author tullio
 */
public interface CanCureShield {
	/**
	 * Metodo chiamato dagli elementi che possono rigenerare lo scudo di altri
	 * @param shield ShieldRestorable un elemento che ha uno scudo che può essere curato
	 * @param quantityShield int quantità di scudo da donare
	 * @throws InsufficientEnergyException lanciata se l'energia residua non è suffieciente a compiere l'azione
	 * @throws DeadException se dopo aver eseguito l'azione, l'elemento termina l'energia
	 */
	public void cureShield(ShieldRestorable shield,int quantityShield) throws InsufficientEnergyException, DeadException;
	/**
	 * Ricarica i punti scudo che l'elemento può donare agli altri
	 * @param toReceive quantità di scudi da ricevere
	 */
	public void restoreShieldChargher(int toReceive);
	/**
	 * Restituisce la quantià di punti scudo disponibili
	 * @return int quantità di punti scudo che può ancora distribuire
	 */
	public int getShieldChargher();
}
