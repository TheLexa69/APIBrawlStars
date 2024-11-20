package com.example.apibrawlstars.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String DATABASE = "bd_brawl";
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";



    public Connection conectarMySQL() {
        Connection conn = null;
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void cerrarMySQL(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("Conexión cerrada correctamente.");
        } else {
            System.out.println("Conexión ya estaba cerrada o es nula.");
        }
    }
}
