package conexion;

public class Espera implements Runnable{

	public boolean fin = false;
	
	public void run(){
		while(!fin){
			System.out.print(".");
			try{ Thread.sleep(500);} catch (InterruptedException e) {}
		}
		
	}

	

}