package inteligenca;

import java.util.List;

import splosno.KdoIgra;
import splosno.Koordinati;
import logika.Igra;
import logika.Igralec;

public class Inteligenca extends KdoIgra{
	
	private static final int ZMAGA = Integer.MAX_VALUE;
	private static final int PORAZ = -ZMAGA;
	
	//Globina, do katere gre algoritem
	private int globina;
	
	//Konstruktor za tekmovanje
	public Inteligenca() {
		super("Ime");
		this.globina = 9;//??
	}
	
	//Konstruktor za testiranje, ki sprejme parmeter globine
	public Inteligenca(int globina) {
		super("alphabeta globina " + globina);
		this.globina = globina;
	}
	
	//Izbere najbolso potezo glede na alphabeta algoritem
	public Koordinati izberiPotezo (Igra igra) {
		return alphabetaPoteze(igra, this.globina, PORAZ, ZMAGA, igra.naPotezi()).poteza;
	}
	
	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
		int ocena;
		if (igra.naPotezi() == jaz) {ocena = PORAZ;} else {ocena = ZMAGA;}
		List<Koordinati> moznePoteze = igra.moznePoteze();
		Koordinati kandidat = moznePoteze.get(0);
		for (Koordinati k: moznePoteze) {
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigraj (k);
			int ocenak;
			switch (kopijaIgre.stanje()) {
			case ZMAGA_R: ocenak = (jaz == Igralec.R ? ZMAGA : PORAZ); break;
			case ZMAGA_M: ocenak = (jaz == Igralec.M ? ZMAGA : PORAZ); break;
			default:
				if (globina == 1) ocenak = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				else ocenak = alphabetaPoteze (kopijaIgre, globina-1, alpha, beta, jaz).ocena;
			}
			if (igra.naPotezi() == jaz) {
				if (ocenak > ocena) {
					ocena = ocenak;
					kandidat = k;
					alpha = Math.max(alpha,ocena);
				}
			} else {
				if (ocenak < ocena) {
					ocena = ocenak;
					kandidat = k;
					beta = Math.min(beta, ocena);					
				}	
			}
			if (alpha >= beta)
				return new OcenjenaPoteza (kandidat, ocena);
		}
		return new OcenjenaPoteza (kandidat, ocena);
	}
}
