package Proyecto_ADA;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        int Size = 10;
        Tablero t = new Tablero(Size);

        t.Imprimir();
    }

    public static int generarRandom(Random random){
        return random.nextInt(9)+1;
    }

    public void  Apuntar(){
        Random r = new Random();
        int X = generarRandom(r);
        int Y = generarRandom(r);

    }

    public void Disparando(){
        t.disparo
    }
}


