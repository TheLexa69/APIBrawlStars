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
            // Ruta de la imagen que quieres cargar
            String imagen = "/com/example/apibrawlstars/View/Images/Brawl-Stars-logo.png";  // Cambia esta ruta según tu imagen

            // Imprime la ruta para asegurarnos de que es correcta
            System.out.println("Ruta de la imagen: " + imagen);

            // Intenta cargar la imagen desde el directorio de recursos
            var imageStream = Main.class.getResourceAsStream(imagen);
            if (imageStream != null) {
                System.out.println("Imagen encontrada, cargando...");
                Image image = new Image(imageStream);
                imgLogoBrawl.setImage(image);  // Asigna la imagen al ImageView
            } else {
                System.out.println("No se encontró la imagen en la ruta: " + imagen);
            }
        } catch (Exception e) {
            System.out.println("Error en ControllerLogin.java: " + e.getMessage());
            e.printStackTrace();  // Para depurar el error de manera más detallada
        }
    }
}
