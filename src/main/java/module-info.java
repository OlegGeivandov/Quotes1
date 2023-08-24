module com.example.quotes1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.quotes1 to javafx.fxml;
    exports com.example.quotes1;
    exports com.example.quotes1.model;
    opens com.example.quotes1.model to javafx.fxml;
}