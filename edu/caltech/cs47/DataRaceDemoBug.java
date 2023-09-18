package edu.caltech.cs47;

/**
 * The classic demonstration of a data race.  One thread increments a shared variable N times, 
 * another decrements it N times.  You'd expect the end result to be zero, but with high probability
 * it's not, because the increment and decrement operations are not atomic.
 * 
 * Solve the problem by wrapping the body of each for loop with syncrhonized (DataRaceDemo.this) {...} .
 * 
 * (If the problem doesn't happen for you, increase the value of TIMES.)
 * 
 * @author joev
 *
 */
public class DataRaceDemoBug {

	static final int TIMES = 10000000;
	
	private int counter;
	
	private class Incrementer extends Thread {
		public void run() {
			for ( int i = 0; i < TIMES; i++ ) {
				counter += 10;
			}
		}
	}
	
	private class Decrementer extends Thread {
		public void run() {
			for ( int i = 0; i < TIMES; i++ ) {
				counter -= 10;
			}
		}
	}
	
	public DataRaceDemoBug() {
		
		counter = 0;
		Thread inc = new Incrementer();
		Thread dec = new Decrementer();
		
		inc.start();
		dec.start();
		try {
			inc.join();
			dec.join();
		} catch (InterruptedException e) {
		}
		System.out.println("Final value: " + counter);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new DataRaceDemoBug();
		
	}

}
