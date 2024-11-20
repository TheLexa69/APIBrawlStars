package com.example.apibrawlstars.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.apibrawlstars.Model.SQLCommands;

import java.util.Optional;
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

        try {
            String hashedPassword = ControllerCredentials.hashPassword(contrasena);
            if (sqlCommands.registrarUsuario(nombre, hashedPassword)) {
                confirmationCode = generateConfirmationCode();
                sqlCommands.updateCodigoUsuario(confirmationCode, nombre, hashedPassword);//ACTUALIZAMOS EN LA BDD EL CODIGO PARA POSTERIORMENTE HACER LA LECTURA
                showConfirmationModal();
            } else {
                showAlert("Error", "No se pudo registrar el usuario. Intente nuevamente.");
            }
        } catch (Exception e) {
            showAlert("Error", "Ocurrió un error al procesar la contraseña.");
            e.printStackTrace();
        }
    }

    private String generateConfirmationCode() {
        Random rand = new Random();
        return String.format("%03d-%03d", rand.nextInt(1000), rand.nextInt(1000));
    }

    private void showConfirmationModal() {
        // Crear un diálogo personalizado
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Código de Confirmación");
        dialog.setHeaderText("Tu código de confirmación es: " + confirmationCode);

        // Campo de texto para ingresar el código
        TextField codeInput = new TextField();
        codeInput.setPromptText("Ingrese el código aquí");

        // Crear un contenedor para el campo de texto
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("Ingrese el código:"), codeInput);
        dialog.getDialogPane().setContent(vbox);

        // Botón de confirmar
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Establece el propietario del diálogo para que sea modal respecto al panel de registro
        dialog.initOwner(btnRegistrarse.getScene().getWindow());

        // Mostrar el diálogo y esperar la respuesta del usuario
        Optional<String> result = dialog.showAndWait();

        // Contador de intentos
        int intentos = 0;
        boolean codigoCorrecto = false;

        while (result.isPresent() && result.get() != null && intentos < 5) {
            String inputCode = codeInput.getText();

            if (inputCode.equals(confirmationCode)) {
                codigoCorrecto = true;
                break; // Código correcto
            } else {
                intentos++;
                if (intentos < 5) {
                    showAlert("Código incorrecto", "Código incorrecto, vuelva a intentar. Intento " + (intentos + 1));
                    // Reiniciar el campo de texto y volver a mostrar el diálogo
                    codeInput.clear();
                    result = dialog.showAndWait(); // Vuelve a mostrar el diálogo
                }
            }
        }

        // Si el usuario agotó los intentos
        if (intentos >= 5) {
            showAlert("Máximo de intentos alcanzado", "Has alcanzado el máximo de intentos. Vuelve a intentarlo en 30 segundos.");
            closeModal(); // Cierra el modal actual
            // Implementar lógica para deshabilitar el registro por 30 segundos, si es necesario
            disableRegistrationTemporarily();
        }

        // Si el código fue correcto
        if (codigoCorrecto) {
            activateUserInDatabase(txtNombre.getText(), confirmationCode);
        }
    }

    // Método para deshabilitar el registro temporalmente
    private void disableRegistrationTemporarily() {
        // Lógica para deshabilitar el registro (puedes implementar un temporizador aquí)
        btnRegistrarse.setDisable(true);

        new Thread(() -> {
            try {
                Thread.sleep(30000); // Espera 30 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Reactivar el botón de registro en el hilo de JavaFX
            Platform.runLater(() -> btnRegistrarse.setDisable(false));
        }).start();
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

        // Establece el propietario del diálogo para que sea modal
        alert.initOwner(btnRegistrarse.getScene().getWindow());

        alert.showAndWait(); // Muestra la alerta y espera a que el usuario la cierre
    }
}
