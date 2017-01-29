package p2_project_2016.unisa.simulatorelement.benchsupply;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.robot.soldier.Soldier;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.LaserGun;
import p2_project_2016.unisa.simulatorelement.robot.worker.Worker;

public class BenchSupplyTester {
	public static void main(String argv[]) throws InsufficientEnergyException, DeadException{
		//Istanzio un banco rifornimenti
		BenchSupply b=new BenchSupply(new Position(1,1));
		//Instanzio un soldato e un lavoratore
		Soldier s = new Soldier(new Position(1,2),new LaserGun());	
		s.receiveDamage(50);
		Worker w = new Worker(new Position(2,1));
		w.receiveDamage(50);
		//Li stampo
		System.out.println("b: "+b);
		System.out.println("s: "+s);
		System.out.println("w: "+w);
		//Riduco i rifornimenti di w
		w.moveUp();
		w.cureEnergy(s, 5);
		w.cureShield(s, 4);
		//Li stampo
		System.out.println("b: "+b);
		System.out.println("s: "+s);
		System.out.println("w: "+w);
		//Li faccio curare dal banco rifornimenti
		b.chargeElement(s);
		b.chargeElement(w);
		//Li stampo
		System.out.println("b: "+b);
		System.out.println("s: "+s);
		System.out.println("w: "+w);
	}
}
