package SIMPGORILLA;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class GameController  {
    private static final int CANVAS_X = 100;
    private static Player player1 = new Player(0, 0, true);
    private static Player player2 = new Player(CANVAS_X - 1, 0, false);

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
            Data.simulateProjectileWithTime(player1, player2, numangle, numvelocity);

            System.out.println(projectile.getCenterX());
            AnimationTimer animation = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    projectile.setCenterX(50);
                    projectile.setCenterY(50);
                }
            };
            animation.start();
            System.out.println(projectile.getCenterX());
            animation.stop();
        }
    }
}
