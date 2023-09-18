public class BannerFrases {
 
public static void main(String args[]) throws InterruptedException {
        String frases[] = {
            "Cuanto cuesta un fin de semana gratis",
            "Solo queda una cerveza, y es de Bart",
            "A la grande le puse Cuca",
            "Lisa, mira detras de ti!"
        };

        for (int i = 0; i < frases.length; i++) {
            Thread.sleep(2500);
            System.out.println(frases[i]);
        }
    }
}
