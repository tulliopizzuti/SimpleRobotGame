package p2_project_2016.unisa;

import p2_project_2016.unisa.simulatorelement.gamefield.Field;
import p2_project_2016.unisa.ui.main.Main;
/**
 * Classe contenente il metodo main per far partire l'esecuzione
 */
public class StartSimulator {
	public static void main(String[] argv){
		Field field=new Field();
		Main main=new Main(field);
		main.setVisible(true);
	}
}