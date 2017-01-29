package p2_project_2016.unisa.simulatorelement.gamefield;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.obstacle.Obstacle;
import p2_project_2016.unisa.simulatorelement.robot.soldier.Soldier;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.FireGun;

public class FieldTester {
	public static void main(String argv[]) throws CloneNotSupportedException, InsufficientEnergyException, DeadException{
		Field field=new Field();
		Obstacle o=new Obstacle(new Position(1,2));
		field.insertElement(o);
		Soldier s=new Soldier(new Position(4,4),100,new FireGun());
		field.insertElement(s);
		Soldier s2=s.clone();
		s2.setPosition(new Position(5,5));
		field.insertElement(s2);
		s2.moveDown();
		s2.fire(o,s2.getWeapon());
		System.out.println(field);
		Field f2=field.clone();
		System.out.println("\n"+f2);
		f2.deleteElementFromField(o);
		System.out.println("\n"+f2);
		System.out.println("\n"+field);
	}
}
