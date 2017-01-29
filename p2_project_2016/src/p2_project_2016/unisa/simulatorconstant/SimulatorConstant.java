package p2_project_2016.unisa.simulatorconstant;

import java.awt.Color;

public final class SimulatorConstant {
	/**
	 * Massima ordianta per il campo del simulatore
	 */
	public final static int maxX=12;
	/**
	 * Massima ascissa per il campo del simulatore 
	 */
	public final static int maxY=10;
	/**
	 * Energia base per la classe Energy
	 */
	public final static int baseEnergy=100;
	/**
	 * Valore di resistenza fisso per un ostacolo
	 */
	public final static int obstacleResistance=5;
	/**
	 * Valore di peso fisso per un ostacolo
	 */
	public final static int obstacleWeight=10;
	/**
	 * Quantit� base di scudo per la classe interna di Soldier$Shield
	 */
	public final static int baseShield=3;
	/**
	 * La resistenza di un elemento, viene calcolata in base all'energia e questa percentuale
	 */
	public final static int resistancePercentage=20;
	/**
	 * Il peso di un elemento, viene calcolato in base all'energia e questa percentuale
	 */
	public final static int weightPercentage=20;
	/**
	 * Energia necessaria per spostarsi
	 */
	public final static int energyForMove=1;
	/**
	 * Quantit� di ricariche di scudo da assegnare ai robot Lavoratori
	 */
	public final static int maxShieldRecharger=20;
	/**
	 * Quantit� di ricariche di energia da assegnare ai robot Lavoratori
	 */
	public final static int maxEnergyRecharger=200;
	/**
	 * Quantit� di energia necessaria per curare un elemento
	 */
	public final static int energyForCure=3;
	/**
	 * Energia necessaria per spostare un ostacolo
	 */
	public static final int energyForShift=3;
	/**
	 * Energia disponibile per un banco rifornimento
	 */
	public static final int energyForBench=1000;
	/**
	 * Scudi disponibili per un banco rifornimento
	 */
	public static final int shieldForBench=100;
	/**
	 * Colore di default per gli Obstacle
	 */
	public static final Color defaultColorForObstacle=Color.ORANGE;
	/**
	 * Colore di default per i Worker
	 */
	public static final Color defaultColorForWorker=Color.LIGHT_GRAY;
	/**
	 * Colore di default per i Soldier
	 */
	public static final Color defaultColorForSoldier=Color.MAGENTA;
	/**
	 * Colore di default per gli Elementi Selezionati
	 */
	public static final Color defaultColorForSelected=Color.BLUE;
	/**
	 * Colore di default per gli elementi che subiscono un danno
	 */
	public static final Color defaultColorForDamage=Color.RED;
	/**
	 * Colore di default per gli elementi che vengono curati
	 */
	public static final Color defaultColorForCure=Color.CYAN;
}
