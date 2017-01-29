package p2_project_2016.unisa.simulatorelement.elementcommonpiece.Energy;

import p2_project_2016.unisa.exception.DeadException;

public class EnergyTester {

	public static void main(String[] args) throws CloneNotSupportedException, DeadException {
		
		//Istanzio una nuova energia
		Energy e=new Energy();
		//Stampo l'energia e
		System.out.println("Energia e: "+e);
		
		//Imposta l'energia a 10
		e.setEnergy(10);
		//Stampa l'energia e
		System.out.println("Energia e impostata a: "+e);
		
		//Clona e in e2
		Energy e2=e.clone();
		//Stampa e2
		System.out.println("Ho clonato e: "+e +" in e2 "+ e2);
		//Controlla se e==e2
		System.out.println("e==e2? ->"+e.equals(e2));
		//Modifica e2
		e.setEnergy(100);
		//Controlla se sono ancora uguali
		System.out.println("Ho impostato e: "+e+ " controllo se sono ancora uguali ->"+e.equals(e2));

		

	}

}
