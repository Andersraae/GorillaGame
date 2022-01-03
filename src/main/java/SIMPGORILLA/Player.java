package SIMPGORILLA;


public class Player {

    private int x;
    private int y;
    private boolean hasTurn;

    public Player(int x, int y, boolean hasTurn) {
        this.x = x;
        this.y = y;
        this.hasTurn = hasTurn;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean hasTurnNow() {
        return this.hasTurn;
    }

    public double lenthBetweenPlayerAndProjectile(double px, double py){
        return Math.abs(Math.sqrt(Math.pow(px-this.x,2))+Math.pow(py-this.y,2));
    }



}

