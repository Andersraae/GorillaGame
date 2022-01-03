package SIMPGORILLA;


public class Data {

    private static final int CANVAS_X = 100;
    private static final int CANVAS_Y = 50;
    private static final double g = -9.81;

    private static Player player1 = new Player(0, 0, true);
    private static Player player2 = new Player(CANVAS_X - 1, 0, false);
    private static Player proj = new Player(0,0,false);

    public static void main(String[] args) {
        simulateProjectile(player1, player2,45, 25);
    }

    public static void simulateProjectile(Player playerThatShoots, Player playThatIsHit, int a, double velocity) {
        double angle = Math.toRadians(a);
        proj.setY(playerThatShoots.getY());
        proj.setX(playerThatShoots.getX());

        while (projIsInside(proj.getX(),proj.getY())) {
            proj.setY(g / (2 * Math.pow(velocity, 2) * Math.pow(Math.cos(angle), 2)) * Math.pow(proj.getX(), 2) + Math.tan(angle) * proj.getX());
            System.out.println(proj.toString());

            if (playerIsHit(playThatIsHit)){
                System.out.println(" player is hit!");
                break;
            }
            proj.addToX(0.1);
        }
    }

    public static boolean projIsInside(double x, double y) {
        return (x < CANVAS_X && x >= 0 && y >= 0);
    }

    public static boolean playerIsHit(Player player){
        double len = player.distanceToProjectile(proj.getX(), proj.getY());
        System.out.print(" len:" + len);
        return len < 50;
    }


}

