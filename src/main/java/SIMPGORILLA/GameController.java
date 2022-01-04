package SIMPGORILLA;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class GameController  {
    private static final int CANVAS_X = 600;
    private static Entity player1 = new Entity(0, 0, "p1");
    private static Entity player2 = new Entity(CANVAS_X - 1, 0, "p2");
    private static Entity proj = new Entity(0,0);
    private static final double g = 9.81;
    private static final int STEPS = 20;
    private boolean hasTurnP1 = true;

    public Label player1point;
    public Label player2point;
    @FXML
    private Circle projectile;
    @FXML
    private TextField angle;
    @FXML
    private TextField velocity;

    public void kast(){
        //player 1 har tur
        if (angle.getText() != null && velocity.getText() != null){
            double numangle = Double.parseDouble(angle.getText());
            double numvelocity = Double.parseDouble(velocity.getText());

            if(hasTurnP1){
                simulateProjectileWithTime(player1, player2, numangle, numvelocity);
            } else {
                //player 2 har tur
                simulateProjectileWithTime(player2, player1, -numangle, -numvelocity);
            }

            //Fjerner værdier fra sidste spiller
            angle.clear();
            velocity.clear();

        } //Der sker intet hvis der ikke er noget tekst i felterne
    }

    public void simulateProjectileWithTime(Entity shootingPlayer, Entity targetPlayer, double ANGLE_IN_DEGREES, double VELOCITY){
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

            double l = targetPlayer.distanceToProjectile(proj.getX(), proj.getY());
            System.out.println(i + "\t" + round(x) + "\t" + round(y) + "\t" + round(t) + "\t" + round(l));
        }

        System.out.println();

        if (playerIsHit(targetPlayer)){
            shootingPlayer.addPoint(1);
            System.out.println(targetPlayer.getName() + " is hit!");
            player1point.setText(Integer.toString(player1.getPoint()));
            player2point.setText(Integer.toString(player2.getPoint()));
        }

        //status på point
        System.out.println(player1.getName() + ":" + player1.getPoint());
        System.out.println(player2.getName() + ":" + player2.getPoint());

        //skifte tur
        if (hasTurnP1){
            hasTurnP1 = false;
        } else {
            hasTurnP1 = true;
        }

        System.out.println(targetPlayer.getName() + " har tur!");
    }

    public static String round(double a){
        return String.format("%.2f",a);
    }
    public static boolean playerIsHit(Entity player){
        double len = player.distanceToProjectile(proj.getX(), proj.getY());
        return len <= CANVAS_X/50;
    }
}
