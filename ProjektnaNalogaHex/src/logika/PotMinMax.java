package logika;

import java.util.LinkedList;

import splosno.Koordinati;

/* Razred, ki vsebuje seznam koordinat in min, max
 * Uporabi se za povezano komponento in izracun njene dolzine v inteligenci
 */
public class PotMinMax {
	
	public LinkedList<Koordinati> pot;
	public int min;
	public int max;
	
	// Konstruktor za PotMinMax z danim seznamom koordinat ter minimumom in maksimumom
	public PotMinMax(LinkedList<Koordinati> pot, int min, int max) {
		this.pot = pot;
		this.min = min;
		this.max = max;
	}
	
	// Izpis PotMinMaxa
	public String toString() {
		return "(" + pot + "," + min + "," + max + ")";
	}

}
