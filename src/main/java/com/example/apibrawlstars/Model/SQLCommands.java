package com.example.apibrawlstars.Model;

import com.example.apibrawlstars.Controller.ControllerCredentials;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
    public void insertUser() {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar los datos al usuario
        System.out.print("Ingrese el nombre del usuario: ");
        String name = scanner.nextLine();  // Leer el nombre

        System.out.print("Ingrese la contraseña del usuario: ");
        String password = scanner.nextLine();  // Leer la contraseña

        System.out.print("Ingrese el rol del usuario (por ejemplo, 'admin' o 'user'): ");
        String role = scanner.nextLine();  // Leer el rol

        // Crear un objeto User con los datos ingresados
        User user = new User(name, password, role);


        // Consulta SQL para insertar un nuevo usuario
        String query = "INSERT INTO Users (name, password, role) VALUES (?, ?, ?)";

        // Establecer conexión con la base de datos y ejecutar la inserción
        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer los parámetros para el PreparedStatement
            stmt.setString(1, user.getUsername());  // Nombre del usuario
            stmt.setString(2, user.getPassword());  // Contraseña
            stmt.setString(3, user.getRole());      // Rol

            // Ejecutar la consulta
            int rowsAffected = stmt.executeUpdate();

            // Confirmar si la inserción fue exitosa
            if (rowsAffected > 0) {
                System.out.println("El usuario '" + user.getUsername() + "' ha sido insertado correctamente.");
            } else {
                System.out.println("Error al insertar el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Cerrar el scanner
        }
    }
    public void deleteUserByName() {
        // Usar Scanner para leer el nombre del usuario desde la consola
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese el nombre
        System.out.print("Ingresa el nombre del usuario a eliminar: ");
        String username = scanner.nextLine();  // Leer nombre del usuario

        // Consulta SQL para eliminar al usuario basado en su nombre
        String query = "DELETE FROM Users WHERE name = ?";

        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer el parámetro de la consulta (el nombre del usuario)
            stmt.setString(1, username);

            // Ejecutar la consulta
            int rowsAffected = stmt.executeUpdate();

            // Si la consulta afecta alguna fila, el usuario fue eliminado
            if (rowsAffected > 0) {
                System.out.println("El usuario '" + username + "' fue eliminado con éxito.");
            } else {
                System.out.println("No se pudo eliminar al usuario '" + username + "'.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el scanner después de usarlo
            scanner.close();
        }
    }
    public void getUsers() {
        // Consulta SQL para obtener name y password
        String query = "SELECT name, password FROM Users";

        try (Connection conn = conexion.conectarMySQL();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Iterar sobre los resultados
            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");

                // Aquí puedes hacer lo que necesites con los datos, por ejemplo, imprimirlos
                System.out.println("Name: " + name + ", Password: " + password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}