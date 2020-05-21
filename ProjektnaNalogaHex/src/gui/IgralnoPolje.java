package gui;

import logika.Igra;
import splosno.Koordinati;
import vodja.Vodja;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IgralnoPolje extends JPanel implements MouseListener{
	
	public IgralnoPolje() {
		setBackground(Color.WHITE);
		this.addMouseListener(this);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
	private final static double LINE_WIDTH = 0.2; 
	
	
	// Da je igralno polje v celoti vidno - za silo
	private double stranica() {
		return Math.min(getWidth(), getHeight()) / (4 * Vodja.N);
	}
	
	public int zamik = 50 * Vodja.N / 11;
	
	@Override 
	protected void paintComponent(Graphics g) {
		if (Vodja.igra != null) {
			super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    g2.setStroke(new BasicStroke(3));
		    double stranica = 1.5 * stranica();
		    
		    // Narišemo mrežo 
		    for (int a = 0; a < Vodja.igra.N; a++) {
		    	for (int b = 0; b < Vodja.igra.N; b++) {
				    for (int i = 0; i < 6; i++) {
				    	if (((a == 0) && (i == 2 || i == 3)) || ((a == Vodja.igra.N - 1 && (i == 5 || i == 0)))) {g2.setColor(Vodja.barvaIgralca.get(logika.Igralec.R));}
				    	else if (((b == 0) && (i == 4 || i == 5)) || ((b == Vodja.igra.N - 1 && (i == 2 || i == 1)))){g2.setColor(Vodja.barvaIgralca.get(logika.Igralec.M));}
				    	else {g2.setColor(Color.BLACK);}
				    	g.drawLine((int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2 + stranica * Math.sin(2 * Math.PI * i / 6)),
				    	(int) (zamik + a * stranica * 1.5 + stranica * Math.cos(2 * Math.PI * i / 6)),
				    	(int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2 + stranica * Math.sin(2 * Math.PI * (i + 1) / 6)),
				    	(int) (zamik + a * stranica * 1.5 + stranica * Math.cos(2 * Math.PI * (i + 1) / 6)));
				    }	
		    	}
		    }
		    
		    // Pobarvamo že odigrane poteze
		    Color barva = Vodja.barvaIgralca.get(logika.Igralec.R);
		    for (Koordinati koordinata : Vodja.igra.odigranePoteze) {
		    	int b = koordinata.getX(); int a = koordinata.getY();
		    	g2.setColor(barva);
		    	Polygon p = new Polygon();
		        for (int i = 0; i < 6; i++)
		            p.addPoint((int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2 + stranica * Math.sin(2 * Math.PI * i / 6)),
		              (int) (zamik + a * stranica * 1.5 + stranica * Math.cos(2 * Math.PI * i / 6)));        
		        g2.fillPolygon(p);
		        g2.setColor(Color.BLACK);
		        g2.drawPolygon(p);
		        g2.setColor(barva);
		        if (barva == Vodja.barvaIgralca.get(logika.Igralec.R)) {
		        	barva = Vodja.barvaIgralca.get(logika.Igralec.M);
		        }
		        else {barva = Vodja.barvaIgralca.get(logika.Igralec.R);}
		    }
		    
		    // Zadnja poteza oznaèena rumeno
		    if (Vodja.igra.odigranePoteze.size() > 0) {
			    Koordinati koordinata = Vodja.igra.odigranePoteze.get(Vodja.igra.odigranePoteze.size() - 1);
			    int b = koordinata.getX(); 
			    int a = koordinata.getY();
			    g.setColor(Color.YELLOW);
			    for (int i = 0; i < 6; i++) {
				    g.drawLine((int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2 + stranica * Math.sin(2 * Math.PI * i / 6)),
					    	(int) (zamik + a * stranica * 1.5 + stranica * Math.cos(2 * Math.PI * i / 6)),
					    	(int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2 + stranica * Math.sin(2 * Math.PI * (i + 1) / 6)),
					    	(int) (zamik + a * stranica * 1.5 + stranica * Math.cos(2 * Math.PI * (i + 1) / 6)));
			    }	
		    }
		    
		    // Zmagovalna pot je oznaèena rumeno
		    if (Vodja.igra.zmagovalec != null) {
		    	for (Koordinati koordinata : Vodja.igra.zmagovalnaPot) {
		    		int b = koordinata.getX(); 
				    int a = koordinata.getY();
				    g.setColor(Color.YELLOW);
				    for (int i = 0; i < 6; i++) {
					    g.drawLine((int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2 + stranica * Math.sin(2 * Math.PI * i / 6)),
						    	(int) (zamik + a * stranica * 1.5 + stranica * Math.cos(2 * Math.PI * i / 6)),
						    	(int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2 + stranica * Math.sin(2 * Math.PI * (i + 1) / 6)),
						    	(int) (zamik + a * stranica * 1.5 + stranica * Math.cos(2 * Math.PI * (i + 1) / 6)));
				    }	
		    	}
		    }
	    }
		else {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
		}
		this.repaint();
	}

	public boolean vsebuje(int x0, int y0, int r, int x, int y) {
		return (x - x0) * (x - x0) + (y - y0) * (y - y0) <= r * r;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (Vodja.clovekNaVrsti) {
			int x = e.getX(); int y = e.getY();
			int stranica = (int) (1.5 * stranica());
			for (int a = 0; a < Vodja.igra.N; a++) {
				for (int b = 0; b < Vodja.igra.N; b++) {
					int x0 = (int) (zamik + b * stranica * Math.sqrt(3.) + Math.sqrt(3) * a * stranica / 2);
					int y0 = (int) (zamik + a * stranica * 1.5);
					int r = (int) (stranica * Math.sqrt(3) / 2);
					if (vsebuje(x0, y0, r, x, y)) {
						Vodja.igrajClovekovoPotezo(new Koordinati(b, a));
						break;
					}
				}
			}
		}
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Nerabim 
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Nerabim
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Nerabim 
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Nerabim
	}

}
