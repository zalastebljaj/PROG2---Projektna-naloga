package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import vodja.Vodja;
import vodja.VrstaIgralca;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener{
	
	public IgralnoPolje polje; 
	
	private JMenuBar menuBar;
	private JMenu novaIgra, lastnostiIgralcev, lastnostiVmesnika;
	private JMenuItem CC, CR, RC, RR , velikost, imeIgralcev, algoritemIgranja,
		trajanje, barva, razveljavi;
	private JLabel status;
	
	public Okno() {
		this.setTitle("Igra");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		// Menu
		menuBar = new JMenuBar();
		
		novaIgra = new JMenu("Nova igra");
		CC = new JMenuItem("�lovek - �lovek");
		CC.addActionListener(this);
		RC = new JMenuItem("Ra�unalnik - �lovek");
		RC.addActionListener(this);
		CR = new JMenuItem("�lovek - Ra�unalnik"); 
		CR.addActionListener(this);
		RR = new JMenuItem("Ra�unalnik - Ra�unalnik"); 
		RR.addActionListener(this);
		novaIgra.add(CC);
		novaIgra.add(CR); 
		novaIgra.add(RC); 
		novaIgra.add(RR);
		velikost = new JMenuItem("Velikost"); 
		velikost.addActionListener(this);
		novaIgra.add(velikost);
		menuBar.add(novaIgra);
		
		lastnostiIgralcev = new JMenu("Lastnosti igralcev"); 
		imeIgralcev = new JMenuItem("Imena igralcev"); 
		imeIgralcev.addActionListener(this);
		algoritemIgranja = new JMenuItem("Algoritem igranja"); 
		algoritemIgranja.addActionListener(this);
		razveljavi = new JMenuItem("Razveljavi zadnjo potezo");
		razveljavi.addActionListener(this);
		lastnostiIgralcev.add(imeIgralcev);  
		lastnostiIgralcev.add(algoritemIgranja);
		lastnostiIgralcev.add(razveljavi);
		menuBar.add(lastnostiIgralcev);
		
		lastnostiVmesnika = new JMenu("Lastnosti vmesnika");
		trajanje = new JMenuItem("Trajanje"); 
		trajanje.addActionListener(this);
		barva = new JMenuItem("Barva"); 
		barva.addActionListener(this);
		lastnostiVmesnika.add(trajanje); 
		lastnostiVmesnika.add(barva);
		menuBar.add(lastnostiVmesnika);
		
		this.setJMenuBar(menuBar);
		
		// Igralno polje
		polje = new IgralnoPolje();
		
		GridBagConstraints polje_layout = new GridBagConstraints(); 
		polje_layout.gridx = 0;
		polje_layout.gridy = 0; 
		polje_layout.fill = GridBagConstraints.BOTH;
		polje_layout.weightx = 1.0;
		polje_layout.weighty = 1.0;
		getContentPane().add(polje, polje_layout);
		
		// Status 
		status = new JLabel();
		GridBagConstraints status_layout = new GridBagConstraints(); 
		status_layout.gridx = 0; 
		status_layout.gridy = 1; 
		status_layout.anchor = GridBagConstraints.CENTER;
		getContentPane().add(status, status_layout); 
		
		status.setText("Izberite igro!");
		
	}
	
	// Klik v menu
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == CC) {
			// Spremenim privzete nastavitve in za�nem novo igro 
			Vodja.igralec1 = vodja.VrstaIgralca.CLOVEK;
			Vodja.igralec2 = vodja.VrstaIgralca.CLOVEK;
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == CR) {
			Vodja.igralec1 = vodja.VrstaIgralca.CLOVEK;
			Vodja.igralec2 = vodja.VrstaIgralca.RACUNALNIK;
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == RC) {
			Vodja.igralec1 = vodja.VrstaIgralca.RACUNALNIK;
			Vodja.igralec2 = vodja.VrstaIgralca.CLOVEK;
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == RR) {
			Vodja.igralec1 = vodja.VrstaIgralca.RACUNALNIK;
			Vodja.igralec2 = vodja.VrstaIgralca.RACUNALNIK;
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == velikost) {
			Vodja.N = Integer.parseInt(JOptionPane.showInputDialog("Velikost igralnega polja: "));
			Vodja.igramoNovoIgro();
		}
		else if (e.getSource() == imeIgralcev) { 
			String novoImeEna = JOptionPane.showInputDialog(this, "Kako se imenuje prvi igralec?");
			String novoImeDva = JOptionPane.showInputDialog(this, "Kako se imenuje drugi igralec");
			
			// Spremenim za�etno vrednost 
			Vodja.imeEna = novoImeEna; 
			Vodja.imeDva = novoImeDva;
			
			// Spremenim trenutno vrednsot 
			if (Vodja.igra != null) { 
				Vodja.imeIgralca.replace(logika.Igralec.R, novoImeEna);
				Vodja.imeIgralca.replace(logika.Igralec.M, novoImeDva);
			}
		}
		else if (e.getSource() == algoritemIgranja) { 
			String[] options = {"MiniMax", "AlphaBeta"};
	        String algoritem = (String)JOptionPane.showInputDialog(null, "S katerim Algoritmom naj igra ra�unalnik",
	                null, JOptionPane.QUESTION_MESSAGE, null, options, null);
	        Vodja.algoritem = algoritem;
		}
		else if (e.getSource() == trajanje) { 
			int N = Integer.parseInt(JOptionPane.showInputDialog("Po koliko �asa naj ra�unalnik odigra potezo?"));
			Vodja.cas = N;
		}
		else if (e.getSource() == barva) { 
			Color novaBarvaEna = JColorChooser.showDialog(this, "Izberi barvo prvega igralca",
					Vodja.barvaEna);
			Color novaBarvaDva = JColorChooser.showDialog(this, "Izberite barvo drugega igralca",
					Vodja.barvaDva);
			// Spremenim privzeto nastavitev 
			Vodja.barvaEna = novaBarvaEna; 
			Vodja.barvaDva = novaBarvaDva;
			// Spremenim trenutne barve
			if (Vodja.igra != null) {
				Vodja.barvaIgralca.replace(logika.Igralec.R, novaBarvaEna);
				Vodja.barvaIgralca.replace(logika.Igralec.M, novaBarvaDva);
			}
		}
		else if (e.getSource() == razveljavi) {
			if (Vodja.igra != null) {
				if (Vodja.igralec1 == Vodja.igralec2 && Vodja.igralec1 == vodja.VrstaIgralca.CLOVEK) {
					Vodja.igra.razveljavi();
				}
				else if (Vodja.vrstaIgralca.get(Vodja.igra.naPotezi) == vodja.VrstaIgralca.CLOVEK) {
					Vodja.igra.razveljavi_ClovekRacunalnik();
				}
				else {System.out.println("Naredi ni�");}
			}
			osveziGUI();
		}
		
	}
	
	// Osvezimo GUI
	public void osveziGUI() {
		if (Vodja.igra == null) {status.setText("Izberite igro!");}
		else {
			switch(Vodja.igra.stanje()) {
			case V_TEKU:
				status.setText("Na potezi je " + Vodja.imeIgralca.get(Vodja.igra.naPotezi) + " - " + Vodja.vrstaIgralca.get(Vodja.igra.naPotezi));
				break;
			case ZMAGA_R:
				status.setText("Zmaga R");
				break;
			case ZMAGA_M:
				status.setText("Zmaga M");
				break;
			}
		}
		polje.repaint();
	}
	
	
	

}
