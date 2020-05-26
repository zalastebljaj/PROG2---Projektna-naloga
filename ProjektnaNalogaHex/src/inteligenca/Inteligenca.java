package inteligenca;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;

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
		this.globina = 7;//??
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
	
	public Koordinati izberiPotezoMinMax (Igra igra) {
		return MiniMaxPoteze(igra, this.globina, PORAZ, ZMAGA, igra.naPotezi()).poteza;
	}
	
	public static OcenjenaPoteza MiniMaxPoteze(Igra igra, int globina, int max, int min, Igralec jaz) {
		int ocena;
		if (igra.naPotezi() == jaz) {ocena = PORAZ;}
		else {ocena = ZMAGA;}
		List<Koordinati> moznePoteze = igra.moznePoteze();
		Koordinati kandidat = moznePoteze.get(0);
		for (OcenjenaPoteza p: najboljse(igra, globina, jaz)) {
			Koordinati k = p.poteza; 
			Igra kopijaIgre = new Igra(igra); 
			kopijaIgre.odigrajVKopiji(k);
			int ocenak; 
			switch(kopijaIgre.stanje()) {
			case ZMAGA_R: ocenak = (jaz == Igralec.R ? ZMAGA : PORAZ); break; 
			case ZMAGA_M: ocenak = (jaz == Igralec.M ? ZMAGA : PORAZ); break; 
			default: 
				if (globina == 1) {
					ocenak = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				}
				else {ocenak = MiniMaxPoteze(igra, globina - 1, max, min, jaz).ocena;}
			}
			if (igra.naPotezi() == jaz) {
				if (ocenak > ocena) {
					ocena = ocenak;
					kandidat = k;
					min = Math.min(min, ocena); 
				}
			} else {
				if (ocenak < ocena) {
					ocena = ocenak;
					kandidat = k;
					max = Math.max(max, ocena);					
				}	
			}
			if (max >= min)
				return new OcenjenaPoteza (kandidat, ocena);
		}
		return new OcenjenaPoteza(kandidat, ocena); 
	}
	
	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
		int ocena;
		if (igra.naPotezi() == jaz) {ocena = PORAZ;} else {ocena = ZMAGA;}
		List<Koordinati> moznePoteze = igra.moznePoteze();
		Koordinati kandidat = moznePoteze.get(0);
		for (OcenjenaPoteza p: najboljse(igra, globina, jaz)) {
			Koordinati k = p.poteza;
			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigrajVKopiji (k);
			int ocenak;
			switch (kopijaIgre.stanje()) {
			case ZMAGA_R: ocenak = (jaz == Igralec.R ? ZMAGA : PORAZ); break;
			case ZMAGA_M: ocenak = (jaz == Igralec.M ? ZMAGA : PORAZ); break;
			default:
				if (globina == 1) {
					ocenak = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
				}
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
			if (alpha > beta)
				return new OcenjenaPoteza (kandidat, ocena);
		}
		return new OcenjenaPoteza (kandidat, ocena);
	}
	
	private static LinkedList<OcenjenaPoteza> najboljse(Igra igra, int globina, Igralec jaz) {
		LinkedList<OcenjenaPoteza> naj = new LinkedList<OcenjenaPoteza>();
		LinkedList<Koordinati> moznePoteze = igra.moznePoteze();
		Igra kopijaIgre = new Igra(igra);
		for (Koordinati k : moznePoteze) {
			kopijaIgre.odigrajVKopiji (k);
			int ocenaKopije = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
			OcenjenaPoteza e = new OcenjenaPoteza(k, ocenaKopije);
			if (naj.size() < globina) {
				naj.add(e);
			}
			else {
				replaceMinIf(naj, e);
			}
		}
		return naj;
	}
	
	private static LinkedList<OcenjenaPoteza> replaceMinIf(LinkedList<OcenjenaPoteza> naj, OcenjenaPoteza p) {
		OcenjenaPoteza min = naj.get(0);
		int indeks = 0;
		for (int i = 1; i < naj.size(); ++i) {
			if (naj.get(i).compareTo(min) == -1) {
				min = naj.get(i);
				indeks = i;
			}
		}
		if (p.compareTo(min) == 1) {
			naj.subList(indeks, indeks + 1).clear();
			naj.add(indeks, p);
			return naj;
		}
		else return naj;
	}
}
