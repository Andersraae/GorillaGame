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

}

