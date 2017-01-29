package p2_project_2016.unisa.simulatorelement.elementcommonpiece.Energy;


import java.io.Serializable;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;

/**
 * Classe che gestisce l'energia di un elemento  
 * @author tullio
 *
 */
public final class Energy implements Cloneable,Serializable {
	/**
	 * Energy Serializable ID
	 */
	private static final long serialVersionUID = -8977587802449334362L;

	private int energy;
	/**
	 * Inizializza l'energia base degli elementi
	 */
	public Energy(){
		energy=SimulatorConstant.baseEnergy;
	}
	/**
	 * Inizializza l'energia a un intero dato energy.
	 * Se energy e maggiore dell'energia base o minore di zero, viene lanciata una IllegalArgumentException
	 * @param energy quantità di energia iniziale
	 */
	public Energy(int energy){
		if(energy>SimulatorConstant.baseEnergy || energy<=0)
			throw new IllegalArgumentException("Valore dell'energia maggiore dell'energia base");
		this.energy=energy;
	}
	/**
	 * Restituisce l'energia dell'elemento
	 * @return int Energia 
	 */
	public int getEnergy(){
		return energy;
	}
	/**
	 * Modifica il valore dell'energia di un elemento. Se l'energia raggiunge lo 0
	 * viene lanciata una DeadException
	 * @param energy Valore da assegnare all'energia dell'elemento. Se energy è un valore maggiore dell'energia base o minore di 0, viene 
	 * lanciata una IllegalArgumentException
	 * @throws DeadException lanciata se l'energia si riduce a 0
	 */
	public void setEnergy(int energy) throws DeadException{
		if(energy>SimulatorConstant.baseEnergy)
			throw new  IllegalArgumentException("Valore dell'energia maggiore dell'energia base");
		this.energy=energy;
		if(this.energy<=0)
			throw new DeadException();
	}
	/*
	 * Sovrascrittura di toString()
	 */
	public String toString(){
		return "Energy["+energy+"]";
	}
	/*
	 * Sovrascrittura di equals
	 */
	public boolean equals(Object anObject){
		if(anObject==null) return false;
		if(!getClass().equals(anObject.getClass())) return false;
		Energy e=(Energy) anObject;
		return energy==e.energy;
	}
	/*
	 * Sovrascrittura di clone()
	 */
	public Energy clone(){
		try{
			return (Energy) super.clone();
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
}
