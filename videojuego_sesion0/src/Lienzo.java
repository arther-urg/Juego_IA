import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas implements Constantes{
   
    public Escenario escenario;
    public Celda[][] celdasE;
    public Jugador jugadorE;
    
    public Image fondo;
    
    public Lienzo(){
        escenario = new Escenario();
        try{
            fondo = ImageIO.read(new File("img/fondo.jpg"));
        }catch (IOException error){
            System.out.println("Error al cargar el fondo!!");
        }
        celdasE = escenario.celdas;
        jugadorE = escenario.jugador;
        
        this.setBackground(Color.yellow);
        this.setSize(ANCHURA_ESCENARIO,LARGO_ESCENARIO); 
        
        //Seleccionamos la celda del jugador
        celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].celdaSeleccionada = true;
        
        //Escuchador del mouse
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
                moverCeldaMouse(evt);
                repaint();
            }
        });   
        
        //Escuchador eventos del teclado
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            //identificar la tecla que se pulsó
            public void keyPressed(KeyEvent e){
                moverCelda(e);
                repaint();
            }
        });
    }
    
    private void moverCeldaMouse(MouseEvent evt){
        for(int i = 0; i < NUMERO_CELDAS_ANCHO; i++){
            for (int j=0 ; j < NUMERO_CELDAS_LARGO; j++)
                if(celdasE[i][j].comprobarSiCeldaSeleccionada(evt.getX(),evt.getY()))
                {
                    //Verificar existencia de obstaculo
                    if(celdasE[i][j].tipo != 'O')
                    {
                        //Verificar si hay recompensa
                        if(celdasE[i][j].tipo == 'R')
                        {
                            jugadorE.score++;
                            escenario.numeroRecompensas--;
                            if(escenario.numeroRecompensas == 0)
                            {
                                System.out.println("Has ganado! :) ");
                            }
                        }
                        //Verificar si hay un enemigo
                        if(celdasE[i][j].tipo == 'E')
                        {
                           jugadorE.vida--;
                           if(jugadorE.vida == 0)
                           {
                               System.out.println("Has perdido! :( ");
                           }
                        }
                        celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].celdaSeleccionada = false;
                        celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'V';
                        jugadorE.x = i;
                        jugadorE.y = j;
                        celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].celdaSeleccionada = true;
                        celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'J';
                    }
                }
        }
    }
    
    //Metodo que elimina el pestañeo en ejecucion
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public void moverCelda(KeyEvent evt){
        switch(evt.getKeyCode()){
            case KeyEvent.VK_UP:
                System.out.println("Mover arriba");
                moverCeldaArriba();
            break;
                
            case KeyEvent.VK_DOWN:
                System.out.println("Mover abajo");
                moverCeldaAbajo();
            break;
                
            case KeyEvent.VK_LEFT:
                System.out.println("Mover izquierda");
                moverCeldaIzquierda();
            break;
                
            case KeyEvent.VK_RIGHT:
                System.out.println("Mover derecha");
                moverCeldaDerecha();
            break;
        }
    }
    
    private void moverCeldaArriba(){
        if(jugadorE.getPosJY() > 0)
        {
            //Verificar si arriba del jugador no hay obstaculos
            if(celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()-1].tipo != 'O')
            {
                //Verificar si hay recompensa
                if(celdasE[jugadorE.getPosJX()][jugadorE.getPosJY() - 1].tipo == 'R')
                {
                    jugadorE.score++;
                    escenario.numeroRecompensas--;
                    System.out.println(escenario.numeroRecompensas);
                    if(escenario.numeroRecompensas == 0)
                    {
                        System.out.println("Has ganado! :) ");
                    }
                }
                //Verificar si hay un enemigo
                if(celdasE[jugadorE.getPosJX()][jugadorE.getPosJY() - 1].tipo == 'E')
                {
                    jugadorE.vida--;
                    if(jugadorE.vida == 0)
                    {
                        System.out.println("Has perdido! :( ");
                    }
                }
                
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = false;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'V';
                jugadorE.y = jugadorE.y - 1;
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = true;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'J';
            }
        }
    }
    
   private void moverCeldaAbajo(){
        if(jugadorE.getPosJY() + 1 < NUMERO_CELDAS_LARGO)
        {
            //Verificar si abajo del jugador no hay obstaculos
            if(celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()+1].tipo != 'O')
            {
                //Verificar si hay recompensa
                if(celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()+1].tipo == 'R')
                {
                    jugadorE.score++;
                    escenario.numeroRecompensas--;
                    System.out.println(escenario.numeroRecompensas);
                    if(escenario.numeroRecompensas == 0)
                    {
                        System.out.println("Has ganado! :) ");
                    }
                }
                //Verificar si hay un enemigo
                if(celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()+1].tipo == 'E')
                {
                    jugadorE.vida--;
                    if(jugadorE.vida == 0)
                    {
                        System.out.println("Has perdido! :( ");
                    }
                }
                
                
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = false;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'V';
                jugadorE.y = jugadorE.y + 1;
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = true;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'J';
            }
        }
    }
    
    private void moverCeldaIzquierda(){
        if(jugadorE.getPosJX() > 0)
        {
            //Verificar si a la izquierda del jugador no hay obstaculos
            if(celdasE[jugadorE.getPosJX()-1][jugadorE.getPosJY()].tipo != 'O')
            {
                //Verificar si hay recompensa
                if(celdasE[jugadorE.getPosJX()-1][jugadorE.getPosJY()].tipo == 'R')
                {
                    jugadorE.score++;
                    escenario.numeroRecompensas--;
                    System.out.println(escenario.numeroRecompensas);
                    if(escenario.numeroRecompensas == 0)
                    {
                        System.out.println("Has ganado! :) ");
                    }
                }
                //Verificar si hay un enemigo
                if(celdasE[jugadorE.getPosJX()-1][jugadorE.getPosJY()].tipo == 'E')
                {
                    jugadorE.vida--;
                    if(jugadorE.vida == 0)
                    {
                        System.out.println("Has perdido! :( ");
                    }
                }
                
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = false;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'V';
                jugadorE.x = jugadorE.x - 1;
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = true;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'J';
            }
        }
    }
    
    private void moverCeldaDerecha(){
        if(jugadorE.getPosJX() + 1 < NUMERO_CELDAS_ANCHO)
        {
            //Verificar si a la izquierda del jugador no hay obstaculos
            if(celdasE[jugadorE.getPosJX() + 1][jugadorE.getPosJY()].tipo != 'O')
            {
                //Verificar si hay recompensa
                if(celdasE[jugadorE.getPosJX()+1][jugadorE.getPosJY()].tipo == 'R')
                {
                    jugadorE.score++;
                    escenario.numeroRecompensas--;
                    System.out.println(escenario.numeroRecompensas);
                    if(escenario.numeroRecompensas == 0)
                    {
                        System.out.println("Has ganado! :) ");
                    }
                }
                //Verificar si hay un enemigo
                if(celdasE[jugadorE.getPosJX()+1][jugadorE.getPosJY()].tipo == 'E')
                {
                    jugadorE.vida--;
                    if(jugadorE.vida == 0)
                    {
                        System.out.println("Has perdido! :( ");
                    }
                }
                
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = false;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'V';
                jugadorE.x = jugadorE.x + 1;
                celdasE[jugadorE.x][jugadorE.y].celdaSeleccionada = true;
                celdasE[jugadorE.getPosJX()][jugadorE.getPosJY()].tipo = 'J';
            }
        }
    }
    
    //metodo paint para pintar el escnario
    @Override
    public void paint(Graphics g) {
       escenario.paintComponent(g); 
    }   
}