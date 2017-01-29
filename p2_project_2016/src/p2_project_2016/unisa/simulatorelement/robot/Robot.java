package p2_project_2016.unisa.simulatorelement.robot;

import p2_project_2016.unisa.exception.CriticalStatusException;
import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.interfaces.Curable;
import p2_project_2016.unisa.interfaces.Damageable;
import p2_project_2016.unisa.interfaces.Movable;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.Element;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.Energy.Energy;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;

/**
 * Un elemento Robot
 * @author tullio
 */
public abstract class Robot extends Element implements Damageable, Curable, Movable {
	/**
	 * Robot Serializable ID
	 */
	private static final long serialVersionUID = 3619018416966574007L;
	protected Energy energy;
	/**
	 * Istanzio un nuovo Robot con energia massima in una posizione
	 * @param point Posizione in cui istanziare il robot
	 */
	public Robot(Position point) {
		super(point);
		energy=new Energy();
	}
	/**
	 * Istanzio un nuovo Robot con energia data in una posizione
	 * @param point Posizione in cui istanziare il robot
	 * @param energy Quantità di energia da assegnare, deve essere minore uguale all'energia base di un elemento
	 */
	public Robot(Position point,int energy) {
		super(point);
		this.energy=new Energy(energy);
	}
	/**
	 * Metodo privato per ridurre l'energia 
	 * @throws DeadException
	 */
	private final void reduceEnergyForMove() throws DeadException{
		energy.setEnergy(energy.getEnergy()-SimulatorConstant.energyForMove);
	}
	public void moveUp() throws DeadException, InsufficientEnergyException {
		if(energy.getEnergy()>SimulatorConstant.energyForMove){
			if(position.getY()<SimulatorConstant.maxY-1){
				setPosition(position.getX(),position.getY()+1);
				reduceEnergyForMove();
			}
		}
		else{
			throw new InsufficientEnergyException("Energia insufficiente per muoversi");
		}
	}
	public void moveDown() throws InsufficientEnergyException, DeadException {
		if(energy.getEnergy()>SimulatorConstant.energyForMove){
			if(position.getY()>=0){
				setPosition(position.getX(),position.getY()-1);
				reduceEnergyForMove();
			}
		}
		else{
			throw new InsufficientEnergyException("Energia insufficiente per muoversi");
		}
	}
	public void moveLeft() throws InsufficientEnergyException, DeadException {
		if(energy.getEnergy()>SimulatorConstant.energyForMove){
			if(position.getX()>=0){
				setPosition(position.getX()-1,position.getY());
				reduceEnergyForMove();
			}
		}
		else{
			throw new InsufficientEnergyException("Energia insufficiente per muoversi");
		}
	}
	public void moveRight() throws InsufficientEnergyException, DeadException {
		if(energy.getEnergy()>SimulatorConstant.energyForMove){
			if(position.getX()<SimulatorConstant.maxX-1){
				setPosition(position.getX()+1,position.getY());
				reduceEnergyForMove();
			}
		}
		else{
			throw new InsufficientEnergyException("Energia insufficiente per muoversi");
		}
	}
	public void receiveEnergy(int energy) throws DeadException,IllegalArgumentException {
		if(energy<0)
			throw new IllegalArgumentException("L'energia non può essere negativa");
		if(energy+this.energy.getEnergy()>SimulatorConstant.baseEnergy)
			this.energy.setEnergy(SimulatorConstant.baseEnergy);
		else
			this.energy.setEnergy(energy+this.energy.getEnergy());		
	}
	public void receiveDamage(int damage) throws DeadException,CriticalStatusException {
		int oldEnergy=this.energy.getEnergy();
		if(damage<0)
			throw new IllegalArgumentException("Il danno non può essere negativo");
		if(damage>energy.getEnergy())
			energy.setEnergy(0);
		else{
			energy.setEnergy(energy.getEnergy()-damage);
		}
		if(damage>=(oldEnergy*75/100))
			throw new CriticalStatusException();
	}
	/*
	 * Sovrascrittura di toString()
	 */
	public String toString(){
		return super.toString()+" ["+energy+"]";
	}
	/*
	 * Sovrascrittura di equals()
	 */
	public boolean equals(Object anObject){
		if(!super.equals(anObject)) return false;
		Robot r=(Robot)anObject;
		return r.energy.equals(energy);
	}
	/*
	 * Sovrascrittura di clone
	 */
	public Robot clone(){
		Robot r=(Robot) super.clone();
		r.energy=this.energy.clone();
		return r;
	}
	public String read(){
		return super.read()+
				"Energia: "+energy.getEnergy()+"\n\t";
	}
}
