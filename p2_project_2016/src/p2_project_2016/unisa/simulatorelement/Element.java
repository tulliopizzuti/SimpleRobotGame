package p2_project_2016.unisa.simulatorelement;


import java.io.Serializable;


import p2_project_2016.unisa.interfaces.Drawable;
import p2_project_2016.unisa.interfaces.Insertable;
import p2_project_2016.unisa.interfaces.Readable;

import p2_project_2016.unisa.simulatorelement.elementcommonpiece.position.Position;
/**
 * Un Element è un elemento del simulatore che deve essere inseribile, disegnabile e serializzabile
 * @author tullio
 */
public abstract class Element implements Insertable,Cloneable,Drawable,Serializable,Readable{	
	/**
	 * Element Serializable ID
	 */
	private static final long serialVersionUID = -5749580614879909424L;
	protected Position position;
	/**
	 * Inizializza un elemento in un punto
	 * @param point Posizione iniziale
	 */
	public Element(Position point){
		this.position=point;
	}
	public void setPosition(int x, int y)  {
		position.setX(x);
		position.setY(y);
	}
	public void setPosition(Position point) {
		position.setPoint(point);
	}
	public Position getPosition() {
		return position.clone();
	}
	/*
	 * Sovrascrittura di toString()
	 */
	public String toString(){
		return getClass().getSimpleName()+"["+position+"]";
	}
	/*
	 * Sovrascrittura di equals()
	 */
	public boolean equals(Object anObject){
		if(anObject==null) return false;
		if(!getClass().equals(anObject.getClass()))
			return false;
		Element e=(Element) anObject;
		return e.position.equals(this.position);
	}	
	/*
	 * Sovrascrittura di clone
	 */
	public Element clone(){
		try{
			Element cloned= (Element) super.clone();
			cloned.position=this.position.clone();
			return cloned;
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
	public String read(){
		return getClass().getSimpleName()+":\n\t"+position+"\n\t";
	}
}