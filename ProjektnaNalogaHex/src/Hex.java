import gui.Okno;
import vodja.Vodja;

public class Hex {

	public static void main(String[] args) {
		
		Okno okno = new Okno(); 
		okno.pack();
		okno.setVisible(true);
		Vodja.okno = okno;
	}

}
