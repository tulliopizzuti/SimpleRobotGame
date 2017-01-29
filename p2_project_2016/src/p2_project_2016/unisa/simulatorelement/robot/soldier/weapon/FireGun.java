package p2_project_2016.unisa.simulatorelement.robot.soldier.weapon;

import java.io.Serializable;

import p2_project_2016.unisa.interfaces.Weapon;
/**
 * Arma FireGun
 * @author tullio
 */
public class FireGun implements Weapon,Serializable,Cloneable{
	/**
	 * FireGun Serializable ID
	 */
	private static final long serialVersionUID = -1402575140126871794L;
	private final static int damage=20;
	private final static int energyCost=5;
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
	public FireGun clone() {
		try {
			return (FireGun) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	

}
