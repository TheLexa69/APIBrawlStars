package com.example.apibrawlstars.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.apibrawlstars.Main;
<<<<<<< Updated upstream
=======
import javafx.stage.Modality;
import javafx.stage.Stage;
//import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
>>>>>>> Stashed changes

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

    @FXML
    void onBtnClickUsuarioLogin(ActionEvent event) {

    }

    @FXML
    void onBtnClickUsuarioRegistrarse(ActionEvent event) {

    }
    @FXML
    public void initialize() {
        try {
            String imagen = "/com/example/apibrawlstars/View/Images/Brawl_Stars_Update_Hub_Featured_45FCMOZ.png";  // Cambia esta ruta según tu imagen
            var imageStream = Main.class.getResourceAsStream(imagen);
            if (imageStream != null) {
                Image image = new Image(imageStream);
                imgBackgroundLogin.setImage(image);
            } else {
                System.out.println("No se encontró la imagen en la ruta: " + imagen);
            }
        } catch (Exception e) {
            System.out.println("Error en ControllerLogin.java: " + e.getMessage());
            e.printStackTrace();
        }
    }
<<<<<<< Updated upstream
}
=======

    private void redirectToIndex() {
        try {
            // Cargar el archivo FXML de la vista del índice
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/apibrawlstars/View/ViewIndex.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com/example/apibrawlstars/View/SceneAdmin.fxml"));

            Parent root = loader.load();

            // Obtener el Stage actual desde cualquier componente
            Stage currentStage = (Stage) btnUsuarioLogin.getScene().getWindow();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Pantalla Principal");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla principal.");
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
>>>>>>> Stashed changes
