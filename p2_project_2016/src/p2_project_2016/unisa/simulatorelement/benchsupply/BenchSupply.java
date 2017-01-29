package p2_project_2016.unisa.simulatorelement.benchsupply;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.interfaces.CanCureEnergy;
import p2_project_2016.unisa.interfaces.CanCureShield;
import p2_project_2016.unisa.interfaces.Curable;
import p2_project_2016.unisa.interfaces.ShieldRestorable;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.Element;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
/**
 * Banco rifornimento, ogni turno scandisce i vicini e gli rigenera l'energia
 * ed eventuali scudi
 * @author tullio
 */
public class BenchSupply extends Element {
	/**
	 * BenchSupply Serializable ID
	 */
	private static final long serialVersionUID = 4795591079343507730L;
	/**
	 * Quantità di punti scudi disponibili
	 */
	private int quantityOfShield;
	/**
	 * Quantità di punti  energia disponibili
	 */
	private int quantityOfEnergy;
	/**
	 * Instanzia un nuovo banco rifornimento in un punto con punti scudo e punti energia base
	 * @param point Punto in cui inserire il banco rifornimento
	 */
	public BenchSupply(Position point) {
		super(point);
		quantityOfEnergy=SimulatorConstant.energyForBench;
		quantityOfShield=SimulatorConstant.shieldForBench;
	}
	/**
	 * Ricarica un elemento
	 * @param element Elemento da ricaricare
	 * @throws IllegalArgumentException Lanciata
	 * @throws DeadException Non viene mai lanciata
	 */
	public void chargeElement(Element element) throws IllegalArgumentException, DeadException{	
		if(element instanceof Curable){
			int energyToGive=SimulatorConstant.baseEnergy-((Curable) element).getEnergy();
			if(quantityOfEnergy>=energyToGive){
				((Curable) element).receiveEnergy(energyToGive);
				
				quantityOfEnergy-=energyToGive;
			}
		}
		if(element instanceof ShieldRestorable){
			int shieldToGive=SimulatorConstant.baseShield-((ShieldRestorable) element).getShield();
			if(quantityOfShield>=shieldToGive){
				((ShieldRestorable) element).receiveShield(shieldToGive);
				quantityOfShield-=shieldToGive;
			}
		}
		if(element instanceof CanCureShield){
			int shieldToGive=SimulatorConstant.maxShieldRecharger-((CanCureShield)element).getShieldChargher();
			if(quantityOfShield>=shieldToGive){
				((CanCureShield) element).restoreShieldChargher(shieldToGive);
				quantityOfShield-=shieldToGive;
			}
		}
		if(element instanceof CanCureEnergy){
			int energyToGive=SimulatorConstant.maxEnergyRecharger-((CanCureEnergy)element).getEnergyChargher();
			if(quantityOfShield>=energyToGive){
				((CanCureEnergy) element).restoreEnergyChargher(energyToGive);
				quantityOfEnergy-=energyToGive;
			}
		}
	}
	/**
	 * Punti scudo disponibili per il banco rifornimento
	 * @return int quantità di scudi disponibili
	 */
	public int getQuantityOfShield(){
		return quantityOfShield;
	}
	/**
	 * Punti energia disponibili per il banco rifornimento
	 * @return int quantità di energia disponibile
	 */
	public int getQuantityOfEnergy(){
		return quantityOfEnergy;
	}
	/*
	 * Sovrascrittura di toString
	 */
	public String toString(){
		return super.toString()+ "[Quantità di scudi: "+quantityOfShield+" Quantità di energia: "+quantityOfEnergy+"]";
	}
	/*
	 * Sovrascrittura di equals
	 */
	public boolean equals(Object anObject){
		if(!super.equals(anObject)) return false;
		BenchSupply b=(BenchSupply) anObject;
		return b.quantityOfEnergy==this.quantityOfEnergy && b.quantityOfShield==this.quantityOfShield;
	}
	/*
	 * Sovrascrittura di clone
	 */
	public BenchSupply clone(){
		BenchSupply b=(BenchSupply) super.clone();
		b.quantityOfEnergy=this.quantityOfEnergy;
		b.quantityOfShield=this.quantityOfShield;
		return b;
	}
	/**
	 * Metodo utilizzato per disegnare un BenchSupply
	 * @param g Graphics2D grafico su cui disegnare
	 * @param width int larghezza della finestra
	 * @param height int altezza della finestra
	 */
	public void draw(Graphics2D g,int width,int height){
		int l_x=width/SimulatorConstant.maxX;;
		int l_y=height/SimulatorConstant.maxY;;       
		int x=(position.getX()*l_x)+(l_x/4);           
		int y=(Math.abs(position.getY()-9)*l_y)+(l_y/3);  
		int latoX=5*l_x/10;
		int latoY=5*l_y/10;
		Rectangle faccia=new Rectangle(x,y,latoX,latoY);
		if(quantityOfShield>0 || quantityOfEnergy>0)
			g.setColor(Color.green);
		else
			g.setColor(Color.red);
		g.fill(faccia);
		g.setColor(Color.BLACK);
		g.drawString(getClass().getSimpleName(), x, y);
	}
	public void setColor(Color color) {
	}
	public void setDefaultColor() {
	}
	public String read(){
		return super.read()+"Quantità di scudi: "+quantityOfShield+"\n\t"
				+"Quantità di energia: "+quantityOfEnergy+"\n";
	}
}
