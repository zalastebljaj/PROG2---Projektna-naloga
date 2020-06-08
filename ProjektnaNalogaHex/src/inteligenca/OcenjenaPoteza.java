package inteligenca;

import splosno.Koordinati;

public class OcenjenaPoteza {
	
	Koordinati poteza;
	int ocena;
	
	//Konstruktor, ki vrne par poteza in ocena te poteze
	public OcenjenaPoteza(Koordinati poteza, int ocena) {
		this.poteza = poteza;
		this.ocena = ocena;
	}
	
	// Primerja oceni dveh potez
	public int compareTo (OcenjenaPoteza op) {
		if (this.ocena < op.ocena) return -1;
		else if (this.ocena > op.ocena) return 1;
		else return 0;
	}
	
	// Metoda za izpis ocenjene poteze
	@Override
	public String toString() {
		return "(" + poteza + ", " + ocena + ")";
	}

}
