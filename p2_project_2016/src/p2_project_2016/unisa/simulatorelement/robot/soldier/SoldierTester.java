package p2_project_2016.unisa.simulatorelement.robot.soldier;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.FireGun;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.LaserGun;

public class SoldierTester {
	public static void main(String argv[]) throws InsufficientEnergyException, DeadException{
		//Istanzio un soldato nella posizione 1,1 con un weapon Firegun
		Soldier s1=new Soldier(new Position(1,1),new FireGun());
		//Controllo clone
		Soldier s2=s1.clone();
		//Controllo il toString 
		System.out.println("S1: "+s1);
		System.out.println("S2: "+s2);
		//Istanzio un terzo soldato con una LaserGun
		Soldier s3=new Soldier(new Position(1,1),new LaserGun());
		//Stampo il terzo soldato
		System.out.println("S3: "+s3);
		//Controllo equals
		System.out.println("s3==s2 ->"+s3.equals(s2));
		System.out.println("s1==s2 ->"+s1.equals(s2));
		//Il primo soldato fa fuoco su s2
		s1.fire(s2,s1.getWeapon());
		//Controllo se il clone ha avuto successo
		System.out.println("s1: "+s1);
		System.out.println("s2: "+s2);
		//Sposto s2 in basso
		s2.moveDown();
		//Sposto s1 a sinistra
		s1.moveLeft();
		//Sposto s3 a destra
		s3.moveRight();
		//Stampo s1
		System.out.println("S1: "+s1);
		//Aggiungo 5 punti allo scudo di s2. Ne posso aggiungere al massimo SimulatorConstan.baseShield
		s2.receiveShield(5);
		//Stampo s2
		System.out.println("S2: "+s2);
		
	}
}
