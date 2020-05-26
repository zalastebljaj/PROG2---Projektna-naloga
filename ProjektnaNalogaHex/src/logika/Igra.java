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
	
	public LinkedList<Koordinati> koordinate = new LinkedList<Koordinati>();
	public int min = N;
	public int max = 0;
	
	//pomozna funkcija, ki vrne vse sosede glede na dane koordinate
	private LinkedList<Koordinati> sosedi(Koordinati k){
		int x = k.getX();
		int y = k.getY();
		LinkedList<Koordinati> sosedi = new LinkedList<Koordinati>();
		if (y < N - 1) sosedi.add(new Koordinati(x, y + 1));
		if (x < N - 1) sosedi.add(new Koordinati(x + 1, y));
		if (y < N - 1 && x > 0) sosedi.add(new Koordinati(x - 1, y + 1));
		if (y > 0 && x < N - 1) sosedi.add(new Koordinati(x + 1, y - 1));
		if (y > 0) sosedi.add(new Koordinati(x, y - 1));
		if (x > 0) sosedi.add(new Koordinati(x - 1, y));
		return sosedi;
	}
	
	// Pomozna funkcija, ki vrne le sosede ki gredo protu svoji zmagovalni strani
	private LinkedList<Koordinati> sosediProtiRobu(Koordinati k, Igralec jaz) {
		int x = k.getX(); 
		int y = k.getY(); 
		LinkedList<Koordinati> sosedi = new LinkedList<Koordinati>(); 
		if (jaz == Igralec.R) {
			if (x > 0 && y < N - 1) {
				sosedi.add(new Koordinati(x - 1, y + 1));
				sosedi.add(new Koordinati(x, y + 1));
			}
			else if (x == 0 && y < N - 1) {
				sosedi.add(new Koordinati(x, y + 1));
			}
		}
		return sosedi;
	}
	
	// Pomo�na funkcija, ki pogleda mo�nosti za nadaljevanje poti
	private int moznosti(int najkrajsaPot, Koordinati t, Igra igra, Igralec jaz) {
		int x = t.getX(); 
		int y = t.getY(); 
		if (jaz == Igralec.R) {
			if (y != N - 1) {
				if (igra.plosca[x][y] == Polje.PRAZNO) {
					return najkrajsaPot = 1 + najkrajsaPot(new Koordinati(x, y), igra, jaz);
				}
				else if (igra.plosca[x][y] == Polje.R) {
					return najkrajsaPot = najkrajsaPot(new Koordinati(x, y), igra, jaz);
				}
				else {
					return najkrajsaPot = N + 100;
				}
			}
			else {return najkrajsaPot;}
		}
		return 0;
	}
	
	// Funkcija, ki vrne najkraj�o pot iz posamezne za�etne koordinate
	public int najkrajsaPot(Koordinati k, Igra igra, Igralec jaz) {
		int najkrajsaPot = 0; 
		LinkedList<Koordinati> sosedi = sosediProtiRobu(k, jaz);
		int moznaPolja = sosedi.size(); 
		if (moznaPolja == 0) {
			Koordinati t = sosedi.getFirst();
			najkrajsaPot += moznosti(najkrajsaPot, t, igra, jaz);
			}
		else {
			Koordinati t1 = sosedi.getFirst();
			Koordinati t2 = sosedi.getLast(); 
			najkrajsaPot += Math.min(moznosti(najkrajsaPot, t1, igra, jaz),
					moznosti(najkrajsaPot, t2, igra, jaz)); 
			
		}
		return najkrajsaPot; 
	}
	
	// Funkcija ki vrne array s najmanj�im �tevilom potez za dokon�anje poti iz posamezen koordinate
	public int[] najkrajsePoti(Igra igra, Igralec jaz) {
		int[] najkrajsePoti = new int [N];
		
		if (jaz == Igralec.R) {
			for (int i = 0; i < N; i++) {
				najkrajsePoti[i] = najkrajsaPot(new Koordinati(i, 0), igra, jaz);
			}
		}
		return najkrajsePoti;
		
	}

	/**Pomozna funkcija, ki sproti preverja ali je kaksno od sosednjih polj danega polja(podanega s koordinatami)
	 * od istega igralca in tako gradi seznam vseh polj, ki so iste barve in so povezana z zacetnim poljem
	 * (seznam koordinat vseh takih polj)
	 */
	public PotMinMax pot(Koordinati t, Polje barva) {
		if (barva == Polje.R && t.getY() < min) {min = t.getY();}
		else if (barva == Polje.M && t.getX() < min) {min = t.getX();}
		if (barva == Polje.R && t.getY() > max) {max = t.getY();}
		else if (barva == Polje.M && t.getX() > max) {max = t.getX();}
		for (Koordinati k : sosedi(t)) {
			if (koordinate.contains(t) == false) koordinate.add(t);
			if (plosca[k.getX()][k.getY()] == barva) {
				if ((k.getY() == N - 1 && barva == Polje.R) || (k.getX() == N - 1 && barva == Polje.M)) {
					if (koordinate.contains(k) == false) koordinate.add(k);
					if (barva == Polje.R && k.getY() > max) {max = k.getY();}
					if (barva == Polje.R && k.getY() < min) {min = k.getY();}
					if (barva == Polje.M && k.getX() > max) {max = k.getX();}
					if (barva == Polje.M && k.getX() < min) {min = k.getX();}
				}
				else if (koordinate.contains(k) == false) {
					pot(k, barva);	
				}
				else continue;
			}
		}
		return new PotMinMax(koordinate, min, max);
	}
	
	/**S pomocjo pomozne metode pot vrne seznam koordinat vseh enako pobarvanih povezanih polj, ki se drzijo enega od 
	 * robov igralne plosce (polja s koordinatami (i, 0) za rdece in (0, j) za modre).
	 */
	public LinkedList<Koordinati> zmagovalnaPot() {
		min = N;
		max = 0;
		koordinate.clear();
		if (naPotezi == Igralec.M) {
			for (int i = 0; i < N; ++i) {
				min = N;
				max = 0;
				koordinate.clear();
				if (plosca[i][0] == Polje.R) {
					Koordinati k = new Koordinati(i, 0);
					for (int m = 0; m < N; ++m) {
						Koordinati l = new Koordinati(m, N - 1);
						if (pot(k, Polje.R).pot.contains(l)) {
							min = N;
							max = 0;
							koordinate.clear();
							zmagovalnaPot = pot(k, Polje.R).pot;
							min = N;
							max = 0;
							koordinate.clear();
							return pot(k, Polje.R).pot;
						}
					}
				}
			}
		}
		else {
			for (int j = 0; j < N; ++j) {
				min = N;
				max = 0;
				koordinate.clear();
				if (plosca[0][j] == Polje.M) {
					Koordinati k = new Koordinati(0, j);
					for (int n = 0; n < N; ++n) {
						Koordinati l = new Koordinati(N - 1, n);
						if (pot(k, Polje.M).pot.contains(l)) {
							min = N;
							max = 0;
							koordinate.clear();
							zmagovalnaPot = pot(k, Polje.M).pot;
							min = N;
							max = 0;
							koordinate.clear();
							return pot(k, Polje.M).pot;
						}
					}
				}
			}
		}
		return null;
	}
	
	/**Preveri ali se polja iz metode zmagovalnaPot drzijo nasprotnega roba in vrne igralca, ki je zmagal, 
	* �e obstaja, drugace vrne null.
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
	
	public boolean odigrajVKopiji(Koordinati k) {
		if (plosca[k.getX()][k.getY()] == Polje.PRAZNO) {
			plosca[k.getX()][k.getY()] = naPotezi.getPolje();
			naPotezi = naPotezi.nasprotnik();
			return true;
		}
		else return false;
	}
	
	//Razveljavi zadnjo potezo
	public void razveljavi() {
		Koordinati k = odigranePoteze.getLast();
		plosca[k.getX()][k.getY()] = Polje.PRAZNO;
		odigranePoteze.removeLast();
		naPotezi = naPotezi.nasprotnik();
	}
	
	public void razveljavi_ClovekRacunalnik() {
		Koordinati k = odigranePoteze.getLast(); 
		plosca[k.getX()][k.getY()] = Polje.PRAZNO; 
		odigranePoteze.removeLast();
		//Koordinati K = odigranePoteze.getLast(); 
		plosca[k.getX()][k.getY()] = Polje.PRAZNO; 
		odigranePoteze.removeLast();
	}

}
