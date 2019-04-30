import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {
    //atributos
    public int x;
    public int y;
    public char tipo;
    
    boolean celdaSeleccionada;
    
    public BufferedImage jugador, obstaculo, camino, enemigo, recompensa;
    
    private boolean usaImagen = true;
    
    //constructor, inicializa los atributos
    public Celda(int x,int y, char tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.celdaSeleccionada = false;
        
        try {
            jugador = ImageIO.read(new File("img/jugador.png"));
            //obstaculo = ImageIO.read(new File("img/obstaculo.png"));
            camino = ImageIO.read(new File("img/camino.png"));
            enemigo = ImageIO.read(new File("img/enemigo.png"));
            recompensa = ImageIO.read(new File("img/recompensa.png"));
        } catch (IOException error) {
            System.out.println("Error : " + error.toString());
        }
    }
    
    public boolean comprobarSiCeldaSeleccionada(int clickX, int clickY){
        Rectangle rectanguloCelda = new Rectangle(x,y, PIXEL_CELDA, PIXEL_CELDA);
        if(rectanguloCelda.contains(new Point(clickX, clickY)))
            celdaSeleccionada = !celdaSeleccionada;
        
        return celdaSeleccionada;
    }
    
    //metodo de JComponent para pintar en un contexto grafico
    @Override
    public void paintComponent(Graphics g) { 
        update(g);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        if(!usaImagen)
        {
            switch(tipo) {
            case 'J': g.setColor(COLOR_JUGADOR); break;
            case 'O': g.setColor(COLOR_OBSTACULO); break;
            case 'R': g.setColor(COLOR_RECOMPENSA); break;
            case 'E': g.setColor(COLOR_ENEMIGO); break;
            case 'V': g.setColor(COLOR_ESCENARIO); break;
            case 'F': g.setColor(COLOR_FINAL); break;
            }
            g.fillRect(x,y,PIXEL_CELDA,PIXEL_CELDA);
        
            //Switch para seleccionar nuevo color de contexto, y luego escribir letra asociada al cuadro.
            switch(tipo) {
                case 'J': g.setColor(Color.white); g.drawString("J",x+5,y+15); break;
                case 'O': g.setColor(Color.white); g.drawString("O",x+5,y+15); break;
                case 'R': g.setColor(Color.white); g.drawString("R",x+5,y+15); break;
                case 'E': g.setColor(Color.white); g.drawString("E",x+5,y+15); break;
                case 'F': g.setColor(Color.white); g.drawString("F",x+5,y+15); break;
            }

            if(celdaSeleccionada){
                g.setColor(COLOR_JUGADOR);
                g.fillRect(x, y, PIXEL_CELDA, PIXEL_CELDA);
            }
        }
        else
        {
            switch (tipo) {
                case 'J':
                    g.drawImage(jugador,x,y,null);
                    break;
                case 'O':
                    g.drawImage(obstaculo,x,y,null);
                    break;
                case 'V':
                    g.drawImage(camino,x,y,null);
                    break;
                case 'E':
                    g.drawImage(enemigo,x,y,null);
                    break;
                case 'R':
                    g.drawImage(recompensa,x,y,null);
                    break;
            }
        }
    }
}