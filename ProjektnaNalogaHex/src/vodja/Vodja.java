package vodja;

import java.util.*;

import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import java.awt.*;

import logika.*;
import splosno.Koordinati;
import gui.*;
import inteligenca.Inteligenca;

public class Vodja {
	
	//Slovar s kljuèema R(rdeci) in M(modri), ki imata za vrednosti vrsto igralca(CLOVEK\RACUNALNIK)
	public static HashMap<Igralec, VrstaIgralca> vrstaIgralca;
	//Igralcu priredimo njegovo barvo
	public static HashMap<Igralec, Color> barvaIgralca;
	//Igralcu priredimo njegovo ime
	public static HashMap<Igralec, String> imeIgralca;
	
	public static Okno okno;
	
	public static Igra igra = null;
	
	public static boolean clovekNaVrsti = false;
	
	//Koliko casa pretece preden racunalnik odigra svojo potezo (zacetna nastavitev je 2 sekundi)
	public static int cas = 2;
	
	// Zaèetna nastavitev za velikost igralne plošèe
	public static int N = 11; 
	
	// Zaèetne nastavitve za barvo
	public static Color barvaEna = Color.red;
	public static Color barvaDva = Color.blue;
	
	// Zaèetne nastavitve imena 
	public static String imeEna = "Igralec 1"; 
	public static String imeDva = "Igralec 2"; 
	
	// Zaèetne nastavitve vrsta igralca 
	public static VrstaIgralca igralec1 = vodja.VrstaIgralca.CLOVEK;
	public static VrstaIgralca igralec2 = vodja.VrstaIgralca.RACUNALNIK;
	
	// Zaèetne nastavitve algoritma - Za zaèetek
	public static String algoritem = "MiniMax"; 
	
	//Zacne novo igro  na N*N igralnem polju
	public static void igramoNovoIgro() {
		igra = new Igra(N);
		barvaIgralca = new HashMap<Igralec, Color>();
		barvaIgralca.put(logika.Igralec.R, barvaEna);
		barvaIgralca.put(logika.Igralec.M, barvaDva);
		vrstaIgralca = new HashMap<Igralec, VrstaIgralca>();
		vrstaIgralca.put(logika.Igralec.R, igralec1);
		vrstaIgralca.put(logika.Igralec.M, igralec2);
		imeIgralca = new HashMap<Igralec, String>();
		imeIgralca.put(logika.Igralec.R, imeEna);
		imeIgralca.put(logika.Igralec.M, imeDva);
		igramo();
	}
	
	public static void igramo() {
		okno.osveziGUI();
		switch (igra.stanje()) {
		case ZMAGA_R :
		case ZMAGA_M :
			return;
		case V_TEKU :
			Igralec igralec = igra.naPotezi;
			VrstaIgralca vrstaNaPotezi = vrstaIgralca.get(igralec);
			switch (vrstaNaPotezi) {
			case CLOVEK :
				clovekNaVrsti = true;
				break;
			case RACUNALNIK :
				racunalnikovaPoteza();
				break;
			}
		}
	}
	
	
	private static Random random = new Random();
	public static Inteligenca racunalnikovaInteligenca = new Inteligenca();
	
	public static void racunalnikovaPoteza() {
		Igra zacetnaIgra = igra;
		SwingWorker<Koordinati, Void> worker = new SwingWorker<Koordinati, Void> () {
			@Override
			protected Koordinati doInBackground() {
				if (igra.odigranePoteze.size() > 0) {
					Koordinati poteza = racunalnikovaInteligenca.izberiPotezo(igra);
					try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {};
					return poteza;
				}
				else {
					try {TimeUnit.SECONDS.sleep(cas);} catch (Exception e) {};
					LinkedList<Koordinati> moznePoteze = igra.moznePoteze();
					int randomIndex = random.nextInt(moznePoteze.size());
					return moznePoteze.get(randomIndex);
				}
			}
			@Override
			protected void done () {
				Koordinati poteza = null;
				try {poteza = get();} catch (Exception e) {};
				if (igra == zacetnaIgra) {
					igra.odigraj(poteza);
					igramo();
				}
			}
		};
		worker.execute();
	}
	
	public static void igrajClovekovoPotezo(Koordinati k) {
		if (igra.odigraj(k)) clovekNaVrsti = false;
		igramo();
	}

}
