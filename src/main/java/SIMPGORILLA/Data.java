package SIMPGORILLA;


public class Data {

    private static final int CANVAS_X = 100;
    private static final int CANVAS_Y = 50;
    private static final double g = -9.81;

    public static void main(String[] args) {

        Player player1 = new Player(0, 0, true);
        Player player2 = new Player(CANVAS_X - 1, 0, false);



        simulateProjectile(player1, player2,45, 25);

    }

    public static void simulateProjectile(Player playerThatShoots, Player playThatIsHit, int a, double velocity) {
        double angle = Math.toRadians(a);
        double y = playerThatShoots.getY();
        double x = playerThatShoots.getX();

        while (pointIsInside(x,y)) {
            y = g / (2 * Math.pow(velocity, 2) * Math.pow(Math.cos(angle), 2)) * Math.pow(x, 2) + Math.tan(angle) * x;
            System.out.println("x:"+x+" y:"+y);

            if (playerIsHit(playThatIsHit, x, y)){
                System.out.println(" player is hit!");
                break;
            }
            x += 0.1;
        }

    }

    public static boolean pointIsInside(double x, double y) {
        return (x < CANVAS_X && x >= 0 && y >= 0);
    }

    public static boolean playerIsHit(Player player, double x, double y){

        x = x;
        y = y;

        int px =player.getX();
        int py =player.getY();

        double len =Math.abs(Math.sqrt(Math.pow(px-x,2))+Math.pow(py-y,2));
        System.out.print(" len:" + len);

        if(len < 50){
            return true;
        }
        return false;





    }


}

