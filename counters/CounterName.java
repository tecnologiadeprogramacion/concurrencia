package counters;

public class CounterName extends Thread {

    protected int count;
	protected int end;
    protected int inc;
    protected int delay;
	protected String nombre;

    public CounterName( int  init,  int end,   int  inc,  int  delay, String n ) {
        this.count = init;
		this.end = end;
        this.inc = inc;
        this.delay = delay;
		this.nombre = n;
    }


    public void run() {
        try {
			while(count != end){ 
                System.out.println(nombre+":"+count + " ");
                count += inc;
                sleep(delay);
            }
        } catch (InterruptedException e) {}
    }

  
}