package gui;

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
public class Platno extends JPanel implements MouseListener, MouseMotionListener{
	
	public int sirina; 
	public int visina; 
	
	public Platno(int sirina, int visina) {
		this.sirina = sirina; 
		this.visina = visina;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public Dimension getPreferredSize() {
		Dimension d = new Dimension(750, 750);
		return d;
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    // Narejeno za silo 
	    for (int a = 1; a < 12; a++) {
	    	for (int b = 1; b < 12; b++) {
	    		Polygon p = new Polygon();
	            for (int i = 0; i < 6; i++) {
	                p.addPoint((int) (a * 50 + 25 * Math.cos(i * 2 * Math.PI / 6)),
	                  (int) (b * 50 + 25 * Math.sin(i * 2 * Math.PI / 6)));    
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
		// TODO 
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
