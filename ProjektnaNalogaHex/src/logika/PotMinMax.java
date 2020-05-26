package logika;

import java.util.LinkedList;

import splosno.Koordinati;

public class PotMinMax {
	
	public LinkedList<Koordinati> pot;
	public int min;
	public int max;
	
	public PotMinMax(LinkedList<Koordinati> pot, int min, int max) {
		this.pot = pot;
		this.min = min;
		this.max = max;
	}
	
	public String toString() {
		return "(" + pot + "," + min + "," + max;
	}

}
