package edu.caltech.cs47;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * Demo in which a thread other than the GUI event thread creates BouncingBalls,
 * raising the possibility that one will be created while the BallArea's
 * paintComponent method is iterating over its list of balls.  
 * This will raise a ConcurrentModificationException each time it happens.
 * 
 * To fix, make the paintComponent and addBall methods in BallArea synchronized, or 
 * wrap (a) the loop in paintComponent and (b) the add to the linked list in addBall
 * with synchronized blocks to make them mutually exclusive.
 * 
 * @author joev
 *
 */
public class BouncingBallSynchDemo implements Runnable {

	private final class BallCreator implements Runnable {
		
		private static final int DELAY = 150;
		
		private volatile boolean creating = true;
		
		public BallCreator() {
			new Thread(this).start();
		}
			
		public void run() {
			while (creating) {
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
				}
				new BouncingBall(ba,0,0);
			}
		}
		
		public void stopCreating() {
			creating = false;
		}
		
	}
	
	private final BallArea ba = new BallArea();
	private BallCreator creator;

	public void run() {
		
		JFrame f = new JFrame("Bouncing Ball Synchronization");
		
		Container cp = f.getContentPane();
		cp.add(BorderLayout.CENTER,ba);
		
		JPanel p = new JPanel();
		final JButton b = new JButton("Start");
		p.add(b);
		cp.add(BorderLayout.SOUTH,p);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (creator == null) {
					b.setText("Stop");
					creator = new BallCreator();
				} else {
					creator.stopCreating();
					creator = null;
					b.setText("Start");
				}
			}
		});
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new BouncingBallSynchDemo());
		
	}
}