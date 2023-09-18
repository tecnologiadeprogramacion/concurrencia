package edu.caltech.cs47;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * A demo of the BallArea and BouncingBall classes.  This demo is simple enough not
 * to require nontrivial synchronization -- all modifications to "shared" data structures
 * happen on the AWT event thread.
 * 
 * @author joev
 *
 */
public final class BouncingBallDemo implements Runnable {


	public void run() {

		JFrame f = new JFrame("Bouncing Ball!");
		
		final BallArea ba = new BallArea();
		f.getContentPane().add(BorderLayout.CENTER,ba);
		
		JPanel p = new JPanel();
		JButton b = new JButton("New");
		b.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				new BouncingBall(ba,0,0);
			}
		});
		p.add(b);
		
		b = new JButton("Kill");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ba.removeABall();
			}
		});
		p.add(b);
		f.getContentPane().add(BorderLayout.SOUTH,p);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new BouncingBallDemo());
	}
}