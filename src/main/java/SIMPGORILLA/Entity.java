package SIMPGORILLA;


public class Entity {

    private double x;
    private double y;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;

    }

    //retunerer x-værdi
    public double getX() {
        return this.x;
    }

    //retunerer y-værdi
    public double getY() {
        return this.y;
    }

    //sætter x værdi
    public void setX(double x) {
        this.x = x;
    }

    //sætter y værdi
    public void setY(double y) {
        this.y = y;
    }

    //Entity toString metode
    public String toString(){
        return "x:"+ round(this.x) + " y:" + round(this.y);
    }

    //afrunder en double til to decimaler
    public String round(double a){
        return String.format("%.2f",a);
    }
}

