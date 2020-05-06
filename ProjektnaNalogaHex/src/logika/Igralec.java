package logika;

public enum Igralec {
	R, M;
	
	public Igralec nasprotnik() {
		return (this == R ? M : R);
	}
	
	public Polje getPolje() {
		return (this == R ? Polje.R : Polje.M);
	}

}
