package p2_project_2016.unisa.exception;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import p2_project_2016.unisa.interfaces.Curable;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.simulatorelement.Element;
import p2_project_2016.unisa.simulatorelement.gamefield.Field;

/**
 * Il lancio di questa eccezione vuol dire che un elemento ha terminato l'energia
 * @author tullio
 */
public class DeadException extends Exception {
	/**
	 * DeadException ID
	 */
	private static final long serialVersionUID = 2593075885973318789L;
	/**
	 * Istanzia una nuova eccezzione
	 */
	public DeadException(){
		super("Elemento morto");
	}
	/**
	 * Istanzia una nuova eccezione e elimina dal campo di gioco
	 * gli eventuali elementi che hanno terminato l'energia
	 * @param field Campo in cui controllare se ci sono elementi che hanno terminato l'energia
	 */
	public DeadException(Field field){
		ArrayList<Insertable> temp=new ArrayList<Insertable>();
		for(Insertable i:field.getElements()){
			if(i instanceof Curable){
				if(((Curable) i).getEnergy()<=0){
					temp.add(i);
				}
			}
		}
		for(Insertable i:temp){
			field.deleteElementFromField((Element) i);
			JOptionPane.showMessageDialog(null,i+" ha terminato l'energia");
		}
	}
}
