import java.awt.Color;
import java.util.Random;
public interface Constantes {
    //Tomar la cantidad de pixeles de la resolucion actual
    int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    
    int ancho_ajustado = 1+(ancho-60)/64;
    int alto_ajustado = 1+(alto-100)/64;
    //size of the cells
    public final int PIXEL_CELDA=64;
    //number of cells - width
    public final int NUMERO_CELDAS_ANCHO= ancho_ajustado - 1;
    //number of cells - height
    public final int NUMERO_CELDAS_LARGO= alto_ajustado - 2;
    //size of the stage
    public final int ANCHO_BORDE_VENTANA=30;
    public final int LARGO_BORDE_VENTANA=50;
    
    public final int ANCHURA_ESCENARIO=(PIXEL_CELDA*NUMERO_CELDAS_ANCHO)+
            ANCHO_BORDE_VENTANA;
    public final int LARGO_ESCENARIO=(PIXEL_CELDA*NUMERO_CELDAS_LARGO)+
            LARGO_BORDE_VENTANA;
    
    //Cantidad de obstaculos, enemigos y recompensas
    public final int NUMERO_OBSTACULOS = (NUMERO_CELDAS_ANCHO * NUMERO_CELDAS_LARGO)/8;
    public final int NUMERO_ENEMIGOS = (NUMERO_CELDAS_ANCHO * NUMERO_CELDAS_LARGO)/12;
    public final int NUMERO_RECOMPENSAS = (NUMERO_CELDAS_ANCHO * NUMERO_CELDAS_LARGO)/16;
    
    //tipos de celdas
    public final char JUGADOR = 'J';
    public final char ENEMIGO = 'E';
    public final char RECOMPENSA = 'R';
    public final char OBSTACULO = 'O';
    public final char VACIO = 'V';
    //Colores de los tipos
    public final Color COLOR_JUGADOR = Color.green;
    public final Color COLOR_ENEMIGO = Color.red;
    public final Color COLOR_RECOMPENSA = Color.orange;
    public final Color COLOR_OBSTACULO = Color.black;
    public final Color COLOR_ESCENARIO=Color.DARK_GRAY;
    public final Color COLOR_FINAL=Color.MAGENTA;
    
    default int randomValue(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}