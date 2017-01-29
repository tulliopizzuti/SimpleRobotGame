package p2_project_2016.unisa.interfaces;

import p2_project_2016.unisa.exception.CriticalStatusException;
import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
/**
 * Interfaccia usata dagli elementi dotati di armi
 * @author tullio
 */
public interface CanAttack {
	/**
	 * Restituisce il tipo di arma che l'elemento usa
	 * @return Weapon tipo di arma
	 */
	public Weapon getWeapon();
	
	/**
	 * Un elemento che implementa questa interfaccia, può attaccare un altro elemento di tipo Damageable
	 * con un arma di tipo Weapon
	 * @param toAttack Damageable elemento che può essere distrutto
	 * @param weapon Weapon interfaccia arma
	 * @throws InsufficientEnergyException Invocata se chi chiama il metodo non ha abbastanza energia per eseguire l'azione
	 * @throws DeadException Invocata se l'energia di chi invoca il metodo o/e quella del nemico si esaurisce 
	 */
	public void fire(Damageable toAttack, Weapon weapon) throws InsufficientEnergyException, DeadException,CriticalStatusException;
}
