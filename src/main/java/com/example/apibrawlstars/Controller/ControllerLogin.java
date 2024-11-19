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
            /*System.out.println(Main.class.getResourceAsStream("resources/com/example/apibrawlstars/View/Images/Brawl-Stars-logo.png"));
            Image image = new Image(Main.class.getResourceAsStream("resources/com/example/apibrawlstars/View/Images/Brawl-Stars-logo.png"));
            if (image != null) {
                System.out.println(image);
            } else {
                System.out.println(image + "is null");
            }*/
        } catch (Exception e) {
            System.out.println("Mensaje del ControllerLogin.java: " + e.getMessage());
        }
    }

}
