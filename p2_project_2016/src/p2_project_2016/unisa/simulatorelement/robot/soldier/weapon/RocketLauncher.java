package p2_project_2016.unisa.simulatorelement.robot.soldier.weapon;

import java.io.Serializable;

import p2_project_2016.unisa.interfaces.Weapon;
/**
 * Arma RocketLauncher
 * @author tullio
 */
public class RocketLauncher implements Weapon,Serializable,Cloneable{
	/**
	 * RocketLauncher Serializable ID
	 */
	private static final long serialVersionUID = 593593005834657735L;
	private static final int damage=25;
	private static final int energyCost=7;
	public int damage() {
		return damage;
	}
	public int energyCost() {
		return energyCost;
	}
	/*
	 * Sovrascrittura di toString
	 */
	public String toString(){
		return getClass().getSimpleName()+"[ Danno= "+damage+" Energia Consumata= "+energyCost+"]";
	}
	/*
	 * Sovrascrittura di equals
	 */
	public boolean equals(Object anObject){
		if(anObject==null)return false;
		if(getClass().equals(anObject.getClass()))return true;
		return false;
	}
	/*
	 * Sovrascrittura di clone
	 */
	public RocketLauncher clone() throws CloneNotSupportedException{
		return (RocketLauncher) super.clone();
	}

}
