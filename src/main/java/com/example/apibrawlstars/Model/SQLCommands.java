package com.example.apibrawlstars.Model;

import com.example.apibrawlstars.Controller.ControllerCredentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLCommands {

    private Conexion conexion;

    public SQLCommands() {
        this.conexion = new Conexion();
    }

    public boolean registrarUsuario(String nombre, String passwordHash) {
        String sql = "INSERT INTO Users (name, password, activado) VALUES (?, ?, FALSE)";
        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, passwordHash);
            return pstmt.executeUpdate() > 0; // Retorna true si se registró correctamente
        } catch (SQLException e) {
            System.out.println("Error no SQLCommands registrarUsuario()" + e.getMessage());
            return false;
        }
    }

    public boolean activarUsuario(String nombre, String codigo) {
        // Suponiendo que tienes una columna `confirmation_code` en tu tabla
        String sql = "UPDATE Users SET activado = TRUE WHERE name = ? AND confirmation_code = ?";
        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, codigo);
            return pstmt.executeUpdate() > 0; // Retorna true si se activó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String nombre, String password) {
        String sql = "SELECT password FROM Users WHERE name = ?";
        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                String hashedInputPassword = ControllerCredentials.hashPassword(password);
                return hashedInputPassword.equals(hashedPassword); // Verifica la contraseña
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Usuario no encontrado o contraseña incorrecta
    }
}