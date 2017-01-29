package p2_project_2016.unisa.simulatorelement.elementcommonpiece.position;

import java.io.Serializable;

import p2_project_2016.unisa.simulatorconstant.SimulatorConstant;

/**
 * Classe utilizzata per gestire la posizione di un elemento in una griglia 
 * 
 * @author tullio
 */
public final class Position implements Cloneable,Serializable{
	/**
	 * Position Serializable ID
	 */
	private static final long serialVersionUID = -2165105758264321067L;
	private int x;
	private int y;
	/*
	 * Metodi costruttori
	 */
	public Position(){
		x=0;
		y=0;
	}
	/**
	 * Costruttore di un elemento nella posizione x,y
	 * @param x Ordinata
	 * @param y Ascissa
	 */
	public Position(int x,int y){
		if(x<0 || x>=SimulatorConstant.maxX || y<0 || y>=SimulatorConstant.maxY)
			throw new IllegalArgumentException("Parametri x,y non corretti");
		this.x=x;
		this.y=y;
	}
	/**
	 * Restituisce l'ordinata del punto
	 * @return int X l'ordinata di questo punto
	 */
	public int getX(){
		return x;
	}
	/**
	 * Restituisce l'ascissa del punto
	 * @return int Y l'ordinata di questo punto
	 */
	public int getY(){
		return y;
	}
	/**
	 * Imposta la posizione in un punto di tipo Position
	 * @param point La posizione in cui deve essere posizionato l'elemento 
	 */
	
	public void setPoint(Position point){
		this.x=point.x;
		this.y=point.y;
	}
	/**
	 * Imposta l'ordinata
	 * @param x Ordinata da modificare
	 */
	public void setX(int x){
		if(x<0 || x>=SimulatorConstant.maxX)
			throw new IllegalArgumentException("Parametro x non corretto");
		this.x=x;
	}
	/**
	 * Imposta l'ascissa
	 * @param y Ascissa da modificare
	 */
	public void setY(int y){
		if(y<0 || y>=SimulatorConstant.maxY)
			throw new IllegalArgumentException("Parametro y non corretto");
		this.y=y;
	}
	/**
	 * Controlla che il punto passato come parametro sia sopra, sotto, a destra o a sinistra rispetto al parametro implicito
	 * @param otherPoint punto su cui effettuare il controllo
	 * @return true se è vicino, false altrimenti
	 */
	public boolean isNear(Position otherPoint){
		if((x==(otherPoint.getX()+1) || x==(otherPoint.getX()-1)) && y==otherPoint.getY()){
			return true;
		}
		else if ((y==(otherPoint.getY()+1) || y==(otherPoint.getY()-1))&& otherPoint.getX()==x){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Controlla se other si trova sopra alla posizione che richiama il metodo
	 * @param other Punto su cui effettuare il controllo
	 * @return true se si trova sopra, false altrimenti
	 */
	public boolean isUp(Position other){
		return (this.getY()+1)==other.getY();
	}
	/**
	 * Controlla se other si trova sotto alla posizione che richiama il metodo
	 * @param other Punto su cui effettuare il controllo
	 * @return true se si trova sopra, false altrimenti
	 */
	public boolean isDown(Position other){
		return (this.getY()-1)==other.getY();
	}
	/**
	 * Controlla se other si trova a sinistra della posizione che richiama il metodo
	 * @param other Punto su cui effettuare il controllo
	 * @return true se si trova sopra, false altrimenti
	 */
	public boolean isLeft(Position other){
		return (this.getX()-1)==other.getX();
	}
	/**
	 * Controlla se other si trova a destra della posizione che richiama il metod
	 * @param other Punto su cui effettuare il controllo
	 * @return true se si trova sopra, false altrimenti
	 */
	public boolean isRight(Position other){
		return (this.getX()+1)==other.getX();
	}
	/*
	 * Sovrascrittura di toString()
	 */
	public String toString(){
		return "Position["+x+", "+y+"]";
	}
	/*
	 * Sovrascrittura di equals()
	 */
	public boolean equals(Object anObject){
		if(anObject==null) return false;
		if(!getClass().equals(anObject.getClass())) return false;
		Position p=(Position) anObject;
		return x==p.x && y==p.y;
	}
	/*
	 * Sovrascrittura di clone()
	 */
	public Position clone(){
		try{
			return (Position) super.clone();
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
}
