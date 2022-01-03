package SIMPGORILLA;


public class Player {

    private double x;
    private double y;
    private boolean hasTurn;

    public Player(double x, double y, boolean hasTurn) {
        this.x = x;
        this.y = y;
        this.hasTurn = hasTurn;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean hasTurnNow() {
        return this.hasTurn;
    }

    public double distanceToProjectile(double px, double py){
        return Math.abs(Math.sqrt(Math.pow(px-this.x,2))+Math.pow(py-this.y,2));
    }

    public String toString(){
        return "x:"+ this.x + " y:" + this.y;
    }

    public void addToX(double n){
        this.x += n;
    }

    public void addToY(double n){
        this.y += n;
    }

}

