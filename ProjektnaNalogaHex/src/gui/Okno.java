package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Okno extends JFrame implements ActionListener{
	
	public Platno platno; 
	
	private JMenuBar menuBar;
	private JMenu igra, lastnostiIgre, lastnostiIgralcev, lastnostiVmesnika;
	private JMenuItem novaIgra, velikost, imeIgralcev, vrstaIgralcev, algoritemIgranja,
		trajanje, barva;
	
	public Okno(int sirina, int visina) {
		this.setTitle("Igra");
		
		JPanel glavnaPlosca = new JPanel();
		glavnaPlosca.setLayout(new BoxLayout(glavnaPlosca, BoxLayout.Y_AXIS));
		this.add(glavnaPlosca);
		
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
		
		glavnaPlosca.add(menuBar);
		
		platno = new Platno(sirina, visina); 
		glavnaPlosca.add(platno); 
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == novaIgra) {
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
			// TODO 
		}
		
	}
	

}
