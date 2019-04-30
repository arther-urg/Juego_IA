import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Escenario extends JComponent implements Constantes {
    
    public Celda[][] celdas;
    public Jugador jugador;
    public int numeroRecompensas = NUMERO_RECOMPENSAS;
        
    public Escenario() {
        celdas=new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
        
        //inicializar el array de celdas
        for(int i=0; i < NUMERO_CELDAS_ANCHO; i++)
            for (int j=0 ; j < NUMERO_CELDAS_LARGO ; j++)
                celdas[i][j]=new Celda((i*PIXEL_CELDA+16)+i, (j*PIXEL_CELDA+25)+j, 'V');
        
        //Colocar Jugador:
        int posJX = randomValue(0, NUMERO_CELDAS_ANCHO-1);
        int posJY = randomValue(0, NUMERO_CELDAS_LARGO-1);
        //Si Posicion actual esta vacia, entonces colocamos al jugador
        if(celdas[posJX][posJY].tipo == 'V')
        { 
            celdas[posJX][posJY].tipo = 'J';
            jugador = new Jugador(posJX, posJY);
        }
        
        insertEnemies();       
        insertObstaculos();
        insertRecompensas();      
        
       //Colocar final:
        int posFX = randomValue(0, NUMERO_CELDAS_ANCHO-1);
        int posFY = randomValue(0, NUMERO_CELDAS_LARGO-1);
        if(celdas[posFX][posFY].tipo == 'V') celdas[posFX][posFY].tipo = 'F';
    }
    
    private void insertEnemies(){
        int i=0;
        while(i < NUMERO_ENEMIGOS){
            int posX = randomValue(0, NUMERO_CELDAS_ANCHO-1);
            int posY = randomValue(0, NUMERO_CELDAS_LARGO-1);
            
            //COLOCAR EN CASO DE QUE SE ENCUENTRE OCUPADO
            if(celdas[posX][posY].tipo == 'V')
            { 
                celdas[posX][posY].tipo = 'E';
                i++;
            }
        }
    }
    
    private void insertObstaculos(){
        int i=0;
        while(i < NUMERO_OBSTACULOS){
            int posX = randomValue(0, NUMERO_CELDAS_ANCHO-1);
            int posY = randomValue(0, NUMERO_CELDAS_LARGO-1);
            
            //COLOCAR EN CASO DE QUE SE ENCUENTRE OCUPADO
            if(celdas[posX][posY].tipo == 'V')
            { 
                celdas[posX][posY].tipo = 'O';
                i++;
            }
        }
    }
    
    private void insertRecompensas(){
        int i=0;
        while(i < NUMERO_OBSTACULOS){
            int posX = randomValue(0, NUMERO_CELDAS_ANCHO-1);
            int posY = randomValue(0, NUMERO_CELDAS_LARGO-1);
            
            //COLOCAR EN CASO DE QUE SE ENCUENTRE OCUPADO
            if(celdas[posX][posY].tipo == 'V')
            { 
                celdas[posX][posY].tipo = 'R';
                i++;
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        update(g);
        for(int i=0; i < NUMERO_CELDAS_ANCHO ; i++){ 
            for ( int j=0 ; j < NUMERO_CELDAS_LARGO; j++){ 
              celdas[i][j].paintComponent(g);
            }
        }
        //Para dibujar un rectangulo que borre el marcador anterior
        g.setColor(Color.yellow);
        g.fillRect(5, 5, ANCHURA_ESCENARIO, 17);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.drawString("Puntuacion: " + jugador.score, 15, 20);   
    }
    
    

}