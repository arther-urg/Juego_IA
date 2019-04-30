

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes{
    
    
    //nuestra clase se compone de un lienzo de dibujo (herada de canvas)
    public Lienzo lienzo;
    
    //constructor
    public VentanaPrincipal() {
        lienzo=new Lienzo();
        this.getContentPane().add(lienzo);
        //el tamaño de la venta es la del escenario y el incremento de los bordes
        this.setSize(lienzo.getWidth(),lienzo.getHeight()); 
    }
    
}
