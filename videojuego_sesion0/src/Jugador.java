public class Jugador {
    //Atributos
    public int x;
    public int y;
    public int score;
    public int vida;
    //Constructor
    public Jugador(int x, int y){
        this.x = x;
        this.y = y;
    }
    //Getters
    public int getPosJX() {
        return x;
    }

    public int getPosJY() {
        return y;
    }
    
    //Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
