package logika;

public enum Igralec {
	R, M;
	
	// Vrne nasprotnika od igralca, ki je na vrsti
	public Igralec nasprotnik() {
		return (this == R ? M : R);
	}
	
	// Polje oznaci kot rdece oz. modro
	public Polje getPolje() {
		return (this == R ? Polje.R : Polje.M);
	}

}
