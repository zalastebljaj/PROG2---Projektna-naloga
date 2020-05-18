package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vodja.Vodja;

public class Okno extends JFrame implements ActionListener{
	
	public IgralnoPolje polje; 
	
	private JMenuBar menuBar;
	private JMenu novaIgra, lastnostiIgre, lastnostiIgralcev, lastnostiVmesnika;
	private JMenuItem CC, CR, RC, RR , velikost, imeIgralcev, vrstaIgralcev, algoritemIgranja,
		trajanje, barva;
	private JLabel status;
	
	public Okno() {
		this.setTitle("Igra");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		// Menu
		menuBar = new JMenuBar();
		
		novaIgra = new JMenu("Nova igra");
		CC = new JMenuItem("Èlovek - Èlovek");
		CC.addActionListener(this);
		RC = new JMenuItem("Raèunalnik - Èlovek");
		RC.addActionListener(this);
		CR = new JMenuItem("Èlovek - Raèunalnik"); 
		RC.addActionListener(this);
		RR = new JMenuItem("Raèunalnik - Raèunalnik"); 
		RR.addActionListener(this);
		novaIgra.add(CC);
		novaIgra.add(RC); 
		novaIgra.add(CR); 
		novaIgra.add(RR);
		menuBar.add(novaIgra);
		
		lastnostiIgre = new JMenu("Lastnosti igre"); 
		velikost = new JMenuItem("Velikost"); 
		velikost.addActionListener(this);
		lastnostiIgre.add(velikost); 
		menuBar.add(lastnostiIgre);
		
		lastnostiIgralcev = new JMenu("Lastnosti igralcev"); 
		imeIgralcev = new JMenuItem("Imena igralcev"); 
		imeIgralcev.addActionListener(this);
		vrstaIgralcev = new JMenuItem("Vrsta igralcev"); 
		vrstaIgralcev.addActionListener(this);
		algoritemIgranja = new JMenuItem("Algoritem igranja"); 
		algoritemIgranja.addActionListener(this);
		lastnostiIgralcev.add(imeIgralcev); 
		lastnostiIgralcev.add(vrstaIgralcev); 
		lastnostiIgralcev.add(algoritemIgranja); 
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
			Vodja.igramoNovoIgro(vodja.VrstaIgralca.CLOVEK, vodja.VrstaIgralca.CLOVEK);
		}
		else if (e.getSource() == CR) {
			Vodja.igramoNovoIgro(vodja.VrstaIgralca.CLOVEK, vodja.VrstaIgralca.RACUNALNIK);
		}
		else if (e.getSource() == RC) {
			Vodja.igramoNovoIgro(vodja.VrstaIgralca.RACUNALNIK, vodja.VrstaIgralca.CLOVEK);
		}
		else if (e.getSource() == RR) {
			Vodja.igramoNovoIgro(vodja.VrstaIgralca.RACUNALNIK, vodja.VrstaIgralca.RACUNALNIK);
		}
		else if (e.getSource() == velikost) {
			int N = Integer.parseInt(JOptionPane.showInputDialog("Velikost igralnega polja: "));
			Vodja.igramoNovoIgro(N, vodja.VrstaIgralca.CLOVEK, vodja.VrstaIgralca.RACUNALNIK);
		}
		else if (e.getSource() == imeIgralcev) { 
			// TODO 
		}
		else if (e.getSource() == vrstaIgralcev) { 
			// TODO 
		}
		else if (e.getSource() == algoritemIgranja) { 
			// TODO 
		}
		else if (e.getSource() == trajanje) { 
			int N = Integer.parseInt(JOptionPane.showInputDialog("Po koliko èasa naj raèunalnik odigra potezo?"));
			Vodja.cas = N;
		}
		else if (e.getSource() == barva) { 
			Color novaBarvaEna = JColorChooser.showDialog(this, "Izberi barvo prvega igralca",
					Vodja.barvaIgralca.get(logika.Igralec.R));
			Color novaBarvaDva = JColorChooser.showDialog(this, "Izberite barvo drugega igralca",
					Vodja.barvaIgralca.get(logika.Igralec.M));
			Vodja.barvaIgralca.replace(logika.Igralec.R, novaBarvaEna);
			Vodja.barvaIgralca.replace(logika.Igralec.M, novaBarvaDva);
		}
		
	}
	
	// Osvezimo GUI
	public void osveziGUI() {
		if (Vodja.igra == null) {status.setText("Igra ni v teku!");}
		else {
			switch(Vodja.igra.stanje()) {
			case V_TEKU:
				status.setText("Na potezi je");
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
