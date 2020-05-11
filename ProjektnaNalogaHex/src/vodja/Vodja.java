package vodja;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import logika.*;

public class Vodja {
	
	//Slovar s kljuèema R(rdeci) in M(modri), ki imata za vrednosti vrsto igralca(CLOVEK\RACUNALNIK)
	public static Map<Igralec, VrstaIgralca> vrstaIgralca;
	
	//public static GlavnoOkno okno;
	
	public static Igra igra = null;
	
	public static boolean clovekNaVrsti = false;
	
	
	public static void igramoNovoIgro() {
		igra = new Igra();
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
				try {TimeUnit.SECONDS.sleep(2);} catch (Exception e) {};
				List<Koordinati> moznePoteze = igra.moznePoteze();
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
