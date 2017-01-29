package p2_project_2016.unisa.exception;
/**
 * Il lancio di questa eccezione vuol dire che un elemento non ha energia
 * suffuciente per compire un'azione
 * @author tullio
 */
public class InsufficientEnergyException extends Exception {
	/**
	 * InsufficientEnergyException ID
	 */
	private static final long serialVersionUID = -3313993129164060995L;
	 /**
	  * Instanzia una nuova InsufficientEnergyException
	  */
	public InsufficientEnergyException(){
		 super("Energia insufficiente per eseguire l'azione");
	 }
	/**
	 * Istanzia una nuova InsufficientEnergyException lanciando un messaggio
	 * @param msg Messaggio da lanciare
	 */
	public InsufficientEnergyException(String msg){
		super(msg);
	}
	
}
