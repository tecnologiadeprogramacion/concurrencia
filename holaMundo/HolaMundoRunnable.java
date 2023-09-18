public class HolaMundoRunnable implements Runnable {

    public void run() {
        System.out.println("Hola gente de TdP, saludo desde clase de tipo Runnable");
    }

   // uso de la clase
	public static void main(String args[]) {
        (new Thread(new HolaMundoRunnable())).start();
    }

}
