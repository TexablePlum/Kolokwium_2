module org.example.kolokwium_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.kolokwium_2 to javafx.fxml;
    exports org.example.kolokwium_2;
}