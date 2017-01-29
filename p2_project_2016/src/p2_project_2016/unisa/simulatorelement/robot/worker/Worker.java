package p2_project_2016.unisa.simulatorelement.robot.worker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.interfaces.CanCureEnergy;
import p2_project_2016.unisa.interfaces.CanCureShield;
import p2_project_2016.unisa.interfaces.Curable;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.interfaces.Pesabile;
import p2_project_2016.unisa.interfaces.ShieldRestorable;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.Element;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.robot.Robot;
/**
 * Robot che può curare altri robot
 * @author tulli
 */
public class Worker extends Robot implements CanCureEnergy,CanCureShield {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5928307275618469815L;
	/**
	 * Quantità di scudi che un robot può ricaricare
	 */
	private int shieldChargher;
	/**
	 * Quantità di energia che un robot può ricaricare
	 */
	private int energyChargher;
	/**
	 * Peso del worker
	 */
	private int weight;
	/**
	 * Colore del robot
	 */
	private Color color;
	/**
	 * Istanzia un robot lavoratore 
	 * @param point Posizione iniziale
	 */

	
	public Worker(Position point) {
		super(point);
		shieldChargher=SimulatorConstant.maxShieldRecharger;
		energyChargher=SimulatorConstant.maxEnergyRecharger;
		weight=(SimulatorConstant.baseEnergy*SimulatorConstant.weightPercentage)/100;
		this.color=SimulatorConstant.defaultColorForWorker;
	}
	/**
	 * Istanzia un robot lavoratore
	 * @param point Posizione iniziale
	 * @param energy Energia iniziale
	 */
	public Worker(Position point, int energy) {
		super(point, energy);
		shieldChargher=SimulatorConstant.maxShieldRecharger;
		energyChargher=SimulatorConstant.maxEnergyRecharger;
		weight=(energy*SimulatorConstant.weightPercentage)/100;
		this.color=SimulatorConstant.defaultColorForWorker;
	}
	/**
	 * Calcola il peso del robot in base alla sua energia
	 */
	private void calculateWeight(){
		weight=(this.energy.getEnergy()*SimulatorConstant.weightPercentage)/100;
	}
	/**
	 * Restituisce il peso del robot Lavoratore
	 * @return int Peso
	 */
	public int getWeight(){
		return weight;
	}
	public void cureShield(ShieldRestorable shield, int quantityShield) throws InsufficientEnergyException, DeadException {
		if(shield instanceof Insertable){
			if(((Insertable) shield).getPosition().isNear(position)){
				if(this.energy.getEnergy()>SimulatorConstant.energyForCure){ 
					if(shieldChargher>=quantityShield){
						if(shield instanceof ShieldRestorable){
							shield.receiveShield(quantityShield);
							shieldChargher-=quantityShield;
							this.receiveDamage(SimulatorConstant.energyForCure);
							calculateWeight();
						}
					}
				}
				else{
					throw new InsufficientEnergyException("Energia insufficiente per curare");
				}
			}
		}
	
	}
	public void cureEnergy(Curable toCure, int quantity) throws InsufficientEnergyException, DeadException {
		if(toCure instanceof Insertable){
			if(((Insertable) toCure).getPosition().isNear(position)){
				if(this.energy.getEnergy()>SimulatorConstant.energyForCure){
					if(energyChargher>=quantity){
						if(toCure instanceof Curable){
							toCure.receiveEnergy(quantity);
							energyChargher-=quantity;
							this.receiveDamage(SimulatorConstant.energyForCure);
							calculateWeight();
						}
					}
				}
				else{
					throw new InsufficientEnergyException("Energia insufficiente per curare");
				}
				
				
			}
		}
	}
	/**
	 * Ripristina la quantità di scudi da poter riparare
	 */
	public void restoreShieldChargher(int toReceive){
		if(toReceive<0) throw new IllegalArgumentException("Quantitï¿½ negativa");
		if(toReceive+shieldChargher>=SimulatorConstant.maxShieldRecharger)
			shieldChargher=SimulatorConstant.maxShieldRecharger;
		else
			shieldChargher+=toReceive;
	}
	public void restoreEnergyChargher(int toReceive){
		if(toReceive<0) throw new IllegalArgumentException("Quantitï¿½ negativa");
		if(toReceive+energyChargher>=SimulatorConstant.maxEnergyRecharger)
			energyChargher=SimulatorConstant.maxEnergyRecharger;
		else
			energyChargher+=toReceive;
	}
	/**
	 * Sposta un oggetto Pesabile avanti
	 * @param obstacle Oggetto da spostare
	 * @throws InsufficientEnergyException lanciata se un elementonon ha abbastanza energia
	 * @throws DeadException lanciata se un elemento termina l'energia
	 */
	public void moveElement(Pesabile obstacle) throws InsufficientEnergyException, DeadException{
		if(energy.getEnergy()>SimulatorConstant.energyForShift){
			if(obstacle.getWeight()<=this.weight){
				if(obstacle instanceof Element){
					Element o=(Element) obstacle;
					Position p=o.getPosition();
					if(o.getPosition().isNear(position)){
						if(this.position.isDown(o.getPosition())){
							p.setY(p.getY()-1);
						}
						else if(this.position.isUp(o.getPosition())){
							p.setY(p.getY()+1);
						}
						else if(this.position.isLeft(o.getPosition())){
							p.setX(p.getX()-1);
						}
						else if(this.position.isRight(o.getPosition())){
							p.setX(p.getX()+1);
						}
						o.setPosition(p);
						this.receiveDamage(SimulatorConstant.energyForShift);
					}
				}
			}
		}
		else{
			throw new InsufficientEnergyException("Energia insufficiente per spostare l'elemento");
		}
	}
	public int getEnergy() {
		return energy.getEnergy();
	}
	public int getShieldChargher() {
		return shieldChargher;
	}
	public int getEnergyChargher() {
		return energyChargher;
	}
	/*
	 * Sovrascrittura di toString
	 */
	public String toString(){
		return super.toString()+ "[Peso: "+weight+
				" Quantità Di Scudi: "+shieldChargher+
				" Quantità Di Energia "+ energyChargher+"]";
	}
	/*
	 * Sovrascrittura di equals
	 */
	public boolean equals(Object anObject){
		if(!super.equals(anObject))return false;
		Worker w=(Worker) anObject;
		return w.weight==this.weight &&
				w.shieldChargher==this.shieldChargher &&
				w.energyChargher== this.energyChargher;
	}
	/*
	 * Sovrascrittura di clone
	 */
	public Worker clone(){
		Worker w=(Worker) super.clone();
		w.weight=this.weight;
		w.energyChargher=this.energyChargher;
		w.shieldChargher=this.shieldChargher;
		return w;
	}
	public void draw(Graphics2D g, int width, int height) {
		int l_x=width/SimulatorConstant.maxX;;
		int l_y=height/SimulatorConstant.maxY;;       
		int x=(position.getX()*l_x);           
		int y=(Math.abs(position.getY()-9)*l_y);
		g.setColor(color);
		Ellipse2D.Double e=new Ellipse2D.Double(x,y,l_x,l_y);
		g.fill(e);
		g.draw(e);
		g.setColor(Color.black);
		g.drawString(getClass().getSimpleName(),x,y+(l_y/2));
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public void setDefaultColor() {
		this.color=SimulatorConstant.defaultColorForWorker;
	}
	public String read(){
		return super.read()+
				"Quantità di scudi: "+shieldChargher+"\n\t"+
				"Quantità di energia: "+energyChargher+"\n\t"+
				"Peso: "+weight+"\n";
	}
}
