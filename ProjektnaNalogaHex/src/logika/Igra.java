package logika;

import java.util.*;

import splosno.Koordinati;
import vodja.Vodja;

public class Igra {
	
	//poskus
	//Velikost igralne plosce
	public int N;
	//Igralna plosca
	private Polje[][] plosca;
	//Kdo je na potezi
	public Igralec naPotezi;
	//Seznam koordinat ze odigranih potez
	public LinkedList<Koordinati> odigranePoteze;
	
	public Igralec zmagovalec;
	
	public LinkedList<Koordinati> zmagovalnaPot;
	
	//Osnovni konstruktor, ki naredi igro s poljem 11*11
	public Igra() {
		N = 11;
		plosca = new Polje[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.R;
		odigranePoteze = new LinkedList<Koordinati>();
		zmagovalec = null;
		zmagovalnaPot = null;
	}
	
	//Konstruktor, ki vzame N kot parameter in naredi igro s poljem velikosti N*N
	public Igra(int N) {
		this.N = N;
		plosca = new Polje[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.R;
		odigranePoteze = new LinkedList<Koordinati>();
		zmagovalec = null;
		zmagovalnaPot = null;
	}
	
	//Konstruktor, ki naredi kopjo igre, ki jo dobi kot parameter
	public Igra(Igra igra) {
		this.N = igra.N;
		this.plosca = new Polje[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; j++) {
				this.plosca[i][j] = igra.plosca[i][j];
			}
		}
		this.naPotezi = igra.naPotezi;
		this.odigranePoteze = igra.odigranePoteze;
		this.zmagovalec = igra.zmagovalec;
		this.zmagovalnaPot = igra.zmagovalnaPot;
	}
	
	//Metoda, ki vrne igralca na potezi
	public Igralec naPotezi() {
		return naPotezi;
	}
	
	//Metoda, ki vrne igralno plosco
	public Polje[][] getPlosca(){
		return plosca;
	}
	
	//Vrne seznam vseh moznih potez(polja, ki so se prazna)
	public LinkedList<Koordinati> moznePoteze() {
		LinkedList<Koordinati> mp = new LinkedList<Koordinati>();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (plosca[i][j] == Polje.PRAZNO) {
					mp.add(new Koordinati(i, j));
				}
			}
		}
		return mp;
	}
	
	LinkedList<Koordinati> koordinate = new LinkedList<Koordinati>();
	
	//pomozna funkcija, ki vrne vse sosede glede na dane koordinate
	private LinkedList<Koordinati> sosedi(Koordinati k){
		int x = k.getX();
		int y = k.getY();
		LinkedList<Koordinati> sosedi = new LinkedList<Koordinati>();
		if (y < N - 1) sosedi.add(new Koordinati(x, y + 1));
		if (y > 0) sosedi.add(new Koordinati(x, y - 1));
		if (x < N - 1) sosedi.add(new Koordinati(x + 1, y));
		if (x > 0) sosedi.add(new Koordinati(x - 1, y));
		if (y < N - 1 && x > 0) sosedi.add(new Koordinati(x - 1, y + 1));
		if (y > 0 && x < N - 1) sosedi.add(new Koordinati(x + 1, y - 1));
		return sosedi;
	}

	/**Pomozna funkcija, ki sproti preverja ali je kaksno od sosednjih polj danega polja(podanega s koordinatami)
	 * od istega igralca in tako gradi seznam vseh polj, ki so iste barve in so povezana z zacetnim poljem
	 * (seznam koordinat vseh takih polj)
	 */
	private LinkedList<Koordinati> pot(Koordinati t, Polje barva) {
		for (Koordinati k : sosedi(t)) {
			if (koordinate.contains(t) == false) koordinate.add(t);
			if (plosca[k.getX()][k.getY()] == barva) {
				if ((k.getY() == N - 1 && barva == Polje.R) || (k.getX() == N - 1 && barva == Polje.M)) {
					if (koordinate.contains(k) == false) koordinate.add(k);
				}
				else if (koordinate.contains(k) == false) {
					pot(k, barva);	
				}
				else continue;
			}
		}
		return koordinate;
	}
	
	/**S pomocjo pomozne metode pot vrne seznam koordinat vseh enako pobarvanih povezanih polj, ki se drzijo enega od 
	 * robov igralne plosce (polja s koordinatami (i, 0) za rdece in (0, j) za modre).
	 */
	public LinkedList<Koordinati> zmagovalnaPot() {
		koordinate.clear();
		if (naPotezi == Igralec.M) {
			for (int i = 0; i < N; ++i) {
				koordinate.clear();
				if (plosca[i][0] == Polje.R) {
					Koordinati k = new Koordinati(i, 0);
					for (int m = 0; m < N; ++m) {
						Koordinati l = new Koordinati(m, N - 1);
						if (pot(k, Polje.R).contains(l)) {
							koordinate.clear();
							zmagovalnaPot = pot(k, Polje.R);
							return pot(k, Polje.R);
						}
					}
				}
			}
		}
		else {
			for (int j = 0; j < N; ++j) {
				koordinate.clear();
				if (plosca[0][j] == Polje.M) {
					Koordinati k = new Koordinati(0, j);
					for (int n = 0; n < N; ++n) {
						Koordinati l = new Koordinati(N - 1, n);
						if (pot(k, Polje.M).contains(l)) {
							koordinate.clear();
							zmagovalnaPot = pot(k, Polje.M);
							return pot(k, Polje.M);
						}
					}
				}
			}
		}
		return null;
	}
	
	/**Preveri ali se polja iz metode zmagovalnaPot drzijo nasprotnega roba in vrne igralca, ki je zmagal, 
	* èe obstaja, drugace vrne null.
	*/
	public Polje zmagovalec() {
		LinkedList<Koordinati> p = zmagovalnaPot();
		if (p != null) {
			if (naPotezi == Igralec.M) {
				zmagovalec = Igralec.R;
				return Polje.R;
			}
			else {
				zmagovalec = Igralec.M;
				return Polje.M;
			}
		}
		return null;
	}
	
	//Preveri ali imamo zmagovalca in vrne stanje igre (zmaga\v teku)
	public Stanje stanje() {
		Polje z = zmagovalec();
		if (z != null) {
			switch (z) {
			case R : return Stanje.ZMAGA_R;
			case M : return Stanje.ZMAGA_M;
			case PRAZNO : assert false;
			}
		}
		return Stanje.V_TEKU;
	}
	
	//Poskusa odigrati potezo, ce je polje ze zasedeno, vrne false
	public boolean odigraj(Koordinati k) {
		if (plosca[k.getX()][k.getY()] == Polje.PRAZNO) {
			plosca[k.getX()][k.getY()] = naPotezi.getPolje();
			naPotezi = naPotezi.nasprotnik();
			odigranePoteze.addLast(k);
			return true;
		}
		else return false;
	}
	
	//Razveljavi zadnjo potezo
	public void razveljavi() {
		if (Vodja.vrstaIgralca.get(naPotezi) == vodja.VrstaIgralca.CLOVEK && (Vodja.igralec1 == Vodja.igralec2)) {
			Koordinati k = odigranePoteze.getLast();
			plosca[k.getX()][k.getY()] = Polje.PRAZNO;
			odigranePoteze.removeLast();
			naPotezi = naPotezi.nasprotnik();
		}
		else if (Vodja.vrstaIgralca.get(naPotezi) == vodja.VrstaIgralca.CLOVEK) {
			Koordinati k = odigranePoteze.getLast(); 
			plosca[k.getX()][k.getY()] = Polje.PRAZNO; 
			odigranePoteze.removeLast();
			Koordinati K = odigranePoteze.getLast(); 
			plosca[k.getX()][k.getY()] = Polje.PRAZNO; 
			odigranePoteze.removeLast();
		}
	}

}
