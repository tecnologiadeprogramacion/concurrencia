package edu.caltech.cs47;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.SwingUtilities;

/**
 * An active object representing an animation of a "ball", i.e. a filled circle.
 * A BouncingBall bounces around in a BallArea component.
 * @author joev
 *
 */
final class BouncingBall extends Thread {
	//	final class BouncingBall implements Runnable {

	// Constants
	public static final int DEFAULT_SIZE = 30;
	public static final Color DEFAULT_COLOR = Color.BLUE;
	public static final double DEFAULT_SPEED_X = 210;
	public static final double DEFAULT_SPEED_Y = 90;
	public static final long FRAME_DELAY = 33;

	
	private BallArea area;
	private int size = DEFAULT_SIZE;
	private Color color = DEFAULT_COLOR;

	private double ballX = 0, ballY = 0;
	private double speedX = DEFAULT_SPEED_X;
	private double speedY = DEFAULT_SPEED_Y;

	private volatile boolean stillBouncing = true;

	public BouncingBall(BallArea area, double ballX, double ballY) {
		if (area == null) {
			throw new NullPointerException();
		}
		this.area = area;
		this.ballX = ballX;
		this.ballY = ballY;
		area.addBall(this);
		//			new Thread(this).start();
		start();
	}

	public void paintIn(Graphics g0) {
		Graphics g = g0.create();
		g.setColor(color);
		g.fillOval((int) ballX, (int) ballY, size, size);
		g.dispose();
	}

	public void run() {
		long lastTime = System.currentTimeMillis();
		while (stillBouncing) {
			try {
				Thread.sleep(FRAME_DELAY);
			} catch (InterruptedException e) {
				// Whatever.
			}
			long currentTime = System.currentTimeMillis();
			long elapsedTime = currentTime - lastTime;
			lastTime = currentTime;
			double dx = (speedX * elapsedTime) / 1000;
			double dy = (speedY * elapsedTime) / 1000;
			ballX += dx;
			ballY += dy;
			if (ballX < 0) {
				ballX = -ballX;
				speedX = -speedX;
			}
			int maxX = area.getWidth() - size;
			if (ballX > maxX) {
				ballX -= 2 * (ballX - maxX);
				speedX = -speedX;
			}
			if (ballY < 0) {
				ballY = -ballY;
				speedY = -speedY;
			}
			int maxY = area.getHeight() - size;
			if (ballY > maxY) {
				ballY -= 2 * (ballY - maxY);
				speedY = -speedY;
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					area.repaint();
				}
			});
		}
	}

	public void stopBouncing() {
		stillBouncing = false;
	}

}