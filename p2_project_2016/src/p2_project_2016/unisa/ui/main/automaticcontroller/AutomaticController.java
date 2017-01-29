package p2_project_2016.unisa.ui.main.automaticcontroller;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.interfaces.Curable;
import p2_project_2016.unisa.interfaces.Damageable;
import p2_project_2016.unisa.interfaces.Drawable;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.interfaces.ShieldRestorable;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.benchsupply.BenchSupply;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.gamefield.Field;
import p2_project_2016.unisa.simulatorelement.robot.Robot;
import p2_project_2016.unisa.simulatorelement.robot.soldier.Soldier;
import p2_project_2016.unisa.simulatorelement.robot.worker.Worker;
import p2_project_2016.unisa.ui.main.Main;
/**
 * Esegue una mossa in automatico
 * @author tullio
 */
public class AutomaticController {
	private Field field;
	private Robot robot;
	/**
	 * Istanzia un controllore automatico che esegue un'operazione su un robot
	 * @param field Field contenente il robot
	 * @param robot Robot da controllare
 	 * @throws DeadException Lanciata alla morte di un elemento
	 */
	public AutomaticController(Field field, Robot robot) throws DeadException {
		this.field=field;
		this.robot=robot;
		if(robot instanceof Worker){
			mossaWorker();
		}
		if(robot instanceof Soldier){
			mossaSoldier();
		}
	}
	/**
	 * Sceglie una mosse per un Soldier
	 */
	private void mossaSoldier() throws DeadException{
		Soldier s=(Soldier)robot;
		Random random=new Random();
		Damageable toAttack=null;
		if(field.getElements().size()>1){
			ArrayList<Damageable> friends = new ArrayList<Damageable>();
			for(Insertable i:field.getElements())
				if(i instanceof Damageable && !(i.getPosition().equals(s.getPosition())))
					friends.add((Damageable) i);
			if(friends.size()>0){
				toAttack=friends.get(random.nextInt(friends.size()));
				try{
					Main.changeColorTo((Drawable) toAttack, SimulatorConstant.defaultColorForDamage);
					s.fire(toAttack,s.getWeapon());
					JOptionPane.showMessageDialog(null, s.getClass().getSimpleName()+" in posizione "+s.getPosition()+" ha attaccato "+ ((Insertable) toAttack).getPosition());
				}
				catch(InsufficientEnergyException e){
					ArrayList<Insertable> nears=searchNearElements(s);
					boolean stop=false;
					for(Insertable i:nears){
						if(i instanceof BenchSupply || i instanceof Worker)
							stop=true;
					}
					if(!stop){
						for(Insertable i:field.getElements()){
							if(i instanceof BenchSupply || i instanceof Worker){
								try {
									move(s,i);
									JOptionPane.showMessageDialog(null, s.getClass().getSimpleName()+" si sposta verso "+i.getPosition());
									break;
								} catch (InsufficientEnergyException e1) {
									JOptionPane.showMessageDialog(null, s.getClass().getSimpleName()+" in "+ s.getPosition()+ " non ha energia per muoversi");
								}
							}
						}
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Forever Alone");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Forever Alone");
		}
	}		
	/**
	 * Sceglie una mossa per un worker
	 */
	private void mossaWorker() throws DeadException{
		Worker w=(Worker)robot;
		try {
			ArrayList<Insertable> nears=searchNearElements(w);
			boolean nonCurable=false;
			for(Insertable i: nears){
				if(i instanceof ShieldRestorable){
					if(((ShieldRestorable) i).getShield()<SimulatorConstant.baseShield){
						Main.changeColorTo((Drawable) i, SimulatorConstant.defaultColorForCure);
						w.cureShield((ShieldRestorable) i, SimulatorConstant.baseShield-((ShieldRestorable) i).getShield());
						JOptionPane.showMessageDialog(null, w.getClass().getSimpleName()+" cura lo scudo "+i.getPosition());
						nonCurable=true;
						break;
					}
				}
				if(i instanceof Curable){
					if(((Curable) i).getEnergy()<SimulatorConstant.baseEnergy){
						Main.changeColorTo((Drawable) i, SimulatorConstant.defaultColorForCure);
						w.cureEnergy((Curable) i, SimulatorConstant.baseEnergy-((Curable) i).getEnergy());
						JOptionPane.showMessageDialog(null, w.getClass().getSimpleName()+" cura "+i.getPosition());
						nonCurable=true;
						break;
					}
				}
			}
			if(!nonCurable){
				Random random=new Random();
				ArrayList<Insertable> temp=new ArrayList<Insertable>();
				for(Insertable i:field.getElements()){
					if((i instanceof Curable || i instanceof ShieldRestorable) 
							&& !(i.getPosition().equals(w.getPosition()))){
						temp.add(i);
					}
				}
				if(temp.size()>0){
					Insertable element=temp.get(random.nextInt(temp.size()));
					move(w,element);
					nonCurable=true;
					JOptionPane.showMessageDialog(null, w.getClass().getSimpleName()+" si sposta verso "+element.getPosition());
				}
			}
			if(!nonCurable){
				for(Insertable insertable:field.getElements()){
					if(insertable instanceof BenchSupply){
						move(w,insertable);
						JOptionPane.showMessageDialog(null, w.getClass().getSimpleName()+" si sposta versooooo "+insertable.getPosition());
						break;
					}
				}
			}
		} catch (InsufficientEnergyException e) {
			JOptionPane.showMessageDialog(null, "Energia insufficiente");
		}
		catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	/**
	 * Cerca gli elementi intorno al robot r
	 * @param r Robot su cui effettuare la ricerca
	 * @return ArryList<Insertable> elementi intorno al robot r
	 */
	private ArrayList<Insertable> searchNearElements(Robot r){
		ArrayList<Insertable> newList=new ArrayList<Insertable>();
		int x=r.getPosition().getX();
		int y=r.getPosition().getY();
		for(int i=x-1;i<=x+1;i++){
			for(int j=y-1;j<=y+1;j++){
				if(i>=0 && j>=0 && i<SimulatorConstant.maxX && j<SimulatorConstant.maxY){
					Insertable temp=field.getElement(i, j);
					if(temp!=null && !(temp.getPosition().equals(r.getPosition()))){
						newList.add(temp);
					}
				}
			}
		} 
		return newList;
	}
	/**
	 * Fa avvicinare il robot s a un oggetto inseribile i
	 * @param s Il robot da muovere
	 * @param i L'obbiettivo da raggiungere
	 * @throws InsufficientEnergyException quando il robot non ha energia sufficiente per muoversi
	 * @throws DeadException quando il robot muore
	 */
	private void move(Robot s, Insertable i) throws InsufficientEnergyException, DeadException {
		try{
			if(s.getPosition().getX()>i.getPosition().getX()){
				Position p=robot.getPosition();
				if(!field.isOccuped(p.getX()-1,p.getY())){
					robot.moveLeft();
					field.deleteElement(p);
					field.occupePosition(robot);
				}
				else
					throw new IllegalArgumentException("Posizione già occupata");
			}
			else if(s.getPosition().getX()<i.getPosition().getX()){
				Position p=robot.getPosition();
				if(!field.isOccuped(p.getX()+1,p.getY())){
					robot.moveRight();
					field.deleteElement(p);
					field.occupePosition(robot);
				}
				else
					throw new IllegalArgumentException("Posizione già occupata");
			}
			else if(s.getPosition().getY()>i.getPosition().getY()){
				Position p=robot.getPosition();
				if(!field.isOccuped(p.getX(),p.getY()-1)){
					robot.moveDown();
					field.deleteElement(p);
					field.occupePosition(robot);
				}
				else
					throw new IllegalArgumentException("Posizione già occupata");			
			}
			else if(s.getPosition().getY()<i.getPosition().getY()){
				Position p=robot.getPosition();
				if(!field.isOccuped(p.getX(),p.getY()+1)){
					robot.moveUp();
					field.deleteElement(p);
					field.occupePosition(robot);
				}
				else
					throw new IllegalArgumentException("Posizione già occupata");
			}
		}
		catch(IllegalArgumentException e1){
			JOptionPane.showMessageDialog(null, e1);
		}
		
	}

}
	