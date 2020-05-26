package inteligenca;

import java.awt.List;
import java.util.LinkedList;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.PotMinMax;
import splosno.Koordinati;

public class OceniPozicijo {
	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		Polje[][] plosca = igra.getPlosca();
		LinkedList<Koordinati> odigrane = igra.odigranePoteze;
		int maxDolzinaRdec = 0;
		int maxDolzinaModer = 0;
		for (Koordinati k : odigrane) {
			LinkedList<Koordinati> obiskane = new LinkedList<Koordinati>();
			if (obiskane.contains(k) == false) {
				int xk = k.getX();
				int yk = k.getY();
				if (plosca[xk][yk] == Polje.R) {
					PotMinMax komponenta = igra.pot(k, Polje.R);
					igra.min = igra.N;
					igra.max = 0;
					igra.koordinate.clear();
					int min = komponenta.min;
					int max = komponenta.max;
					if (max - min + 1 > maxDolzinaRdec) maxDolzinaRdec = max - min + 1;
				}
				else if (plosca[xk][yk] == Polje.M){
					PotMinMax komponenta = igra.pot(k, Polje.M);
					igra.min = igra.N;
					igra.max = 0;
					igra.koordinate.clear();
					int min = komponenta.min;
					int max = komponenta.max;
					if (max - min + 1 > maxDolzinaModer) maxDolzinaModer = max - min + 1;
				}
			}
		}
		if (jaz == Igralec.R) {return maxDolzinaRdec - 5*maxDolzinaModer;}
		else return maxDolzinaModer - 5*maxDolzinaRdec;
	}
}
	
//	public static int oceniPozicijo(Igra igra, Igralec jaz) {
//		int ocena = 0;
//		for (Koordinati koordinata : igra.odigranePoteze) {
//			ocena = ocena + oceniKoordinato(koordinata, igra, jaz);
//		}
//		return ocena;	
//	}
//	
//	public static int oceniKoordinato (Igra igra, Igralec jaz) {
//		// TODO
//	}
//}
//
//
///*
// * Strategija grajenja povezave: 
// * - most -> dve polji lahko povezes na dva nacina -> Nasprotnik ne more blokirati
// * 		(navpiscna ali vodoravna smer
// * - Pot gradis z mostovi
// * - Za vsako polje pogledas 3 polja okrog, in ce lahko zgradis most ga zgradis
// */