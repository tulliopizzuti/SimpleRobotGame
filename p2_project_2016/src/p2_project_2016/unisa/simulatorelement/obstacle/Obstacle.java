package p2_project_2016.unisa.simulatorelement.obstacle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import p2_project_2016.unisa.exception.CriticalStatusException;
import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.interfaces.Curable;
import p2_project_2016.unisa.interfaces.Damageable;
import p2_project_2016.unisa.interfaces.Pesabile;
import p2_project_2016.unisa.interfaces.Resistant;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.Element;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.Energy.Energy;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;

public final class Obstacle extends Element implements Resistant,Pesabile,Damageable,Curable{
	/**
	 * Obstacle Serializable ID
	 */
	private static final long serialVersionUID = 7975560793827168356L;
	/**
	 * Energia dell'ostacolo
	 */
	private Energy energy;
	/**
	 * Colore dell'ostacolo
	 */
	private Color color;
	/**
	 * Inizializza un ostacolo in una posizione
	 * @param point Position posizione iniziale dell'ostacolo
	 */
	public Obstacle(Position point) {
		super(point);
		energy=new Energy();
		this.color=SimulatorConstant.defaultColorForObstacle;
	}
	public Obstacle(Position point,int energy) {
		super(point);
		this.energy=new Energy(energy);
		this.color=SimulatorConstant.defaultColorForObstacle;
	}
	public void receiveDamage(int damage) throws DeadException,CriticalStatusException{
		int oldEnergy=this.energy.getEnergy();
		if(damage<0)
			throw new IllegalArgumentException("Il danno non può essere negativo");
		energy.setEnergy(energy.getEnergy()-damage);
		if(damage>=(oldEnergy*75/100))
			throw new CriticalStatusException();
	}
	public int getResistance() {
		return SimulatorConstant.obstacleResistance;
	}
	public int getWeight() {
		return SimulatorConstant.obstacleWeight;
	}
	/*
	 * Sovrascrittura di toString()
	 */
	public String toString(){
		return super.toString()+" "+energy+"[ Resistance="+
				SimulatorConstant.obstacleResistance+" Weight="+
				SimulatorConstant.obstacleWeight+" ]";
	}
	/*
	 * Sovrascrittura di equals()
	 */
	public boolean equals(Object anObject){
		if(!super.equals(anObject)) return false;
		Obstacle o=(Obstacle)anObject;
		return energy.equals(o.energy);
	}
	/*
	 * Sovrascrittura di clone()
	 */
	public Obstacle clone(){
		Obstacle cloned=(Obstacle) super.clone();
		cloned.energy=this.energy.clone();
		return cloned;
	}
	/**
	 * Metodo utilizzato per disegnare un Obstacle
	 * @param g Graphics2D gafico su cui disegnare
	 * @param width int larghezza della finestra
	 * @param height int altezza della finestra
	 */
	public void draw(Graphics2D g, int width, int height) {
		int l_x=width/SimulatorConstant.maxX;;
		int l_y=height/SimulatorConstant.maxY;      
		int x=(position.getX()*l_x);           
		int y=(Math.abs((position.getY()-9))*l_y);  
		g.setColor(color);
		Rectangle e=new Rectangle(x,y,l_x,l_y);
		g.fill(e);
		g.draw(e);
		g.setColor(Color.black);
		g.drawString(getClass().getSimpleName(),x,y+(l_y/2));
	}
	public void receiveEnergy(int energy) throws DeadException, IllegalArgumentException {
		if(energy<0)
			throw new IllegalArgumentException("L'energia non puï¿½ essere negativa");
		if(energy+this.energy.getEnergy()>SimulatorConstant.baseEnergy)
			this.energy.setEnergy(SimulatorConstant.baseEnergy);
		else
			this.energy.setEnergy(energy+this.energy.getEnergy());	
	}
	public int getEnergy() {
		return energy.getEnergy();
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public void setDefaultColor() {
		this.color=SimulatorConstant.defaultColorForObstacle;
	}
	public String read(){
		return super.read()+"Energia: "+energy.getEnergy()+"\n\t"+
				"Resistenza: "+SimulatorConstant.obstacleResistance+"\n\t"+
				"Peso: "+SimulatorConstant.obstacleWeight+"\n";
	}
}
