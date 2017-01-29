package p2_project_2016.unisa.interfaces;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Interfaccia che permette di disegnare gli elementi nel campo
 * e di cambiargli colore
 * @author tullio
 */
public interface Drawable {
	/**
	 * Interfaccia per gli oggetti che possono essere disegnati
	 * @param g Graphics2D
	 * @param width largherzza schermo
	 * @param height altezza schermo
	 */
	public void draw(Graphics2D g,int width,int height);
	/**
	 * Cambia il colore ad un oggetto drawable
	 * @param color Colore da impostare all'elemento
	 */
	public void setColor(Color color);
	/**
	 * Imposta il colore dell'elemento al colore di default
	 */
	public void setDefaultColor();
}
