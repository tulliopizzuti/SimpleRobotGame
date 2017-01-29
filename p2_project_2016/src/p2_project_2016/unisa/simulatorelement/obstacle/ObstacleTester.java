package p2_project_2016.unisa.simulatorelement.obstacle;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;

public class ObstacleTester {

	public static void main(String[] args) throws CloneNotSupportedException, DeadException {
		//Istanzio un ostacolo nella posizione 1,1
		Obstacle o=new Obstacle(new Position(1,1));
		//Provo il toString
		System.out.println("Nuovo ostacolo o: "+o);
		//Provo il clone
		Obstacle o2 =o.clone();
		//Stampo l'elemento clonato chiamato o2
		System.out.println("Nuovo ostacolo clone di o, o2: "+o2);
		//Provoco un danno a 02
		o2.receiveDamage(20);
		//Sposto o2 in 2,3
		o2.setPosition(2, 3);
		//Controllo se il clone ha avuto effetto
		System.out.println("Ostacolo o: "+o);
		System.out.println("Ostacolo o2 dopo aver modificato energia e posizione: " +o2);
		//Controllo equals, restituisce false perchè sono diversi
		System.out.println("o2==o?->"+o2.equals(o));
		//Copio o2 in o
		o=o2.clone();
		//Controllo se o ed o2 sono uguali
		System.out.println("Ricopio o in o2. o2==o?->"+o2.equals(o));

		
	}

}
