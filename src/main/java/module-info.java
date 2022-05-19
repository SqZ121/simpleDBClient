module com.example.simpledbclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.simpledbclient to javafx.fxml;
    exports com.example.simpledbclient;
}