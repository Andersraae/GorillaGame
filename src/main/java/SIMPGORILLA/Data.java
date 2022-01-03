package SIMPGORILLA;


public class Data {

    private static final int CANVAS_X = 100;
    private static final int CANVAS_Y = 50;
    private static final double g = -9.81;

    public static void main(String[] args) {

        Player player1 = new Player(0, 0, true);
        Player player2 = new Player(CANVAS_X - 1, 0, false);



        simulateProjectile(player1, 45, 10);

    }

    public static void simulateProjectile(Player player, int a, double velocity) {
        double angle = Math.toRadians(a);
        double y = player.getY();
        double x = player.getX();

        while (isInside(x,y)) {
            y = g / (2 * Math.pow(velocity, 2) * Math.pow(Math.cos(angle), 2)) * Math.pow(x, 2) + Math.tan(angle) * x;
            x += 0.1;
            System.out.println("x:"+x+" y:"+y);
        }

    }

    public static boolean isInside(double x, double y) {
        return (x < CANVAS_X && x >= 0 && y >= 0);
    }

}

