package p2_project_2016.unisa.interfaces;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;

/**
 * Interfaccia per gli elementi che possono curare l'energia degli elementi Curable
 * @author tullio
 */
public interface CanCureEnergy {
	/**
	 * L'elemento che invoca questo metodo deve guarire l'elemento Curable toCure
	 * @param toCure Curable elemento a cui viene rigenerata l'energia 
	 * @param quantity int quantità di energia da donare
	 * @throws InsufficientEnergyException lanciata se l'energia residua non è suffieciente a compiere l'azione
	 * @throws DeadException lanciata se dopo aver eseguito l'azione, l'elemento termina l'energia
	 */
	public void cureEnergy(Curable toCure,int quantity) throws InsufficientEnergyException, DeadException;
	/**
	 * Metodo usato dai BenchSupply per rigenerare le riserve di energia
	 * @param toReceive quantità di energia da ricevere
	 */
	public void restoreEnergyChargher(int toReceive);
	/**
	 * Restituisce la quantià di punti energia disponibili
	 * @return int quantità di punti energia che può ancora distribuire
	 */
	public int getEnergyChargher();
}
