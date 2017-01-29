package p2_project_2016.unisa.simulatorelement.elementcommonpiece.position;

public class PositionTester {
	public static void main(String argv[]) throws CloneNotSupportedException{
		/*
		 * Istanzio una nuova posizione
		 */
		Position p=new Position();
		/*
		 * Stampo la posizione
		 */
		System.out.println("Nuova posizione p "+p);
		/*
		 * Imposto x=10 e y=5
		 */
		p.setX(10);
		p.setY(5);
		System.out.println("P: "+p);
		/*
		 * Clono p in p2
		 */
		Position p2=p.clone();
		/*
		 * Stampo p2
		 * Controllo se sono uguali 
		 * Poi modifico p
		 * E ricontrollo l'uguaglianza
		 */
		System.out.println("Ho clonato p in p2= "+p2);
		System.out.println("p1= "+p);
		System.out.println("p2==p1 -> "+p2.equals(p));
		/*
		 * Imposto la x di p a 1
		 */
		p.setX(1);
		/*
		 * Stampo p
		 * Ricontrollo se sono uguali
		 */
		System.out.println("p1= "+p);
		System.out.println("Ho impostato la x di p a 1. p==p2?-> "+p2.equals(p));
		/*
		 * Controllo se sono vicini
		 */
		System.out.println("p vicino p2 -> "+p.isNear(p2));
		/*
		 * Ricopio p in p2
		 * Lo sposto di una posizione a destra
		 */
		p2=p.clone();
		p2.setX(p2.getX()+1);
		//p2.setY(8);
		System.out.println("p2 viene spostato a destra di p. p vicino p2 -> " + p.isNear(p2));
		
		/*
		 * Controllo le funzioni isUp, isDown, isLeft, isRight
		 */
		p=new Position();
		p2=new Position();
		p2.setX(p2.getX()+1);
		/*
		 * p2 ora è a destra di p 
		 * ma p non è a destra di p2
		 */
		System.out.println("p2:"+p2+ " è alla destra di p:"+p+" ? -> "+p.isRight(p2));
		System.out.println("p è alla destra di p2? -> "+p2.isRight(p));
		System.out.println("p è alla sinistra di p2? -> "+p2.isLeft(p));
		//Sposto p2 sopra a p
		p2=new Position(0,1);
		System.out.println("p2:"+p2+ " è sopra di p:"+p+" ? -> "+p.isUp(p2));
		System.out.println("p è sopra di p2? -> "+p2.isUp(p));
		
		System.out.println("p2 è sotto di p? -> "+p.isDown(p2));
		System.out.println("p è sotto di p2? -> "+p2.isDown(p));



		
	}
}
