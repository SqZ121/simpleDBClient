package com.example.simpledbclient;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class DBController {

    @FXML
    private TextField textField;

    @FXML // fx:id="tableView";
    TableView<DBTable> tableView;

    public void doLoad() {

        System.out.println(textField.getText());

        DBTable table = new DBTable();
        table.command = textField.getText();

        ObservableList<DBTable> values = DBTable.getEmployees();

        TableColumn<DBTable, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn<DBTable,String> firstName = new TableColumn<>("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory("firstName"));

        TableColumn<DBTable,String> lastName = new TableColumn<>("Last Name");
        lastName.setCellValueFactory(new PropertyValueFactory("lastName"));

        TableColumn<DBTable, String> birthDate = new TableColumn<>("Birthday Date");
        birthDate.setCellValueFactory(new PropertyValueFactory("birthDate"));

        TableColumn<DBTable,String> gender = new TableColumn<>("Gender");
        gender.setCellValueFactory(new PropertyValueFactory("gender"));

        TableColumn<DBTable,String> hireDate = new TableColumn<>("Hire Date");
        hireDate.setCellValueFactory(new PropertyValueFactory("hireDate"));

        tableView.getColumns().setAll(id, birthDate, firstName, lastName, gender, hireDate);
        tableView.setItems(values);

    }
}