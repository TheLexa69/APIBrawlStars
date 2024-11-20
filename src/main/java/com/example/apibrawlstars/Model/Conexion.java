package com.example.apibrawlstars.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String DATABASE = "bd_brawl";
    private final String HOSTNAME = "localhost";
    private final String PORT = "3306";
    private final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE;
    private final String USERNAME = "root";
    private final String PASSWORD = "root";



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
