package com.example.apibrawlstars.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.apibrawlstars.Model.SQLCommands;
import org.mindrot.jbcrypt.*;

import java.util.Random;

public class ControllerRegisterModal {

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private PasswordField txtContrasenaConfirm;

    @FXML
    private Button btnRegistrarse;

    private String confirmationCode;
    private SQLCommands sqlCommands = new SQLCommands();

    @FXML
    void onBtnClickRegistrarse(ActionEvent event) {
        String nombre = txtNombre.getText();
        String contrasena = txtContrasena.getText();
        String contrasenaConfirm = txtContrasenaConfirm.getText();

        if (nombre.isEmpty() || contrasena.isEmpty() || contrasenaConfirm.isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos.");
            return;
        }

        if (!contrasena.equals(contrasenaConfirm)) {
            showAlert("Error", "Las contraseñas no coinciden.");
            return;
        }

        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());

        if (sqlCommands.registrarUsuario(nombre, hashedPassword)) {
            confirmationCode = generateConfirmationCode();
            showConfirmationModal();
        } else {
            showAlert("Error", "No se pudo registrar el usuario. Intente nuevamente.");
        }
    }

    private String generateConfirmationCode() {
        Random rand = new Random();
        return String.format("%03d-%03d", rand.nextInt(1000), rand.nextInt(1000));
    }

    private void showConfirmationModal() {
        Alert confirmationAlert = new Alert(AlertType.INFORMATION);
        confirmationAlert.setTitle("Código de Confirmación");
        confirmationAlert.setHeaderText("Tu código de confirmación es:");
        confirmationAlert.setContentText(confirmationCode);
        confirmationAlert.showAndWait();

        activateUserInDatabase(txtNombre.getText(), confirmationCode);
    }

    private void activateUserInDatabase(String nombre, String codigo) {
        if (sqlCommands.activarUsuario(nombre, codigo)) {
            showAlert("Éxito", "Usuario registrado y activado exitosamente. Ahora puedes iniciar sesión.");
            closeModal();
        } else {
            showAlert("Error", "No se pudo activar el usuario. Verifica el código de confirmación.");
        }
    }

    private void closeModal() {
        Stage stage = (Stage) btnRegistrarse.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
