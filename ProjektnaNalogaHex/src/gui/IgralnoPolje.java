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
	
	private final static double LINE_WIDTH = 0.01; 
	
	private double stranica() {
		return Math.min(getWidth(), getHeight()) / 22;
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    double stranica = stranica();
	    for (int a = 0; a < 11; a++) {
	    	for (int b = 0; b < 11; b ++) {
	    		Polygon p = new Polygon();
		        for (int i = 0; i < 6; i++) {
		            p.addPoint((int) (2 * stranica + b * stranica * Math.sqrt(3.) + a * stranica * Math.sqrt(3.) / 2 + stranica * Math.sin(i * 2 * Math.PI / 6)),
		              (int) (2 * stranica + a * stranica * 1.5 + stranica * Math.cos(i * 2 * Math.PI / 6)));    
		        }
		        g.drawPolygon(p); 
	    	}
	    }
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Nerabim 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Nerabim
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int w = (int)(stranica());
			int i = x / w ;
			double di = (x % w) / stranica() ;
			int j = y / w ;
			double dj = (y % w) / stranica() ;
			if (0 <= i && i < 11 &&
					0.5 * LINE_WIDTH < di && di < 1.0 - 0.5 * LINE_WIDTH &&
					0 <= j && j < Igra.N && 
					0.5 * LINE_WIDTH < dj && dj < 1.0 - 0.5 * LINE_WIDTH) {
				System.out.print(i);
				System.out.println(j);
//				Vodja.igrajClovekovoPotezo (new Koordinati(i, j));
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
