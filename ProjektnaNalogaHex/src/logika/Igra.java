package logika;

import java.util.*;

public class Igra {
	
	//Zaenkrat se konstantno dolocena velikost 11*11
	public static final int N = 11;
	private Polje[][] plosca;
	public Igralec naPotezi;
	
	public Igra() {
		plosca = new Polje[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.R;
	}
	
	//Vrne seznam vseh moznih potez(polja, ki so se prazna)
	public List<Koordinati> moznePoteze() {
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
	private Pot pot(int i, int j, Polje r) {
		LinkedList<Integer> x = new LinkedList<Integer>();
		LinkedList<Integer> y = new LinkedList<Integer>();
		while (true){
			int prejsnjiX = i;
			int prejsnjiY = j;
			int noviX = i;
			int noviY = j;
			LinkedList<Koordinati> sosedi = new LinkedList<Koordinati>();
			if (noviY < N) sosedi.add(new Koordinati(noviX, noviY + 1));
			if (noviY > 0) sosedi.add(new Koordinati(noviX, noviY - 1));
			if (noviX < N) sosedi.add(new Koordinati(noviX + 1, noviY));
			if (noviX > 0) sosedi.add(new Koordinati(noviX - 1, noviY));
			if (noviY < N && noviX > 0) sosedi.add(new Koordinati(noviX - 1, noviY + 1));
			if (noviY > 0 && noviX < N) sosedi.add(new Koordinati(noviX + 1, noviY - 1));
			for (Koordinati k : sosedi) {
				if (plosca[k.getX()][k.getY()] == r) {
					if ((k.getY() == N && r == Polje.R) || (k.getX() == N && r == Polje.M)) {
						x.add(prejsnjiX); x.add(noviX);
						y.add(prejsnjiY); y.add(noviY);
						return new Pot(x, y);
					}
					else if ((k.getX() != prejsnjiX || k.getY() != prejsnjiY) && plosca[k.getX()][k.getY()] == r) {
						x.add(prejsnjiX);
						y.add(prejsnjiY);
						prejsnjiX = noviX;
						prejsnjiY = noviY;
						noviX = k.getX();
						noviY = k.getY();	
					}
				}
				else return null;
			}
		}
	}
	
	//S pomocjo pomozne funkcije preveri, ali obstaja zmagovalna pot, drugace vrne null
	public Pot zmagovalnaPot() {
		for (int i = 0; i < N; ++i) {
			if (plosca[i][0] == Polje.R) {
				return pot(i, 0, Polje.R);
			}
		}
		for (int j = 0; j < N; ++j) {
			if (plosca[0][j] == Polje.M) {
				return pot(0, j, Polje.M);
			}
		}
		return null;
	}
	
	//Vrne stanje igre (zmaga\v teku)
	public Stanje stanje() {
		Pot p = zmagovalnaPot();
		if (p != null) {
			switch (plosca[p.x.get(0)][p.y.get(0)]) {
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
			return true;
		}
		else return false;
	}

}
