package com.example.simpledbclient;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class DBTable {
    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty birthDate;
    private StringProperty gender;
    private StringProperty hireDate;

    public static String command = "";


    public DBTable(int id, String birthDate, String firstName, String lastName, String gender, String hireDate){
        this.id = new SimpleIntegerProperty(this, "id");
        this.birthDate = new SimpleStringProperty(this, "birthDate");
        this.firstName = new SimpleStringProperty(this, "firstName");
        this.lastName = new SimpleStringProperty(this, "lastName");
        this.gender = new SimpleStringProperty(this, "gender");
        this.hireDate = new SimpleStringProperty(this, "hireDate");

        this.setId(id);
        this.setBirthDate(birthDate);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.setHireDate(hireDate);
    }

    public DBTable() {

    }

    public int getId() { return id.get(); }

    public void setId(int number) { this.id.set(number); }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String value) {
        this.firstName.set(value);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String value) {
        this.lastName.set(value);
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(String _birthdate) {
        this.birthDate.set(_birthdate);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String value) {
        this.gender.set(value);
    }

    public String getHireDate() {
        return hireDate.get();
    }

    public void setHireDate(String value) {
        this.hireDate.set(value);
    }


    public static ObservableList<DBTable> getEmployees(){
        ObservableList<DBTable> ret_val = FXCollections.observableArrayList();

        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=112211&serverTimezone=UTC";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url);

            ResultSet rs;
            boolean w_prepared_stmt = false; // Change me to try prepared or regular statement

            if (w_prepared_stmt){
                // Way with prepared statement
                PreparedStatement stmt = conn.prepareStatement("select first_name, birth_date from employees where first_name = ?");
                stmt.setString(1, "Elvis");
                rs = stmt.executeQuery();
            } else {
                // Way with regular statement
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(command);
            }

            while(rs!=null && rs.next()){
                int id = rs.getInt(1);
                String birthDate = rs.getDate(2).toString();
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String gender = rs.getString(5);
                String hireName = rs.getDate(6).toString();
                ret_val.add(new DBTable(id, birthDate, firstName, lastName, gender, hireName));
            }

        } catch (SQLException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Your input is not a valid MySQL query command.");
            error.show();
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret_val;
    }
}