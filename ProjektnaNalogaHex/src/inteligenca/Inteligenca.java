package inteligenca;

import splosno.KdoIgra;
import splosno.Koordinati;
import logika.Igra;
import logika.Igralec;

public class Inteligenca extends KdoIgra{
	
	private static final int ZMAGA = Integer.MAX_VALUE;
	private static final int PORAZ = -ZMAGA;
	
	private int globina;
	
	public Inteligenca() {
		super("Ime");
	}
	
//	public Koordinati izberiPotezo(Igra igra) {
//		//TODO
//	}
	
//	public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
//		//TODO
//	}

}
