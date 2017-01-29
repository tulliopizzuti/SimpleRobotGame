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
	 * @param shield ShieldRestorable un elemento che ha uno scudo che pu� essere curato
	 * @param quantityShield int quantit� di scudo da donare
	 * @throws InsufficientEnergyException lanciata se l'energia residua non � suffieciente a compiere l'azione
	 * @throws DeadException se dopo aver eseguito l'azione, l'elemento termina l'energia
	 */
	public void cureShield(ShieldRestorable shield,int quantityShield) throws InsufficientEnergyException, DeadException;
	/**
	 * Ricarica i punti scudo che l'elemento pu� donare agli altri
	 * @param toReceive quantit� di scudi da ricevere
	 */
	public void restoreShieldChargher(int toReceive);
	/**
	 * Restituisce la quanti� di punti scudo disponibili
	 * @return int quantit� di punti scudo che pu� ancora distribuire
	 */
	public int getShieldChargher();
}
