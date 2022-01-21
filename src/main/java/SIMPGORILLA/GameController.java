package SIMPGORILLA;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private static int sizeX = StartController.sizeX, sizeY = StartController.sizeY;
    private static Player player1 = new Player(0, 0, "p1"); // spiller 1 i venstre side
    private static Player player2 = new Player(sizeX- 1, 0, "p2"); //spiller 2 i højre side
    private static Projectile proj = new Projectile(0,0); //bananen
    private static final double g = 9.81; //konstant tyngdekraft
    private static final int totalSteps = sizeX; // antallet af punkter tegnes er størrelsen af x-aksen
    private boolean hasTurnP1 = true; // true når spiller 1 har tur, false når spiller 2 har tur
    private ArrayList<Circle> list = new ArrayList<>(); // liste til punkter til bananens kurve

    @FXML
    private AnchorPane screen;
    public Label player1point, player2point,LabelVinkel,LabelHastighed, LabelNamePlayer2;
    @FXML
    private Circle projectile;
    @FXML
    private TextField angle, velocity;
    @FXML
    private Rectangle RectanglePlayer1,RectanglePlayer2;
    @FXML
    private Button ButtonKast;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RectanglePlayer1.setWidth(sizeX/25);
        RectanglePlayer2.setWidth(sizeX/25);
        RectanglePlayer1.setHeight(sizeX/25);
        RectanglePlayer2.setHeight(sizeX/25);
        RectanglePlayer1.setX(0);
        RectanglePlayer1.setY(sizeY - RectanglePlayer1.getHeight());
        RectanglePlayer2.setX(sizeX - RectanglePlayer2.getWidth());
        RectanglePlayer2.setY(sizeY - RectanglePlayer2.getHeight());
        projectile.setCenterX(player1.getX() + projectile.getRadius());
        projectile.setCenterY(player1.getY() - projectile.getRadius() + sizeY);
        angle.setLayoutX(sizeX/2);
        velocity.setLayoutX(sizeX/2);
        ButtonKast.setLayoutX(sizeX/2);
        LabelVinkel.setLayoutX(angle.getLayoutX()-40);
        LabelHastighed.setLayoutX(velocity.getLayoutX()-60);
        LabelNamePlayer2.setLayoutX(sizeX-50);
        player2point.setLayoutX(sizeX-20);
    }

    //Anders
    //Kaldes når kast knap klikkes
    public void kast(){
        try {
            double numangle = Double.parseDouble(angle.getText());
            double numvelocity = Double.parseDouble(velocity.getText());

            if (numangle < 0 || numangle > 90) {
                System.out.println("Du skal vælge en vinkel mellem 0 og 90 grader");
            } else {
                //player 1 har tur
                if(hasTurnP1){
                    simulateProjectile(player1, player2, numangle, numvelocity);
                } else { //player 2 har tur
                    simulateProjectile(player2, player1, -numangle, -numvelocity);
                }
            }


            //Fjerner værdier fra sidste spiller
            angle.clear();
            velocity.clear();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    //Andreas
    //Metode tegner point der danner bananens kurve
    public Circle drawPoint(double x, double y){
        list.add(new Circle(x,-(y-sizeY),1));
        return list.get(list.size() - 1);
    }

    //Andreas
    //Metode fjerner de points der har dannet bananens kurve
    public void removePoints(){
        for (Circle c: list){
            screen.getChildren().remove(c);
        }
    }

    //Andreas
    public void simulateProjectile(Player shootingPlayer, Player targetPlayer, double ANGLE_IN_DEGREES, double VELOCITY){
        double angle = Math.toRadians(ANGLE_IN_DEGREES);
        double xVelocity = VELOCITY * Math.cos(angle);
        double yVelocity = VELOCITY * Math.sin(angle);
        double totalTime = - 2.0 * yVelocity / -g;
        double timeIncrement = totalTime / totalSteps;
        double xIncrement = xVelocity * timeIncrement;
        double x = shootingPlayer.getX();
        double y;
        double t = 0.0;
        int stepCounter;

        removePoints(); // fjerner tegnede punkter

        System.out.println("step\tx \t y \t time \t length");
        System.out.println("0\t0.0\t\t0.0\t\t0.0");

        for (stepCounter= 1; stepCounter <= totalSteps; stepCounter++) {
            t += timeIncrement; // øger tid
            x += xIncrement; // øger x
            y = yVelocity * t + 0.5 * -g * t * t; // beregner y værdi
            proj.setX(x); // sætter bananens x værdi
            proj.setY(y); // sætter bananens y værdi
            projectile.setCenterX(player1.getX() + x);
            projectile.setCenterY(player1.getY() - projectile.getRadius() + sizeY + y); //tegn banan
            screen.getChildren().add(drawPoint(x,y)); // tegn punkt
            double l = targetPlayer.distanceToProjectile(proj); //beregner længde mellem spiller og banan
            System.out.println(stepCounter + "\t" + round(x) + "\t" + round(y) + "\t" + round(t) + "\t" + round(l));
        }

        System.out.println();

        //giver point hvis spiller er ramt
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
            hasTurnP1 = false; // spiller 2 har tur
        } else {
            hasTurnP1 = true; //spiller 1 har tur
        }

        System.out.println(targetPlayer.getName() + " har tur!"); // printer til konsol hvis tur det er
    }

    //Andreas
    //afrunder en double til to decimaler
    public static String round(double a){
        return String.format("%.2f",a);
    }

    //Andreas
    // retunerer true når en spiller er ramt af banan
    public static boolean playerIsHit(Player player){
        double len = player.distanceToProjectile(proj);
        return len <= sizeX/50;
    }
}
