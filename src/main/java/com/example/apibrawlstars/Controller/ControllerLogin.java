package com.example.apibrawlstars.Controller;

import com.example.apibrawlstars.Model.SQLCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.apibrawlstars.Main;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;

public class ControllerLogin {

    @FXML
    private Button btnUsuarioLogin;

    @FXML
    private Button btnUsuarioRegistrarse;

    @FXML
    private ImageView imgBackgroundLogin;

    @FXML
    private ImageView imgLogoBrawl;

    @FXML
    private TextField txtUsuarioLogin;

    @FXML
    private PasswordField txtUsuarioPwd;

    private SQLCommands sqlCommands = new SQLCommands();

    @FXML
    void onBtnClickUsuarioLogin(ActionEvent event) {
        String username = txtUsuarioLogin.getText();
        String password = txtUsuarioPwd.getText();

        if (sqlCommands.authenticateUser(username, password)) {
            showAlert("Inicio de sesión exitoso", "Bienvenido, " + username + "!");
            //Falta redirigir al inicio logueado
        } else {
            showAlert("Error de inicio de sesión", "Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    void onBtnClickUsuarioRegistrarse(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/apibrawlstars/View/RegisterModal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Usuario");
            stage.setScene(new Scene(root));

            // Hacer que la ventana siempre esté encima
            stage.setAlwaysOnTop(true);

            // Hacer que la ventana sea modal
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait(); // Bloquea la interacción con otras ventanas
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo abrir el formulario de registro.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}