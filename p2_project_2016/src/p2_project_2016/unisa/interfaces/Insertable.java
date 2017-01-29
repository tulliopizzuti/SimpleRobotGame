package p2_project_2016.unisa.interfaces;

import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;

/**
 * Intefaccia che gestisce gli elementi inseribili nel campo di gioco
 * I punti vanno implementati con Position
 * @author Tullio
 */
public interface Insertable {
	/**
	 * Setta la posizione dell'elemento, 
	 * @param x Ordinata. X deve essere compreso tra 1 e maxX
	 * @param y Ascissa. Y deve essere compreso tra 1 e maxY
	 */
	public void setPosition(int x,int y);
	/**
	 * Setta la posizione dell'elemento
	 * @param point Position nuova posizione dell'elemento
	 */
	public void setPosition(Position point);
	/**
	 * Restituisce la posizione dell'elemento
	 * @return Position la posizione dell'elemento
	 */
	public Position getPosition();
	
}
