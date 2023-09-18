package conexion;

public class Conectar{
	
	public static void main (String[] args){
		Thread t = new Thread(new BuscadorRed(Integer.parseInt(args[0])));
		Espera zz = new Espera();
		t.start();
		System.out.print("Esperando conexion...");
		while (t.isAlive()) {
			
			Thread tzz = new Thread(zz);
			tzz.start();
			try{
				t.join(15000); // Esperar 15 segundos como máximo
			} catch (InterruptedException e) {}
			zz.fin = true;
			if(t.isAlive()) {
				System.out.print("Error en conexion.");
				t.interrupt();
				try{
					t.join(); // Esperar lo poco que queda
				} catch (InterruptedException e) {}
			} else {
				System.out.print("CONECTADO!!");	
			}
			
		}
	}
	
}
