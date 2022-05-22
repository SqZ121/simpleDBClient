package com.example.simpledbclient;

import java.sql.*;

public class DBTable {
    public static Connection conn;
    public static Statement stmt;

    public static void dbConnection() {
        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=112211&serverTimezone=UTC";
        conn = null;
        try {
            conn = DriverManager.getConnection(conn_url);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}