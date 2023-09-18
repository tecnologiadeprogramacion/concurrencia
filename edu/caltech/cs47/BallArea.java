package edu.caltech.cs47;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

/**
 * A Swing component in which BouncingBall objects can animate themselves.
 * As written, it is not thread safe (as BouncingBallSynchDemo demonstrates).
 * @author joev
 *
 */

final class BallArea extends JPanel {
	
	public static final int DEFAULT_HEIGHT = 300;
	public static final int DEFAULT_WIDTH = 300;
	public static final Color DEFAULT_BACKGROUND = Color.WHITE;
	
	private final LinkedList<BouncingBall> balls = new LinkedList<BouncingBall>();
	
	public BallArea() {
		setBackground(DEFAULT_BACKGROUND);
		setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (BouncingBall b : balls) {
			b.paintIn(g);
		}

		g.dispose();
	}		
	
	public void addBall(BouncingBall b) {
		balls.addLast(b);
		repaint();
	}
	
	public void removeABall() {
		if (!balls.isEmpty()) {
			BouncingBall b = balls.removeFirst();
			b.stopBouncing();
			repaint();
		}
	}
}