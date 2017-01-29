package p2_project_2016.unisa.interfaces;
/**
 * Interfaccia implementata dagli elementi che posssiedono uno scudo che può essere riparato
 * @author tullio
 *
 */
public interface ShieldRestorable {
	/**
	 * Metodo usato per ricevere una quantità intera di scudi
	 * @param shield quantità di scudo da ricevere. La quantità di scudo, non sarà
	 * mai superiore a maxShield in SimulatorConstant
	 */
	public void receiveShield(int shield);
	/**
	 * Restituisce la quantià di scudo
	 * @return int quanitità di scudo disponibile
	 */
	public int getShield();
}
