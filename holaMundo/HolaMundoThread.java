public class HolaMundoThread extends Thread {

    public void run() {
        System.out.println("Hola gente de TdP, saludo desde clase de tipo Thread!");
    }

    // uso de la clase
	public static void main(String args[]) {
        (new HolaMundoThread()).start();
    }
}
