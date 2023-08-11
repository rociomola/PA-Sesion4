package ColoresDobles;


import java.awt.Color;
// La clase Paleta contiene los colores del parchís y los sirve a los pitores
public class Paleta {
    private Color [] colores = new Color[4];
    private boolean[] ocupado = new boolean[4];
    public Paleta(){
        colores[0]=new Color(128,0,0,255);
        colores[1]=new Color(128,0,128,255);
        colores[2]=new Color(0,0,128,255);
        colores[3]=new Color(128,128,0,255);
        for(int i=0;i<4;i++)ocupado[i]=false;
    }
    public synchronized Color tomaColor(int n){
        int i=n%4; //para evitar errores de índice, si n>3 se sustituye por su resto
        while(ocupado[i]){
            try{ wait(); } catch(InterruptedException e){} // mientras el color esté ocupado, espero
        }
        ocupado[i]=true;
        return colores[n%4];
    }
    public synchronized void dejaColor(int n){
        int i=n%4; //para evitar errores de índice, si n>3 se sustituye por su resto
        ocupado[i]=false;
        notifyAll();
    }
    public  Color mezclaColores(int x, int y){
        x=x%4; y=y%4;
        if(x==0 & y==1) return new Color(255,0,128,255);
        if(x==1 & y==2) return new Color(128,0,128,255);
        if(x==2 & y==3) return new Color(128,128,128,255);
        if(x==3 & y==0) return new Color(255,128,0,255);
        System.out.println("Mezcla de colores no permitida");
        return Color.white;
    }
}
