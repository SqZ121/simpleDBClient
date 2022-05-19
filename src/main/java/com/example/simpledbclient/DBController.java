package com.example.simpledbclient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DBController implements Initializable {
   /* @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }*/

    @FXML
    private TextField textField;

    @FXML
    private Button button;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn col1;

    @FXML
    private TableColumn col2;

    @FXML
    protected void onbuttonClick() {
        col1.setText("test");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}