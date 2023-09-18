package conexion;

public class BuscadorRed implements Runnable{

	private int demora;
	
	public BuscadorRed(int d){
		demora = d;
	}
	
	public void run(){
		try{
			Thread.sleep(demora*1000);
		} catch (InterruptedException e) {}
	}	
	

}