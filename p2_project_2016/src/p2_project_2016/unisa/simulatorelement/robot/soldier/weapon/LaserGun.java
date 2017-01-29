package p2_project_2016.unisa.simulatorelement.robot.soldier.weapon;

import java.io.Serializable;

import p2_project_2016.unisa.interfaces.Weapon;
/**
 * Arma LaserGun
 * @author tullio
 */
public class LaserGun implements Weapon,Serializable,Cloneable{
	/**
	 * LaserGun Serializable ID
	 */
	private static final long serialVersionUID = -8778318079821367962L;
	private static final int damage=15;
	private static final int energyCost=3;
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
	public LaserGun clone() throws CloneNotSupportedException{
		return (LaserGun) super.clone();
	}
}