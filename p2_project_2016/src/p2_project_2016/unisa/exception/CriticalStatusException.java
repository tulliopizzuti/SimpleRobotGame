package p2_project_2016.unisa.exception;
/**
 * Il lancio di questa eccezione vuol dire che un elemento con energia
 * ha ricevuto un danno maggiore del 75% della sua energia attuale 
 * @author tullio
 */
public class CriticalStatusException extends RuntimeException {

	/**
	 * CriticalStatusException ID generato automaticamente
	 */
	private static final long serialVersionUID = -2752974595444944429L;

	public CriticalStatusException(){
		 super("L'elemento ha subito un danno maggiore o uguale al 75% della sua energia");
	 }
	/**
	 * Lancia l'eccezzione con un messaggio
	 * @param msg Messaggio con cui lanciare l'eccezione
	 */
	public CriticalStatusException(String msg){
		super(msg);
	}
}
