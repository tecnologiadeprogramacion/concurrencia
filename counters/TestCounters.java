package counters;

public class TestCounters { 

	public static void main(String[] args) {
		new CounterName(0, 50, 1, 100, "Contador A").start();
		new CounterName(0, -50, -1, 200, "Contador B:    ").start();
	}

}