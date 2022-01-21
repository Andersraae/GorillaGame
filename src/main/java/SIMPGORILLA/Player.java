package SIMPGORILLA;

public class Player extends Entity{
    protected String name; // spillerens navn
    protected int point; // spillerens antal point

    public Player(int x, int y, String name){
        super(x,y);
        this.name = name;
        this.point = 0; //point starter på 0
    }

    //retunerer spillerens navn
    public String getName(){
        return this.name;
    }

    //adderer point til en spiller
    public void addPoint(int n){
        this.point += n;
    }

    //retunerer spillerens point
    public int getPoint(){
        return this.point;
    }

    //retunerer spillerens afstand til bananen
    public double distanceToProjectile(Projectile proj){
        return Math.abs(Math.sqrt(Math.pow(proj.getX()-this.getX(),2))+Math.pow(proj.getY()-this.getY(),2));
    }
}
