package p2_project_2016.unisa.ui.drawcomponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

import p2_project_2016.unisa.interfaces.Drawable;
/**
 * Disegna il campo e i suoi componenti
 * @author tullio
 *
 */
public class DrawComponent extends JComponent {
	/**
	 * DrawComponent JComponent ID
	 */
	private static final long serialVersionUID = 1L;
	private Drawable component;
	public DrawComponent(Drawable d){
		this.component=d;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D) g;
		component.draw(g2, getWidth(), getHeight());
	}
}
