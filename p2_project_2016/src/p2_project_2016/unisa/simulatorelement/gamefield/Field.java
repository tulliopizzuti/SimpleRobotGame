package p2_project_2016.unisa.simulatorelement.gamefield;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import p2_project_2016.unisa.interfaces.Drawable;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;
import p2_project_2016.unisa.simulatorelement.Element;
import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;

/**
 * Campo in cui possiamo interagire con gli elementi
 * @author tullio
 */
public final class Field implements Drawable,Cloneable{
	private ArrayList<Insertable> listOfElement;
	
	private Insertable field[][];
	/**
	 * Istanzia un campo di dimensioni maxX e maxY
	 */
	public Field(){
		field=new Insertable[SimulatorConstant.maxX][SimulatorConstant.maxY];
		listOfElement=new ArrayList<Insertable>();
	}
	
	/*public Field(ArrayList<Insertable>toInsert){
		field=new Insertable[SimulatorConstant.maxX][SimulatorConstant.maxY];
		listOfElement=toInsert;
		for(Insertable i:listOfElement){
			field[i.getPosition().getX()][i.getPosition().getY()]=i;
		}
	}*/
	/**
	 * Controlla se la posizione x,y è occupata da un oggetto
	 * @param x Ordinata
	 * @param y Ascissa
	 * @return boolean true se la posizione è libera, false altrimenti
	 * @throws IllegalArgumentException lanciata se non è compreso tra 0 e maxX e se y non è compreso tra 0 e maxY
	 */
	public boolean isOccuped(int x,int y){
		if(x<0 || x>SimulatorConstant.maxX-1 || y<0 || y>SimulatorConstant.maxY-1)
			throw new IllegalArgumentException("Valori di x,y non corretti");
		if(field[x][y]==null)
			return false;
		else
			return true;
	}
	/**
	 * Restituisce l'elemento in posizione x,y
	 * @param x ascissa
	 * @param y ordinata
	 * @return Element o null, se in quella posizione non ï¿½ presente alcun elemento
	 */
	public Element getElement(int x,int y){
		return (Element) field[x][y];
	}
	/**
	 * Restituisce la lista degli elementi presenti nel campo
	 * @return lista di elementi 
	 */
	public ArrayList<Insertable> getElements(){
		return listOfElement;
	}
	/**
	 * Controlla se la posizione point è occupata da un oggetto
	 * @param point Posizione da controllare
	 * @return boolean true se la posizione è libera, false altrimenti
	 */
	public boolean isOccuped(Position point){
		if(field[point.getX()][point.getY()]==null)
			return false;
		else
			return true;
	}
	/**
	 * Inserisce un elemento nella sua posizione e nella lista di elementi
	 * @param element Element elemento da inserire
	 */
	public void insertElement(Insertable element){
		Position point=element.getPosition();
		if(!isOccuped(point.getX(),point.getY())){
			field[point.getX()][point.getY()]=element;
			listOfElement.add(element);
		}
	}
	
	
	/**
	 * Occupa una posizione senza che l'oggetto venga aggiunto di nuovo nella lista
	 * @param element Insertable
	 */
	public void occupePosition(Insertable element){
		Position point=element.getPosition();
		if(!isOccuped(point)){
			field[point.getX()][point.getY()]=element;
		}
	}
	/**
	 * Svuota una posizione
	 * @param point Posizione da svuotare
	 */
	public void deleteElement(Position point){
		field[point.getX()][point.getY()]=null;
	}
	/**
	 * Elimina l'oggetto dal campo
	 * @param e Element elemento da rimuovere
	 */
	public void deleteElementFromField(Element e){
		if(listOfElement.remove(e))
			field[e.getPosition().getX()][e.getPosition().getY()]=null;
	}
	
	public boolean isEmpty(){
		return listOfElement.isEmpty();
	}
	
	
	/*
	 * Sovrascrittura di toString()
	 */
	/**
	 * Restituisce una stringa con i vari elementi presenti in un field, separati da \n
	 * La scansione avviene riga per riga
	 */
	public String toString(){
		String string=new String();
		for(Insertable i:listOfElement)
			string+=i+"\n";
		return string;
	}
	/*
	 * Sovrascrittura di equals()
	 */
	public boolean equals(Object anObject){
		if(anObject==null)return false;
		if(!this.getClass().equals(anObject.getClass())) return false;
		Field toControll=(Field) anObject;
		if(toControll.listOfElement.size()!=this.listOfElement.size())return false;
		ArrayList<Insertable>listToControll=toControll.listOfElement;
		return listToControll.containsAll(listOfElement);
	}
	/*
	 * Sovrascrittura di clone
	 */
	public Field clone() {
		try {
			Field newField = (Field) super.clone();
			newField.field=new Insertable[SimulatorConstant.maxX][SimulatorConstant.maxY];
			newField.listOfElement=new ArrayList<Insertable>();
			for(Insertable i:listOfElement){
				newField.insertElement(((Element) i).clone());
			}
			return newField;			
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}
	
	public void draw(Graphics2D g, int width,int height){
		Integer x=0;
		Integer y=0;
		double l_x=width/SimulatorConstant.maxX;
		double l_y=height/SimulatorConstant.maxY;
		g.setColor(Color.BLACK);
		for(double i=0.0D;i<=width;i+=l_x){
			Line2D.Double l=new Line2D.Double(i, 0, i, height);
			g.draw(l);
		}
		for(double i=0.0D;i<=height;i+=l_y){
			
			Line2D.Double l=new Line2D.Double(0, i, width,i);
			g.draw(l);
		}	
		for(int i=0;i<SimulatorConstant.maxX;i++){
			g.drawString("x:"+x.toString(),(int) ((l_x*i)+2),height-15);
			x++;
		}
		for(int i=0;i<SimulatorConstant.maxY;i++){
			g.drawString("y:"+((Integer)Math.abs(y-9)).toString(),2,(int) (((l_y)*i)+15));
			y++;
		}
		for(Insertable d:listOfElement){
			((Drawable)d).draw(g, width, height);
		}
	}
	public void setColor(Color color) {
	}
	public void setDefaultColor() {
	
	}
}
