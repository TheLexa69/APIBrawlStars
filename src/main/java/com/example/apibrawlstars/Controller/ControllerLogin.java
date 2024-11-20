package com.example.apibrawlstars.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.apibrawlstars.Main;

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
}
