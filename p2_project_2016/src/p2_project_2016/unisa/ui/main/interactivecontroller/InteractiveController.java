package p2_project_2016.unisa.ui.main.interactivecontroller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import p2_project_2016.unisa.exception.CriticalStatusException;
import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.exception.InsufficientEnergyException;
import p2_project_2016.unisa.interfaces.Curable;
import p2_project_2016.unisa.interfaces.Damageable;
import p2_project_2016.unisa.interfaces.Drawable;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.interfaces.Pesabile;
import p2_project_2016.unisa.interfaces.ShieldRestorable;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.Element;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.gamefield.Field;
import p2_project_2016.unisa.simulatorelement.robot.Robot;
import p2_project_2016.unisa.simulatorelement.robot.soldier.Soldier;
import p2_project_2016.unisa.simulatorelement.robot.worker.Worker;
import p2_project_2016.unisa.ui.main.Main;
/**
 * Consente all'utente di scegliere la mossa da eseguire
 * @author tullio
 */
public class InteractiveController extends JFrame {
	/**
	 * InteractiveController Frame ID
	 */
	private static final long serialVersionUID = 4548392817707578811L;
	private Field field;
	private Robot robot;
	private Main frame;
	/**
	 * Istanzia un controllore interattivo contenente le scelte per il robot
	 * @param frame Main su cui viene visualizzato il robot
	 * @param field Field campo contenente il robot
	 * @param robot Robot da controllare
	 */
	public InteractiveController(Main frame,Field field, Robot robot){
		this.field=field;
		this.robot=robot;
		this.frame=frame;
		this.setTitle("Controllore interattivo");
		this.setSize(500,300);
		if(robot instanceof Worker){
			this.add(createFrameForWorker());
		}
		else if (robot instanceof Soldier){
			this.add(createFrameForSoldier());
		}
		this.setVisible(true);
	}	
	/**
	 * Genera il frame per consentire all'utente di scegliere la mossa per un Soldier
	 * @return JPanel contenente le varie mossse che possono essere eseguite dal soldato
	 */
	private JPanel createFrameForSoldier() {
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(6,1));
		JLabel position=new JLabel(robot.getClass().getSimpleName()+": "+robot.getPosition());
		JButton up=new JButton("Sali");
		JButton down=new JButton("Scendi");
		JButton right=new JButton("Destra");
		JButton left=new JButton("Sinistra");
		JButton attacca=new JButton("Attacca");
		panel.add(position);
		panel.add(down);
		panel.add(up);
		panel.add(left);
		panel.add(right);
		panel.add(attacca);
		class UpListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX(),p.getY()+1)){
						robot.moveUp();
						field.deleteElement(p);
						field.occupePosition(robot);
					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e);
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);			
				}
			}
		}
		class DownListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX(),p.getY()-1)){
						robot.moveDown();
						field.deleteElement(p);
						field.occupePosition(robot);
					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, "Operazione non valida");
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);
				}
			}
		}
		class LeftListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX()-1,p.getY())){
						robot.moveLeft();
						field.deleteElement(p);
						field.occupePosition(robot);
					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e);
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);	
				}
			}
		}
		class RightListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX()+1,p.getY())){
						robot.moveRight();
						field.deleteElement(p);
						field.occupePosition(robot);

					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e);
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);		
				}
			}
		}
		class AttackListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0){
				try{
					String input=JOptionPane.showInputDialog("Inserisci la x dell'elemento da attaccare");
					int x=Integer.parseInt(input);
					if(x<0)
						throw new IllegalArgumentException("Valore negativo");
					input=JOptionPane.showInputDialog("Inserisci la y dell'elemento da attaccare");
					int y= Integer.parseInt(input);
					if(y<0)
						throw new IllegalArgumentException("Valore negativo");
					Element e=field.getElement(x, y);
					if(e instanceof Damageable){
						Damageable toAttack=(Damageable) e;
						Soldier s=(Soldier) robot;
						
						try {
							Main.changeColorTo((Drawable) toAttack, SimulatorConstant.defaultColorForDamage);
							s.fire(toAttack,s.getWeapon());
							frame.reDraw();
							class TimerListener implements ActionListener{
								private int i=0;
								public TimerListener(){
									this.i=0;
								}
								public void actionPerformed(ActionEvent arg0) {
									i++;
									if(i>1)
										Main.setDefaultColor((Drawable) toAttack);
									frame.reDraw();
								}
							}
							ActionListener timerListener= new TimerListener();
							Timer timer=new Timer(500,timerListener);
							timer.start();
						} catch (InsufficientEnergyException e1) {
							JOptionPane.showMessageDialog(null, e1);
						} catch(CriticalStatusException ne){
							JOptionPane.showMessageDialog(null, ne);
						}
						catch(DeadException dE){
							new DeadException(field);
						}
					}
				}
				catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, "Operazione non valida");
				}
				catch(ArrayIndexOutOfBoundsException indexExc){
					JOptionPane.showMessageDialog(null, "Valori errati");
				}
				setVisible(false);
			}
		}
		ActionListener atL=new AttackListener();
		attacca.addActionListener(atL);
		
		ActionListener riL=new RightListener();
		right.addActionListener(riL);
		
		ActionListener leL=new LeftListener();
		left.addActionListener(leL);
		
		ActionListener doL=new DownListener();
		down.addActionListener(doL);
		
		ActionListener upL=new UpListener();
		up.addActionListener(upL);
		
		return panel;
	}
	/**
	 * Genera il frame per consentire all'utente di scegliere la mossa per un Worker
	 * @return JPanel contenente le varie mossse che possono essere eseguite dal lavoratore
	 */
	public JPanel createFrameForWorker(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(8,1));
		JLabel position=new JLabel(robot.getClass().getSimpleName()+": "+robot.getPosition());
		JButton up=new JButton("Sali");
		JButton down=new JButton("Scendi");
		JButton right=new JButton("Destra");
		JButton left=new JButton("Sinistra");
		JButton cureE=new JButton("Cura energia");
		JButton cureS=new JButton("Cura scudo");
		JButton moveObstacle=new JButton("Muovi ostacolo");
		panel.add(position);
		panel.add(down);
		panel.add(up);
		panel.add(left);
		panel.add(right);
		panel.add(cureE);
		panel.add(cureS);
		panel.add(moveObstacle);
		class UpListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX(),p.getY()+1)){
						robot.moveUp();
						field.deleteElement(p);
						field.occupePosition(robot);
					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e);
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);
				}
			}
		}
		class DownListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX(),p.getY()-1)){
						robot.moveDown();
						field.deleteElement(p);
						field.occupePosition(robot);
					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, "Operazione non valida");
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);
				}
			}
		}
		class LeftListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX()-1,p.getY())){
						robot.moveLeft();
						field.deleteElement(p);
						field.occupePosition(robot);
					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null,e);
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);
				}
			}
		}
		class RightListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				try{
					Position p=robot.getPosition();
					if(!field.isOccuped(p.getX()+1,p.getY())){
						robot.moveRight();
						field.deleteElement(p);
						field.occupePosition(robot);
					}
					else
						throw new IllegalArgumentException("Posizione già occupata");
					setVisible(false);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, "Operazione non valida");
				} catch (InsufficientEnergyException e) {
					JOptionPane.showMessageDialog(null, e);
				}
				catch(DeadException e){
					new DeadException(field);
				}
			}
		}
		class CureEListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0){
				try{
					String input=JOptionPane.showInputDialog("Inserisci la x dell'elemento da curare");
					int x=Integer.parseInt(input);
					if(x<0)
						throw new IllegalArgumentException("");
					input=JOptionPane.showInputDialog("Inserisci la y dell'elemento da curare");
					int y= Integer.parseInt(input);
					if(y<0)
						throw new IllegalArgumentException("");
					Element e=field.getElement(x, y);
					if(e instanceof Curable){
						input=JOptionPane.showInputDialog("Inserisci la quantità di energia da donare");
						int energyToDone=Integer.parseInt(input);
						Worker w=(Worker) robot;
						try {
							Main.changeColorTo(e, SimulatorConstant.defaultColorForCure);
							w.cureEnergy((Curable) e, energyToDone);
							frame.reDraw();
							class TimerListener implements ActionListener{
								private int i=0;
								public TimerListener(){
									this.i=0;
								}
								public void actionPerformed(ActionEvent arg0) {
									i++;
									if(i>1)
										Main.setDefaultColor((Drawable) e);
									frame.reDraw();
								}
							}
							ActionListener timerListener= new TimerListener();
							Timer timer=new Timer(500,timerListener);
							timer.start();
						} catch (InsufficientEnergyException e1) {
							JOptionPane.showMessageDialog(null,e1);
						} catch (CriticalStatusException ne) {
							
						}
						catch(DeadException e2){
							new DeadException(field);
						}
					}
					setVisible(false);
				}
				catch(IllegalArgumentException iArgExc){
					JOptionPane.showMessageDialog(null, "Dati non validi");
				}
			}
		}
		class CureSListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0){
				try{
					String input=JOptionPane.showInputDialog("Inserisci la x dell'elemento da curare");
					int x=Integer.parseInt(input);
					if(x<0)
						throw new IllegalArgumentException("");
					input=JOptionPane.showInputDialog("Inserisci la y dell'elemento da curare");
					int y= Integer.parseInt(input);
					if(y<0)
						throw new IllegalArgumentException("");
					Element e=field.getElement(x, y);
					if(e instanceof Curable){
						input=JOptionPane.showInputDialog("Inserisci la quantità di scudo da donare");
						int shieldToDone=Integer.parseInt(input);
						Worker w=(Worker) robot;
						try {
							Main.changeColorTo(e, SimulatorConstant.defaultColorForCure);
							w.cureShield((ShieldRestorable) e, shieldToDone);
							frame.reDraw();
							class TimerListener implements ActionListener{
								private int i=0;
								public TimerListener(){
									this.i=0;
								}
								public void actionPerformed(ActionEvent arg0) {
									i++;
									if(i>1)
										Main.setDefaultColor((Drawable) e);
									frame.reDraw();
								}
							}
							ActionListener timerListener= new TimerListener();
							Timer timer=new Timer(500,timerListener);
							timer.start();
						} catch (InsufficientEnergyException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
						catch(DeadException e2){
							new DeadException(field);
						}
					}
					setVisible(false);
				}
				catch(IllegalArgumentException iArgExc){
					JOptionPane.showMessageDialog(null, "Dati non validi");
				}
			}
		}
		class MoveListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0){
				try{
					String input=JOptionPane.showInputDialog("Inserisci la x dell'ostacolo da spostare");
					int x=Integer.parseInt(input);
					if(x<0)
						throw new IllegalArgumentException("");
					input=JOptionPane.showInputDialog("Inserisci la y dell'ostacolo da spostare");
					int y= Integer.parseInt(input);
					if(y<0)
						throw new IllegalArgumentException("");
					Element e=field.getElement(x, y);
					if(e instanceof Pesabile){
						Position positionE=e.getPosition();
						Worker w=(Worker) robot;
						try {
							w.moveElement((Pesabile)e);
							field.deleteElement(positionE);
							field.occupePosition((Insertable)e);
							setVisible(false);
						} catch (InsufficientEnergyException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
						catch(DeadException e2){
							new DeadException(field);
						}
					}
				}
				catch(IllegalArgumentException iArgExc){
					JOptionPane.showMessageDialog(null, "Dati non validi");
				}
			}
		}
		ActionListener riL=new RightListener();
		right.addActionListener(riL);
		
		ActionListener leL=new LeftListener();
		left.addActionListener(leL);
		
		ActionListener doL=new DownListener();
		down.addActionListener(doL);
		
		ActionListener upL=new UpListener();
		up.addActionListener(upL);
		
		ActionListener cureEL=new CureEListener();
		cureE.addActionListener(cureEL);
		
		ActionListener moveL=new MoveListener();
		moveObstacle.addActionListener(moveL);
		
		ActionListener cureSL=new CureSListener();
		cureS.addActionListener(cureSL);
		return panel;
	}
}
