module SIMPGORILLA.gorillagame {
    requires javafx.controls;
    requires javafx.fxml;


    opens SIMPGORILLA to javafx.fxml;
    exports SIMPGORILLA;
}