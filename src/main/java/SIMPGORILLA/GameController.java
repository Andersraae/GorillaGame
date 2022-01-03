package SIMPGORILLA;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController{
    @FXML
    private Label btntext;

    @FXML
    protected void buttonPress(){
        btntext.setText("Welcome to JavaFX Application!");
    }
}
