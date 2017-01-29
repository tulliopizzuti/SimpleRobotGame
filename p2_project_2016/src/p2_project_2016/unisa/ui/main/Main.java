package p2_project_2016.unisa.ui.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import p2_project_2016.unisa.exception.DeadException;
import p2_project_2016.unisa.interfaces.Drawable;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.benchsupply.BenchSupply;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
import p2_project_2016.unisa.simulatorelement.gamefield.Field;
import p2_project_2016.unisa.simulatorelement.obstacle.Obstacle;
import p2_project_2016.unisa.simulatorelement.robot.Robot;
import p2_project_2016.unisa.simulatorelement.robot.soldier.Soldier;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.FireGun;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.LaserGun;
import p2_project_2016.unisa.simulatorelement.robot.soldier.weapon.RocketLauncher;
import p2_project_2016.unisa.simulatorelement.robot.worker.Worker;
import p2_project_2016.unisa.ui.drawcomponent.DrawComponent;
import p2_project_2016.unisa.ui.main.automaticcontroller.AutomaticController;
import p2_project_2016.unisa.ui.main.interactivecontroller.InteractiveController;
import p2_project_2016.unisa.ui.main.visual.Visual;
/**
 * Frame principale del gioco
 * @author tullio
 */
public class Main extends JFrame {
	/**
	 * Main JFame ID
	 */
	private static final long serialVersionUID = -6477111630491582860L;
	/**
	 * Campo di gioco
	 */
	private Field field;
	/**
	 * Lista dei robot presenti nel campo
	 */
	private ArrayList<Robot> robot;
	/**
	 * Larghezza finestra
	 */
	private int width=(SimulatorConstant.maxX)*100;
	/**
	 * Altezza finestra
	 */
	private int height=(SimulatorConstant.maxY)*100;
	/**
	 * Componente di disegno
	 */
	private DrawComponent toDraw;
	/**
	 * Array contenente la lista degli elementi che devono effettuare una mossa
	 */
	private int[] scaletta;
	/**
	 * Contatore usato per scandire scaletta
	 */
	private int count;
	/**
	 * Istanzio una nuova finestra con un campo da gioco
	 * @param field Campo di gioco
	 */
	public Main(Field field){
		this.setTitle("RobotSimulator");
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.field=field;
		inizializzaTurno();
		toDraw=new DrawComponent(this.field);
		this.add(toDraw);
		createMenu();
		
		
	}
	/**
	 * Genera il menù di Main
	 */
	public void createMenu(){
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(file());
		menuBar.add(prossimaMossa());
		menuBar.add(visualizzatoreElementi());
	}
	/**
	 * Genera il menù file
	 * @return JMenu restituisce il menù file
	 */
	public JMenu file(){
		JMenu file=new JMenu("File");
		file.add(nuovaPartita());
		file.add(caricaPartita());
		file.add(salva());
		file.add(esci());
		return file;
	}
	/**
	 * Genera il menù nuova partita
	 * @return JMenu restituisce il menï¿½ nuova partita
	 */
	private JMenu nuovaPartita() {
		JMenu nuovaPartita=new JMenu("Nuova partita");
		nuovaPartita.add(generaCasualmente());
		nuovaPartita.addSeparator();
		nuovaPartita.add(default1());
		nuovaPartita.add(default2());
		return nuovaPartita;
	}
	/**
	 * Item del menù file.nuovapartita
	 * @return JMenuItem Genera una partita casuale
	 */
	private JMenuItem generaCasualmente() {
		JMenuItem casuale=new JMenuItem("Genera mappa");
		class CasualListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				field=new Field();
				Random random=new Random();
				int nWorker=random.nextInt(4)+1;
				int nSoldier=random.nextInt(8)+1;
				int nBench=random.nextInt(4)+1;
				int i=0;
				while(i<nWorker){
					int x=random.nextInt(SimulatorConstant.maxX);
					int y=random.nextInt(SimulatorConstant.maxY);
					if(!field.isOccuped(x, y)){
						field.insertElement(new Worker(new Position(x,y)));
						i++;
					}
				}
				i=0;
				while(i<nSoldier){
					int x=random.nextInt(SimulatorConstant.maxX);
					int y=random.nextInt(SimulatorConstant.maxY);
					if(!field.isOccuped(x, y)){
						field.insertElement(new Soldier(new Position(x,y),new FireGun()));
						i++;
					}
				}
				i=0;
				while(i<nBench){
					int x=random.nextInt(SimulatorConstant.maxX);
					int y=random.nextInt(SimulatorConstant.maxY);
					if(!field.isOccuped(x, y)){
						field.insertElement(new BenchSupply(new Position(x,y)));
						i++;
					}
				}
				inizializzaTurno();
				reDraw();
			}
		}
		ActionListener casL=new CasualListener();
		casuale.addActionListener(casL);
		return casuale;
	}
	/**
	 * Genera la partita di default1
	 * @return JMenuitem partita di default1
	 */
	private JMenuItem default1() {
		JMenuItem def=new JMenuItem("Default 1");
		class DefListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				field=new Field();
				field.insertElement(new Obstacle(new Position(5,0)));
				field.insertElement(new Obstacle(new Position(5,3)));
				field.insertElement(new Obstacle(new Position(5,6)));
				field.insertElement(new Obstacle(new Position(5,9)));
				
				field.insertElement(new Soldier(new Position(3,0),new LaserGun()));
				field.insertElement(new Soldier(new Position(3,3),new FireGun()));
				field.insertElement(new Soldier(new Position(3,6),new RocketLauncher()));
				field.insertElement(new Soldier(new Position(3,9),new LaserGun()));
				
				field.insertElement(new Worker(new Position(1,1)));
				field.insertElement(new Worker(new Position(1,5)));
				field.insertElement(new Worker(new Position(1,8)));
				
				field.insertElement(new BenchSupply(new Position(1,3)));
//--------------------------------------------------------------------------------------
				field.insertElement(new Soldier(new Position(7,0),new LaserGun()));
				field.insertElement(new Soldier(new Position(7,3),new FireGun()));
				field.insertElement(new Soldier(new Position(7,6),new RocketLauncher()));
				field.insertElement(new Soldier(new Position(7,9),new LaserGun()));
				
				field.insertElement(new Worker(new Position(9,1)));
				field.insertElement(new Worker(new Position(9,5)));
				field.insertElement(new Worker(new Position(9,8)));
				
				field.insertElement(new BenchSupply(new Position(9,3)));
				inizializzaTurno();
				reDraw();
			}
		}
		ActionListener listener=new DefListener();
		def.addActionListener(listener);
		return def;
	}
	/**
	 * Genera la partita di default2
	 * @return
	 */
	private JMenuItem default2() {
		JMenuItem def=new JMenuItem("Default 2");
		class DefListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				field=new Field();
				field.insertElement(new Obstacle(new Position(1,5)));
				field.insertElement(new Obstacle(new Position(4,5)));
				field.insertElement(new Obstacle(new Position(7,5)));
				field.insertElement(new Obstacle(new Position(10,5)));
				
				field.insertElement(new Soldier(new Position(1,3),new LaserGun()));
				field.insertElement(new Soldier(new Position(4,3),new FireGun()));
				field.insertElement(new Soldier(new Position(7,3),new RocketLauncher()));
				field.insertElement(new Soldier(new Position(10,3),new LaserGun()));
				
				field.insertElement(new Worker(new Position(2,1)));
				field.insertElement(new Worker(new Position(6,1)));
				field.insertElement(new Worker(new Position(9,1)));
				
				field.insertElement(new BenchSupply(new Position(4,1)));
//--------------------------------------------------------------------------------------
				field.insertElement(new Soldier(new Position(1,7),new LaserGun()));
				field.insertElement(new Soldier(new Position(4,7),new FireGun()));
				field.insertElement(new Soldier(new Position(7,7),new RocketLauncher()));
				field.insertElement(new Soldier(new Position(10,7),new LaserGun()));
				
				field.insertElement(new Worker(new Position(2,9)));
				field.insertElement(new Worker(new Position(6,9)));
				field.insertElement(new Worker(new Position(9,9)));
				
				field.insertElement(new BenchSupply(new Position(4,9)));
				inizializzaTurno();
				reDraw();	
			}
		}
		ActionListener listener=new DefListener();
		def.addActionListener(listener);
		return def;
	}
	/**
	 * Genera il menù per eseguire la prossima mossa della scaletta
	 * @return JMenu formato da controlloreinterattivo e controllore automatico
	 */
	public JMenu prossimaMossa(){
		JMenu nextM=new JMenu("Prossima mossa");
		nextM.add(controlloreInterattivo());
		nextM.add(controlloreAutomatico());
		return nextM;
	}
	/**
	 * Genera il menù utilizzatore per visualizzare lo stato degli elementi
	 * @return JMenu pulsante per visualizzare gli elementi
	 */
	public JMenu visualizzatoreElementi(){
		JMenu menu=new JMenu("Visualizza dettagli elemento");
		class VisualListener implements MouseListener{
			public void mouseClicked(MouseEvent arg0) {
				if(!field.isEmpty()){
					Visual vio=new Visual(field.getElements());
					vio.setLocation(getWidth(), 0);	
					class VisionControllerListener implements WindowListener{
						public void windowActivated(WindowEvent arg0) {
							vio.printAll();
						}
						public void windowClosed(WindowEvent arg0) {
						}
						public void windowClosing(WindowEvent arg0) {
						}
						public void windowDeactivated(WindowEvent arg0) {
							vio.printAll();
						}
						public void windowDeiconified(WindowEvent arg0) {
						}
						public void windowIconified(WindowEvent arg0) {
						}
						public void windowOpened(WindowEvent arg0) {
							vio.printAll();
						}
					}
					vio.setVisible(true);
					WindowListener vL=new VisionControllerListener();
					vio.addWindowListener(vL);
				}
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
		}
		MouseListener listener = new VisualListener();
		menu.addMouseListener(listener);
		return menu;
	}
	/**
	 * Restituisce il menù del controllore interattivo
	 * @return JMenuItem 
	 */
	public JMenuItem controlloreInterattivo(){
		JMenuItem cI=new JMenuItem("Scegli cosa fare");
		Main main=this;
		class Selezionato implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				riorganizzaTurni();
				if(!robot.isEmpty()){
					if(count==robot.size()){
						JOptionPane.showMessageDialog(null,"Cambio turno");
						count=0;
						scaletta=generaTurno(robot.size());
						ArrayList<BenchSupply> bench=new ArrayList<BenchSupply>();
						ArrayList<Insertable> temp=field.getElements();
						for(Insertable i:temp){
							if(i instanceof BenchSupply){
								bench.add((BenchSupply) i);
							}
						}
						for(BenchSupply b:bench){
							int x=b.getPosition().getX();
							int y=b.getPosition().getY();
							for(int i=x-1;i<=x+1;i++){
								for(int j=y-1;j<=y+1;j++){
									if(i>=0 && j>=0 && i<SimulatorConstant.maxX && j<SimulatorConstant.maxY){
										try {
											b.chargeElement(field.getElement(i,j));
										}catch (DeadException e1) {
										}
									}
								}
							} 
						}
					}
					if(count<robot.size()){
						Robot selected=robot.get(scaletta[count]);
						InteractiveController i=new InteractiveController(main,field,selected);
						i.setLocation(getWidth(), getHeight()/2);						
						count++;						
						class InteractiveControllerListener implements WindowListener{
							public void windowActivated(WindowEvent arg0) {
								changeColorTo(selected,SimulatorConstant.defaultColorForSelected);
								reDraw();
							}
							public void windowClosed(WindowEvent arg0) {
								resetColorOfElements();
								reDraw();	
							}
							public void windowClosing(WindowEvent arg0) {
								resetColorOfElements();
								reDraw();	
							}
							public void windowDeactivated(WindowEvent arg0) {
								resetColorOfElements();
								reDraw();				
							}
							public void windowDeiconified(WindowEvent arg0) {
							}
							public void windowIconified(WindowEvent arg0) {
							}
							public void windowOpened(WindowEvent arg0) {
								changeColorTo(selected,SimulatorConstant.defaultColorForSelected);
								reDraw();
							}
						}
						WindowListener wL=new InteractiveControllerListener();
						i.addWindowListener(wL);
					}
				}		
			}
		}
		ActionListener listener=new Selezionato();
		cI.addActionListener(listener);
		return cI;
	}
	/**
	 * Utilizzando questo pulsante la mossa successiva viene scelta dal computer
	 * @return JMenuItem pulsante per il controllore automatico
	 */
	public JMenuItem controlloreAutomatico(){
		JMenuItem cA=new JMenuItem("Procedi in automatico");
		class Selezionato implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(!robot.isEmpty()){
					if(count==robot.size()){
						JOptionPane.showMessageDialog(null,"Cambio turno");
						count=0;
						scaletta=generaTurno(robot.size());
						ArrayList<BenchSupply> bench=new ArrayList<BenchSupply>();
						ArrayList<Insertable> temp=field.getElements();
						for(Insertable i:temp){
							if(i instanceof BenchSupply){
								bench.add((BenchSupply) i);
							}
						}
						for(BenchSupply b:bench){
							int x=b.getPosition().getX();
							int y=b.getPosition().getY();
							for(int i=x-1;i<=x+1;i++){
								for(int j=y-1;j<=y+1;j++){
									if(i>=0 && j>=0 && i<SimulatorConstant.maxX && j<SimulatorConstant.maxY){
										try {
											b.chargeElement(field.getElement(i,j));
										}catch (DeadException e1) {
										}
									}
								}
							} 
						}
					}
					if(count<robot.size()){
						try{
							changeColorTo(robot.get(scaletta[count]),SimulatorConstant.defaultColorForSelected);
							reDraw();
							new AutomaticController(field,robot.get(scaletta[count]));
							reDraw();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							resetColorOfElements();
						}
						catch(DeadException deadEx){
							new DeadException(field);
							riorganizzaTurni();
						}
					}
					count++;
					reDraw();
				}
			}
		}
		ActionListener listener=new Selezionato();
		cA.addActionListener(listener);
		return cA;
	}
	/**
	 * Genera il menu di salvataggio
	 * @return JMenuItem pulsante per il salvataggio della partita corrente
	 */
	
	public JMenuItem salva(){
		JMenuItem salva=new JMenuItem("Salva partita");
		class Salvataggio implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String nomeFile=JOptionPane.showInputDialog(null, "Inserisci il nome del salvataggio");
					ObjectOutputStream obW = null;				
					try{
						File f=new File(nomeFile+".dat");
						if(f.exists())
							JOptionPane.showMessageDialog(null, "Il file è già esistente");
						else{
							f.createNewFile();
							FileOutputStream out=new FileOutputStream(f);
							obW=new ObjectOutputStream(out);
							obW.writeObject(field.getElements());
						}
					}
					catch(IOException exception){
						JOptionPane.showMessageDialog(null,"Si è verificato un errore durante il salvataggio");
						exception.printStackTrace();
					}
					finally{
						try {
							if(obW!=null)
								obW.close();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Errore durante la chiusura del file");
						}
					}
			}
		}
		ActionListener listener=new Salvataggio();
		salva.addActionListener(listener);
		return salva;
	}
	/**
	 * Restituisce il menù esci usato per uscire dal gioco
	 * @return JMenuItem esce dalla partita
	 */
	public JMenuItem esci(){
		JMenuItem esci=new JMenuItem("Esci");
		class Esci implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		ActionListener listener=new Esci();
		esci.addActionListener(listener);
		return esci;
	}
	/**
	 * Richiamato per caricare una partita da un file
	 * @return JMenuItem pulsante utilizzato per caricare una partita
	 */
	public JMenuItem caricaPartita(){
		JMenuItem carica=new JMenuItem("Carica");
		class CaricaListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				String path=JOptionPane.showInputDialog(null, "Inserisci la path del file di salvataggio");
				ObjectInputStream obR=null;
					try {
						File file=new File(path);
						if(file.exists()){
							FileInputStream in=new FileInputStream(file);
							obR=new ObjectInputStream(in);
							@SuppressWarnings("unchecked")
							ArrayList<Insertable> newList=(ArrayList<Insertable>) obR.readObject();
							Field newField=new Field();
							for(Insertable i:newList)
								newField.insertElement(i);
							field=newField;
							reDraw();
							obR.close();
							inizializzaTurno();
						}
						else
							throw new FileNotFoundException();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "File Inesistente");
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Errore nel file");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Errore nella lettura/scrittura del file");
						e.printStackTrace();
					}
					finally{
						try {
							if(obR!=null)
								obR.close();
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Errore nella chiusura del file");
							e.printStackTrace();
						}		
					}
				}
			}
		ActionListener listener=new CaricaListener();
		carica.addActionListener(listener);	
		return carica;		
	}		
	/**
	 * Genera una scaletta casuale di numeri da 0 a maxNumber, senza ripetizioni
	 * @param maxNumber numero massimo di da inserire
	 * @return array contenente i numeri da 0 a maxNumber in oridine sparso e senza ripetizioni
	 */
	private int[] generaTurno(int maxNumber){
		int[] array=new int[maxNumber];
		Random random=new Random();
		for(int i=0;i<maxNumber;i++){
			array[i]=i;
		}
		for(int i=0;i<maxNumber;i++){
			int temp;
			int swap=random.nextInt(maxNumber-i)+i;
			temp=array[swap];
			array[swap]=array[i];
			array[i]=temp;
		}
		return array;
	}
	/**
	 * Metodo utilizzato per ridisegnare il main
	 */
	public void reDraw(){
		remove(toDraw);
		toDraw=new DrawComponent(field);
		add(toDraw);
		revalidate();
		repaint();
	}
	/**
	 * Inizializza i Robot che devono partecipare al turno
	 */
	private void inizializzaTurno(){
		robot=new ArrayList<Robot>();
		ArrayList<Insertable> temp=field.getElements();
		for(Insertable i:temp){
			if(i instanceof Robot){
				robot.add((Robot)i);
			}
		}
		count=0;
		if(!robot.isEmpty()){
			scaletta=new int[robot.size()];
			scaletta=generaTurno(robot.size());
		}
	}
	/**
	 * Metodo utilizzato per riorganizzare i turni di gioco
	 */
	private void riorganizzaTurni(){
		ArrayList<Robot> temp=new ArrayList<Robot>();
		if(robot.size()>0){
			for(Robot r:robot){
				if(r.getEnergy()<=0){
					temp.add(r);
				}
			}
			if(temp.size()>0){
				robot.removeAll(temp);
				inizializzaTurno();
			}
		}
	}
	/**
	 * Metodo statico per cambiare il colore ad un elemento
	 * @param toChange Elemento a cui vogliamo cambiare il colore 
	 * @param color Colore da impostare a toChange
	 */
	public static void changeColorTo(Drawable toChange,Color color){
		toChange.setColor(color);
	}
	/**
	 * Metodo statico per reimpostare il colore di default di un elemento
	 * @param toChange Elemento a cui vogliamo impostare il colore di default
	 */
	public static void setDefaultColor(Drawable toChange) {
		toChange.setDefaultColor();
	}
	/**
	 * Imposta il colore di default a tutti gli elementi
	 */
	public void resetColorOfElements(){
		for(Insertable i:field.getElements()){
			if(i instanceof Drawable){
				setDefaultColor((Drawable) i);
			}
		}
	}
}
