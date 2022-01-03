module com.example.gorillagame {
    requires javafx.controls;
    requires javafx.fxml;


    opens example.gorillagame to javafx.fxml;
    exports example.gorillagame;
    exports SIMPGORILLA;
    opens SIMPGORILLA to javafx.fxml;
}