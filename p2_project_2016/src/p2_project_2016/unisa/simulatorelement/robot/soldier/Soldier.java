package p2_project_2016.unisa.simulatorelement.robot.soldier;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import p2_project_2016.unisa.exception.CriticalStatusException;
import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.interfaces.CanAttack;
import p2_project_2016.unisa.interfaces.Damageable;
import p2_project_2016.unisa.interfaces.Resistant;
import p2_project_2016.unisa.interfaces.ShieldRestorable;
import p2_project_2016.unisa.interfaces.Weapon;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.robot.Robot;
/**
 * Soldato, estensione di Robot.
 * Può attaccare gli oggetti Damageable e utilizza uno scudo per 
 * ridurre eventuali danni subiti
 * @author tullio
 */
public class Soldier extends Robot implements CanAttack,ShieldRestorable {
	/**
	 *  Soldier Serializable ID
	 */
	private static final long serialVersionUID = -2182385426012378990L;
	/**
	 * Resistenza del soldato
	 */
	private int resistance;
	/**
	 * Scudo del soldato
	 */
	private Shield shield;
	/**
	 * Arma utilizzata dal soldato
	 */
	private Weapon weapon;
	/**
	 * Colore del soldato
	 */
	private Color color;
	/**
	 * Costruttore standard di un soldato
	 * @param point Posizione iniziale
	 * @param weapon Weapon arma da assegnare al soldier
	 */
	public Soldier(Position point,Weapon weapon) {
		super(point);
		shield=new Shield();
		resistance=(SimulatorConstant.baseEnergy*SimulatorConstant.resistancePercentage)/100;
		this.weapon=weapon;
		this.color=SimulatorConstant.defaultColorForSoldier;
	}
	/**
	 * Costruttore di un soldato in un punto con un'ergia iniziale stabilita
	 * @param point Posizione iniziale
	 * @param energy energia iniziale
	 * @param weapon Arma del soldato
	 */
	public Soldier(Position point, int energy, Weapon weapon) {
		super(point,energy);
		resistance=(energy*SimulatorConstant.resistancePercentage)/100;
		shield=new Shield();
		this.weapon=weapon;
		this.color=SimulatorConstant.defaultColorForSoldier;
	}
	/**
	 * In un soldato, il danno subito è0 ridotto dallo scudo
	 * Ogni volta che l'energia si riduce va ricalcolata la resistenza
	 * @throws DeadException lanciata se il danno consuma tutta l'energia residua
	 */
	public void receiveDamage(int damage) throws DeadException,CriticalStatusException{
		super.receiveDamage(shield.reduceDamage(damage));
		resistance=(energy.getEnergy()*SimulatorConstant.resistancePercentage)/100;
	}
	/**
	 * Ogni volta che si riceve energi bisogna ricalcoare la resistenza
	 */
	public void receiveEnergy(int energy) throws DeadException {
		super.receiveEnergy(energy);
		resistance=(this.energy.getEnergy()*SimulatorConstant.resistancePercentage)/100;
	}
	/**
	 * Restituisce il livello di resistenza di un robot
	 * @return resistance int Livello di resistenza
	 */
	public int getResistance(){
		return resistance;
	}
	/**
	 * Danneggia un altro elemento
	 * @throws InsufficientEnergyException lanciata se l'energia non è sufficiente per attaccare
	 * @throws DeadException lanciata se l'elemento consuma tutta l'energia
	 */
	public void fire(Damageable toAttack,Weapon weapon) throws InsufficientEnergyException, DeadException,CriticalStatusException{
		if(energy.getEnergy()>weapon.energyCost()){
			if(toAttack instanceof Resistant){
				Resistant r=(Resistant)toAttack;
				if(r.getResistance()<=this.resistance){
					toAttack.receiveDamage(weapon.damage());
				}
			}
			else
					toAttack.receiveDamage(weapon.damage());
			super.receiveDamage(weapon.energyCost());
			resistance=(energy.getEnergy()*SimulatorConstant.resistancePercentage)/100;	
		}
		else{
			throw new InsufficientEnergyException("Energia insufficiente per attaccare");
		}
	}
	public void receiveShield(int shield){
		this.shield.rechargeShield(shield);
	}
	public int getEnergy() {
		return energy.getEnergy();	
	}
	public int getShield(){
		return shield.getShield();
	}
	public Weapon getWeapon(){
		return weapon;
	}
	/*
	 * Sovrascrittura di toString
	 */
	public String toString(){
		return super.toString()+weapon+" "+shield+" Resistance: "+resistance+"]";
	}
	/*
	 * Sovrascrittura di equals
	 */
	public boolean equals(Object anObject){
		if(!super.equals(anObject)) return false;
		Soldier s=(Soldier)anObject;
		return this.resistance==s.resistance && this.position.equals(s.position) 
				&& this.energy.equals(s.energy) && this.weapon.equals(s.weapon)
				&& this.shield.equals(s.shield);
	}
	/*
	 * Sovrascrittura di clone
	 */
	public Soldier clone(){
		Soldier s=(Soldier) super.clone();
		s.weapon=this.weapon;
		s.resistance=this.resistance;
		s.shield=this.shield.clone();
		return s;
	}
	/**
	 * 
	 * @author tulli
	 * Classe per gestire lo scudo
	 */
	class Shield implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -6662837526669910724L;
		/**
		 * Quantità di scudo disponibile
		 */
		private int shield;
		/**
		 * Costruttore di shield
		 */
		public Shield(){
			shield=SimulatorConstant.baseShield;
		}
		
		/**
		 * Utilizzata per ridurre lo scudo ad ogni attacco
		 */
		private void reduceShield(){
			if(shield>0)
				shield--;
		}
		/**
		 * Ricarica lo scudo
		 * @param shield Quantità di scudo da aggiungere
		 */
		public void rechargeShield(int shield){
			if((this.shield+shield)<=SimulatorConstant.baseShield)
				this.shield+=shield;
			else
				this.shield=SimulatorConstant.baseShield;
		}
		/**
		 * Riduce il danno di un robot
		 * @param damage Danno da ridurre
		 * @return Quantitï¿½ di danno ridotto
		 */
		public int reduceDamage(int damage){
			int newDamage=damage-(damage*(shield*10))/100;
			reduceShield();
			return newDamage;
		}
		/**
		 * Restituisce la quantitï¿½ di scudo
		 */
		public int getShield(){
			return shield;
		}
		
		/*
		 * Sovrascrittura di toString
		 */
		public String toString(){
			return "Shield[Quantità= "+shield+"]";
		}
		/*
		 * Sovrascrittura di equals()
		 */
		public boolean equals(Object anObject){
			if(anObject==null)return false;
			if(!getClass().equals(anObject.getClass()))return false;
			Shield s=(Shield)anObject;
			return this.shield==s.shield;
		}
		/*
		 * Sovrascrittura di clone
		 */
		public Shield clone(){
			Shield s=new Shield();
			for(int i=this.shield;i<SimulatorConstant.baseShield;i++)
				s.reduceShield();
			return s;
		}
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
		this.color=SimulatorConstant.defaultColorForSoldier;	
	}
	public String read(){
		return super.read()+
				"Scudo: "+shield.getShield()+"\n\t"+
				"Resistenza: "+resistance+"\n\t"+
				"Arma: "+weapon.getClass().getSimpleName()+"\n";
	}



}
