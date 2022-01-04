package SIMPGORILLA;

public class Player extends Entity{
    protected String name;
    protected int point;

    public Player(int x, int y, String name){
        super(x,y);
        this.name = name;
        this.point = 0;
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

    public double distanceToProjectile(Projectile proj){
        return Math.abs(Math.sqrt(Math.pow(proj.getX()-this.getX(),2))+Math.pow(proj.getY()-this.getY(),2));
    }
}
