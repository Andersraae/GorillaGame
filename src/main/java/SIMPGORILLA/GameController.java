package SIMPGORILLA;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class GameController  {
    private static final int CANVAS_X = 600;
    private static Player player1 = new Player(0, 0, true);
    private static Player player2 = new Player(CANVAS_X - 1, 0, false);
    private static Player proj = new Player(0,0,false);
    private static final double g = 9.81;
    private static final int STEPS = 100;

    @FXML
    private Circle projectile;
    @FXML
    private TextField angle;
    @FXML
    private TextField velocity;


    public void kast(){
        if(angle.getText() != null && velocity.getText() != null){
            double numangle = Double.parseDouble(angle.getText());
            double numvelocity = Double.parseDouble(velocity.getText());
            simulateProjectileWithTime(player1, player2, numangle, numvelocity);
        }

    }

    public void simulateProjectileWithTime(Player shootingPlayer, Player targetPlayer, double ANGLE_IN_DEGREES, double VELOCITY){
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
            projectile.setCenterX(x);
            projectile.setCenterY(y);

            double l = player2.distanceToProjectile(proj.getX(), proj.getY());

            System.out.println(i + "\t" + round(x) + "\t" + round(y) + "\t" + round(t) + "\t" + round(l));
        }

        if (playerIsHit(player2)){
            System.out.println("Player is hit!");
        }
    }

    public static String round(double a){
        return String.format("%.2f",a);
    }

    public static boolean playerIsHit(Player player){
        double len = player.distanceToProjectile(proj.getX(), proj.getY());
        return len <= CANVAS_X/50;
    }
}
