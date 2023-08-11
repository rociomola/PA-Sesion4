package ColoresDobles;
// Pintor es un hilo que hace que el boton cambie de color siguiendo la secuencia:
// se pinta de negro durante un tiempo aleatorio que varía entre un mínimo de 0.2 y un máximo de 0,5 segundos.
// A continuación obtiene el primer color que le corresponde y pinta con ese color
// Espera entre 0.5 y 1 segundos
// Toma el segundo color, lo mezcla con el primero y pinta con la mezcla
// Espera entre 0.3 y 0.8 seg. y vuelve a empezar el ciclo.
import java.awt.Color;
import javax.swing.JButton;
public class Pintor extends Thread {
    private JButton b;
    private Paleta p;
    private int numPintor;
    private Color negro = Color.black;
    public Pintor(JButton b, Paleta p, int numPintor){
        this.b=b;
        this.p=p;
        this.numPintor = numPintor;
        start();        //Al mismo tiempo que se crea el hilo, comienza su ejecución.
    }
    public void run(){
          while (true){
            b.setBackground(negro);             //Pinta el botón de negro
            try {
                sleep((int)(200*Math.random()));  //Espera entre 0.2 y 0,5 seg.
            } catch (InterruptedException e){}
            Color uno = p.tomaColor(numPintor);     //Toma de la paleta elprimer color
            b.setBackground(uno);                   //y pinta el botón
            try {
                sleep((int)(50*Math.random()));  //Espera entre 0.5 y 1 seg.
            } catch (InterruptedException e){}
            Color dos = p.tomaColor(numPintor+1);     //Toma de la paleta el segundo color
            Color mezcla = p.mezclaColores(numPintor, numPintor+1); //los mezcla
            b.setBackground(uno);                   //y pinta el botón
            try {
                sleep((int)(100*Math.random()));  //Espera entre 0.3 y 0.8 seg.
            } catch (InterruptedException e){}
            p.dejaColor(numPintor);
            p.dejaColor(numPintor+1);
        }
    }
}