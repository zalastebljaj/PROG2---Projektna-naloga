package vodja;

import java.util.*;

import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import java.awt.*;

import logika.*;
import splosno.Koordinati;
import gui.*;

public class Vodja {
	
	//Slovar s kljuèema R(rdeci) in M(modri), ki imata za vrednosti vrsto igralca(CLOVEK\RACUNALNIK)
	public static Map<Igralec, VrstaIgralca> vrstaIgralca;
	//Igralcu priredimo njegovo barvo
	public static Map<Igralec, Color> barvaIgralca;
	//Igralcu priredimo njegovo ime
	public static Map<Igralec, String> imeIgralca;
	
	public static Okno okno;
	
	public static Igra igra = null;
	
	public static boolean clovekNaVrsti = false;
	
	//Koliko casa pretece preden racunalnik odigra svojo potezo (zacetna nastavitev je 2 sekundi)
	public static int cas = 2;
	
	
	//Zacne igro na standardnem 11*11 polju
	public static void igramoNovoIgro() {
		igra = new Igra();
		igramo();
	}
	
	//Zacne novo igro  na N*N igralnem polju
	public static void igramoNovoIgro(int N) {
		igra = new Igra(N);
		igramo();
	}
	
	public static void igramo() {
		//okno.osveziGUI();
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
			}
		}
	}
	
	
	private static Random random = new Random();
	
	public static void racunalnikovaPoteza() {
		Igra zacetnaIgra = igra;
		SwingWorker<Koordinati, Void> worker = new SwingWorker<Koordinati, Void> () {
			@Override
			protected Koordinati doInBackground() {
				try {TimeUnit.SECONDS.sleep(cas);} catch (Exception e) {};
				LinkedList<Koordinati> moznePoteze = igra.moznePoteze();
				int randomIndex = random.nextInt(moznePoteze.size());
				return moznePoteze.get(randomIndex);
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
