package SIMPGORILLA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StartController {

    @FXML
    private TextField TextSizeX, TextSizeY;
    public static int sizeX,sizeY;
    private int minX = 200, minY = 200;

    public void startGame(ActionEvent actionEvent) throws IOException {
        setupStageSize();
        if(sizeX >= minX && sizeY >= minY){
            GameApplication.setStage("game-view.fxml",sizeX,sizeY);
        }
    }

    public void setupStageSize(){
        try{
            sizeX = Integer.parseInt(TextSizeX.getText());
            sizeY = Integer.parseInt(TextSizeY.getText());
        } catch (Exception e){
            if (TextSizeX.getText().length() == 0){
                sizeX = 600;
            }

            if(TextSizeY.getText().length() == 0){
                sizeY = 400;
            }
        }
    }
}
