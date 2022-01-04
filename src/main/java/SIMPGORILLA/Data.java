package SIMPGORILLA;


public class Data {
    //    private static final int VELOCITY = 25;
//    private static final int ANGLE_IN_DEGREES = 45;

    private static final int CANVAS_X = 100;
    private static final int CANVAS_Y = 50;
    private static final double g = 9.81;
    private static final int STEPS = 100;

    private static Player player1 = new Player(0, 0, true);
    private static Player player2 = new Player(CANVAS_X - 1, 0, false);
    private static Player proj = new Player(0,0,false);

    public static void main(String[] args) {
        simulateProjectileWithTime(player1, player2, 45, 25);

        //System.out.println();
        //simulateProjectile(player1, player2);
    }

    public static void simulateProjectileWithTime(Player shootingPlayer, Player targetPlayer, double ANGLE_IN_DEGREES, double VELOCITY){
        double angle = Math.toRadians(ANGLE_IN_DEGREES);
        double xVelocity = VELOCITY * Math.cos(angle);
        double yVelocity = VELOCITY * Math.sin(angle);
        double totalTime = - 2.0 * yVelocity / -g;
        double timeIncrement = totalTime / STEPS;
        double xIncrement = xVelocity * timeIncrement;

        double x = shootingPlayer.getX();
        double y = shootingPlayer.getY();
        double t = 0.0;
        System.out.println("step\tx \t y \t time \t length");
        System.out.println("0\t0.0\t\t0.0\t\t0.0");
        for (int i = 1; i <= STEPS; i++) {
            t += timeIncrement;
            x += xIncrement;
            y = yVelocity * t + 0.5 * -g * t * t;
            proj.setX(x);
            proj.setY(y);

            double l = player2.distanceToProjectile(proj.getX(), proj.getY());

            System.out.println(i + "\t" + round(x) + "\t" + round(y) + "\t" + round(t) + "\t" + round(l));

            if (playerIsHit(targetPlayer)){
                //System.out.println("Player is hit!");
                //break;
            }
        }
    }

    public static String round(double a){
        return String.format("%.2f",a);
    }

//    public static void simulateProjectile(Player shootingPlayer, Player targetPlayer) {
//        double angle = Math.toRadians(ANGLE_IN_DEGREES);
//        proj.setY(shootingPlayer.getY());
//        proj.setX(shootingPlayer.getX());
//
//        while (projIsInside(proj.getX(),proj.getY())) {
//            proj.setY(-g / (2 * Math.pow(VELOCITY, 2) * Math.pow(Math.cos(angle), 2)) * Math.pow(proj.getX(), 2) + Math.tan(angle) * proj.getX());
//            System.out.println(proj.toString() +  " length:" + round(targetPlayer.distanceToProjectile(proj.getX(),proj.getY())));
//
//            if (playerIsHit(targetPlayer)){
//                //System.out.println(" player is hit!");
//                //break;
//            }
//            proj.addToX(0.1);
//        }
//        System.out.println("time: " + returnTime() + " s");
//    }

//    public static double returnTime(){
//        return (2*VELOCITY*Math.sin(Math.toRadians(ANGLE_IN_DEGREES)))/g;
//    }

    public static boolean projIsInside(double x, double y) {
        return (x < CANVAS_X && x >= 0 && y >= 0);
    }

    public static boolean playerIsHit(Player player){
        double len = player.distanceToProjectile(proj.getX(), proj.getY());
        //System.out.print("len:" + round(len) + " ");
        return len < 50;
    }





}

