package p2_project_2016.unisa.simulatorelement.robot.worker;

import p2_project_2016.unisa.exception.CriticalStatusException;
import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.obstacle.Obstacle;
import p2_project_2016.unisa.simulatorelement.robot.soldier.Soldier;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.LaserGun;

public class WorkerTester {

	public static void main(String[] args) throws InsufficientEnergyException,CriticalStatusException, DeadException{
		//Istanzio un nuovo lavoratore
		Worker w1=new Worker(new Position(1,1));
		//Clono w1 in w2
		Worker w2=w1.clone();
		//controllo se sono uguali
		System.out.println("w1==w2 -> "+w1.equals(w2));
		//Controllo il toString
		System.out.println("w1: "+w1);
		//Istanzio un Soldato su cui effettuare le cure
		Soldier s=new Soldier(new Position(1,3), new LaserGun());
		//s è istanziato a destra di due posizioni da w quindi non può curarlo
		//Provoco un danno al soldato per fargli diminuire lo scudo e l'energia
		s.receiveDamage(20);
		//Stampo s
		System.out.println("s: "+s);
		//Provo a curarlo
		w1.cureEnergy(s,16);
		//Stampo s e w1
		System.out.println("s: "+s);
		System.out.println("w1: "+w1);
		//Sposto w1 vicino s
		w1.moveUp();
		//Riprovo a curarlo
		w1.cureEnergy(s,16);
		//Stampo s e w1
		System.out.println("s: "+s);
		System.out.println("w1: "+w1);
		//Provo a dargli lo scudo
		w1.cureShield(s, 1);
		//Stampo s e w1
		System.out.println("s: "+s);
		System.out.println("w1: "+w1);
		//Inserisco nuovi punti scudo e nuovi punti energia a w1
		w1.restoreEnergyChargher(16);
		w1.restoreShieldChargher(1);
		//stampo w1
		System.out.println("w1: "+w1);
		//Ricontrollo se w1 è uguale a w2
		System.out.println("w1==w2 -> "+w1.equals(w2));
		//Stampo w2
		System.out.println("w2: "+w2);
		//Istanzio un ostacolo e lo sposto
		Obstacle o= new Obstacle(new Position(1,1));
		w1.moveElement(o);
		System.out.println(o);
		System.out.println(w1);
		//Riprovo a spostarlo ma non si trova in prossimita di w1
		w1.moveElement(o);
		System.out.println(o);
		System.out.println(w1);
		
	}

}
