package inteligenca;

import java.awt.List;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Koordinati;

public class OceniPozicijo {
	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int ocena = 0;
		for (Koordinati koordinata : igra.odigranePoteze) {
			ocena = ocena + oceniKoordinato(koordinata, igra, jaz);
		}
		return ocena;	
	}
	
	public static int oceniKoordinato (Igra igra, Igralec jaz) {
		// TODO
	}
}


/*
 * Strategija grajenja povezave: 
 * - most -> dve polji lahko povezes na dva nacina -> Nasprotnik ne more blokirati
 * 		(navpiscna ali vodoravna smer
 * - Pot gradis z mostovi
 * - Za vsako polje pogledas 3 polja okrog, in ce lahko zgradis most ga zgradis
 */