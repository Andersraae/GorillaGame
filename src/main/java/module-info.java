module com.example.gorillagame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gorillagame to javafx.fxml;
    exports com.example.gorillagame;
}