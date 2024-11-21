package com.example.apibrawlstars.Model;

import com.example.apibrawlstars.Controller.ControllerCredentials;
import javafx.scene.control.Alert;

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
        String sql = "INSERT INTO Users (name, password, is_active) VALUES (?, ?, FALSE)";
        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, passwordHash);
            return pstmt.executeUpdate() > 0; // Retorna true si se registró correctamente
        } catch (SQLException e) {
            /*if (e.getErrorCode() == 1062) { // Código de error para entrada duplicada
                System.out.println("El nombre de usuario ya existe. Por favor, elija otro.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Registro");
                alert.setHeaderText(null);
                alert.setContentText("El nombre de usuario ya existe. Por favor, elija otro.");
                alert.showAndWait();
            } else {
                System.out.println("Error en SQLCommands registrarUsuario(): " + e.getMessage());
            }*/
            System.out.println("Error no SQLCommands registrarUsuario()" + e.getMessage());
            return false;
        }
    }

    public boolean updateCodigoUsuario(String codigo, String name, String hashedPassword) {
        String sql = "UPDATE Users SET confirmation_code = ? WHERE name = ? AND password = ?";
        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setString(2, name);
            pstmt.setString(3, hashedPassword);
            return pstmt.executeUpdate() > 0; // Retorna true si se actualizó correctamente
        } catch (SQLException e) {
            System.out.println("Error no SQLCommands updateCodigoUsuario()" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean activarUsuario(String nombre, String codigo) {
        // Suponiendo que tienes una columna `confirmation_code` en tu tabla
        String sql = "UPDATE Users SET is_active = TRUE WHERE name = ? AND confirmation_code = ?";
        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, codigo);
            return pstmt.executeUpdate() > 0; // Retorna true si se activó correctamente
        } catch (SQLException e) {
            System.out.println("Error no SQLCommands activarUsuario()" + e.getMessage());
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