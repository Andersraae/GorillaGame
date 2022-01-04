package SIMPGORILLA;


public class Player {

    private double x;
    private double y;
    private int point;
    private String name;
    public static int count = 1;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.point = 0;
        this.name = "Player" + count++;
    }

    public Player(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.point = 0;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


    public void addPoint(int n){
        this.point += n;
    }

    public int getPoint(){
        return this.point;
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

    public double distanceToProjectile(double px, double py){
        return Math.abs(Math.sqrt(Math.pow(px-this.x,2))+Math.pow(py-this.y,2));
    }

    public String toString(){
        return "x:"+ round(this.x) + " y:" + round(this.y);
    }

    public void addToX(double n){
        this.x += n;
    }

    public void addToY(double n){
        this.y += n;
    }

    public String round(double a){
        return String.format("%.2f",a);
    }

}

