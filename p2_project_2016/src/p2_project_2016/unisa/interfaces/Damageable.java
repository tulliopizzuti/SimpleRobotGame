package p2_project_2016.unisa.interfaces;

import p2_project_2016.unisa.exception.CriticalStatusException;
import p2_project_2016.unisa.exception.DeadException;

/**
 * Interfaccia per gli elementi che possono ricevere un danno
 * @author tullio
 *
 */
public interface Damageable {
	/**
	 * Riduce l'energia di un elemento di un quanitativo dato
	 * @param damage Quantità di danno da ricevere
	 * @throws DeadException lanciata se un elemento termina l'energia
	 */
	public void receiveDamage(int damage) throws DeadException,CriticalStatusException;
}
