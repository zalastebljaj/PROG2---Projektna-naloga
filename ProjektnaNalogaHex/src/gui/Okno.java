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
import javax.swing.JPanel;

import vodja.Vodja;

public class Okno extends JFrame implements ActionListener{
	
	public IgralnoPolje polje; 
	
	private JMenuBar menuBar;
	private JMenu igra, lastnostiIgre, lastnostiIgralcev, lastnostiVmesnika;
	private JMenuItem novaIgra, velikost, imeIgralcev, vrstaIgralcev, algoritemIgranja,
		trajanje, barva;
	private JLabel status;
	
	public Okno() {
		this.setTitle("Igra");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
//		
//		JPanel glavnaPlosca = new JPanel();
//		glavnaPlosca.setLayout(new BoxLayout(glavnaPlosca, BoxLayout.Y_AXIS));
//		this.add(glavnaPlosca);
		
		menuBar = new JMenuBar();
		
		igra = new JMenu("Igra");
		novaIgra = new JMenuItem("Nova igra");
		novaIgra.addActionListener(this);
		igra.add(novaIgra);
		menuBar.add(igra);
		
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
		
		polje = new IgralnoPolje();
		
		GridBagConstraints polje_layout = new GridBagConstraints(); 
		polje_layout.gridx = 0;
		polje_layout.gridy = 0; 
		polje_layout.fill = GridBagConstraints.BOTH;
		polje_layout.weightx = 1.0;
		polje_layout.weighty = 1.0;
		getContentPane().add(polje, polje_layout);
		
		status = new JLabel();
		GridBagConstraints status_layout = new GridBagConstraints(); 
		status_layout.gridx = 0; 
		status_layout.gridy = 1; 
		status_layout.anchor = GridBagConstraints.CENTER;
		getContentPane().add(status, status_layout); 
		
		status.setText("Zaèni novo igro!");
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == novaIgra) {
			// TODO
		}
		else if (e.getSource() == velikost) {
			// TODO 
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
			// TODO
		}
		else if (e.getSource() == barva) { 
//			Color novaBarvaEna = JColorChooser.showDialog(this, "Izberi barvo prvega igralca", Vodja.barvaIgralca.get(R));
//			Vodja.barvaIgralca.replace(R, novaBarvaEna);
			
		}
		
	}
	
//	public void osveziGUI {
//		if (Vodja.igra == null) { status.setText("Igra ni v teku.");}
//		else { 
//			switch(Vodja.igra.stanje()) {
//			case V_TEKU: 
//				status.setText("Na Potezi je " + Vodja.igra.naPotezi + " - " + Vodja.vrstaIgralca.get(Vodja.igra.naPotezi));
//				break;
//			case ZMAGA_R:
//				status.setText("Zmagal je O - " + Vodja.vrstaIgralca.get(Vodja.igra.naPotezi.nasprotnik()));
//				break;
//			case ZMAGA_M: 
//				status.setText("Zmegal je X - " + Vodja.vrstaIgralca.get(Vodja.igra.naPotezi.nasprotnik()));
//				break;
//			}
//		}
//		polje.repaint();
//	}
	

}
