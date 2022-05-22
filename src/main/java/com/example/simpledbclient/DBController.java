package com.example.simpledbclient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBController {

    @FXML
    private TextField textField;

    @FXML
    private TableView tableView;

    public void doLoad() {
        tableView.getItems().clear();
        tableView.getColumns().clear();

        try {
            DBTable.dbConnection();
            ResultSet rs = DBTable.stmt.executeQuery(textField.getText());

            // get columns dynamically according to input query
            for (int i = 0 ; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableView.getColumns().add(col);
            }

            ObservableList<ObservableList> res = FXCollections.observableArrayList();
            while (rs != null && rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i + 1));
                }
                res.add(row);
            }

            tableView.setItems(res);

        } catch (SQLException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Your input is not a valid MySQL query command.");
            error.show();
            e.printStackTrace();
        } finally {
            if (DBTable.conn != null) {
                try {
                    DBTable.conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}