package p2_project_2016.unisa.interfaces;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;

/**
 * Interfaccia utilizzata dagli elementi che possono spostarsi nella griglia di gioco
 * @author tullio
 */
public interface Movable {
	/**
	 * Muove un elemento in alto nella griglia
	 * @throws InsufficientEnergyException lanciata se l'elemento non ha l'energia per muoversi
	 * @throws DeadException lanciata se dopo essersi mosso l'elemento ha finito l'energia
	 */
	public void moveUp() throws InsufficientEnergyException, DeadException;
	/**
	 * Muove un elemento in basso nella griglia
	 * @throws InsufficientEnergyException lanciata se l'elemento non ha l'energia per muoversi
	 * @throws DeadException lanciata se dopo essersi mosso l'elemento ha finito l'energia
	 */
	public void moveDown() throws InsufficientEnergyException, DeadException;
	/**
	 * Muove un elemento a sinistra nella griglia
	 * @throws InsufficientEnergyException lanciata se l'elemento non ha l'energia per muoversi
	 * @throws DeadException lanciata se dopo essersi mosso l'elemento ha finito l'energia
	 */
	public void moveLeft() throws InsufficientEnergyException, DeadException;
	/**
	 * Muove un elemento a destra nella griglia
	 * @throws InsufficientEnergyException lanciata se l'elemento non ha l'energia per muoversi
	 * @throws DeadException lanciata se dopo essersi mosso l'elemento ha finito l'energia
	 */
	public void moveRight() throws InsufficientEnergyException, DeadException;
}
