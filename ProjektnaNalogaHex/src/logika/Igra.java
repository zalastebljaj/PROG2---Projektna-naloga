package logika;

import java.util.*;

import splosno.Koordinati;

public class Igra {
	
	//Velikost igralne plosce
	public int N;
	//Igralna plosca
	private Polje[][] plosca;
	//Kdo je na potezi
	public Igralec naPotezi;
	//Seznam koordinat ze odigranih potez
	public LinkedList<Koordinati> odigranePoteze;
	
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

	//Pomozna funkcija, ki sproti preverja ali je kaksno od sosednjih polj od istega igralca in tako gradi pot
	private Pot pot(int prejsnjiX, int prejsnjiY, int noviX, int noviY, Polje barva) {
		LinkedList<Koordinati> koordinate = new LinkedList<Koordinati>();
		LinkedList<Koordinati> sosedi = new LinkedList<Koordinati>();
		if (noviY < N) sosedi.add(new Koordinati(noviX, noviY + 1));
		if (noviY > 0) sosedi.add(new Koordinati(noviX, noviY - 1));
		if (noviX < N) sosedi.add(new Koordinati(noviX + 1, noviY));
		if (noviX > 0) sosedi.add(new Koordinati(noviX - 1, noviY));
		if (noviY < N && noviX > 0) sosedi.add(new Koordinati(noviX - 1, noviY + 1));
		if (noviY > 0 && noviX < N) sosedi.add(new Koordinati(noviX + 1, noviY - 1));
		for (Koordinati k : sosedi) {
			if (plosca[k.getX()][k.getY()] == barva) {
				if ((k.getY() == N && barva == Polje.R) || (k.getX() == N && barva == Polje.M)) {
					koordinate.add(new Koordinati(noviX, noviY));
					koordinate.add(k);
					return new Pot(koordinate);
				}
				else if (koordinate.contains(k) == false) {
					koordinate.add(new Koordinati(noviX, noviY));
					return pot(noviX, noviY, k.getX(), k.getY(), barva);	
				}
			}
		}
		return null;
	}
	
	//S pomocjo pomozne funkcije preveri, ali obstaja zmagovalna pot, drugace vrne null
	public Pot zmagovalnaPot() {
		for (int i = 0; i < N; ++i) {
			if (plosca[i][0] == Polje.R) {
				return pot(i, 0, i, 0, Polje.R);
			}
		}
		for (int j = 0; j < N; ++j) {
			if (plosca[0][j] == Polje.M) {
				return pot(0, j, 0, j, Polje.M);
			}
		}
		return null;
	}
	
	//Vrne stanje igre (zmaga\v teku)
	public Stanje stanje() {
		Pot p = zmagovalnaPot();
		if (p != null) {
			switch (plosca[p.koordinate.getFirst().getX()][p.koordinate.getFirst().getY()]) {
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
		Koordinati k = odigranePoteze.getLast();
		plosca[k.getX()][k.getY()] = Polje.PRAZNO;
		odigranePoteze.removeLast();
		naPotezi = naPotezi.nasprotnik();
	}

}
