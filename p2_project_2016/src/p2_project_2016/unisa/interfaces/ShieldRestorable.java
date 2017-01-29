package p2_project_2016.unisa.interfaces;
/**
 * Interfaccia implementata dagli elementi che posssiedono uno scudo che pu� essere riparato
 * @author tullio
 *
 */
public interface ShieldRestorable {
	/**
	 * Metodo usato per ricevere una quantit� intera di scudi
	 * @param shield quantit� di scudo da ricevere. La quantit� di scudo, non sar�
	 * mai superiore a maxShield in SimulatorConstant
	 */
	public void receiveShield(int shield);
	/**
	 * Restituisce la quanti� di scudo
	 * @return int quanitit� di scudo disponibile
	 */
	public int getShield();
}
