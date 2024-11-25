package com.example.apibrawlstars.Controller;


import com.example.apibrawlstars.Model.SQLCommands;
import com.example.apibrawlstars.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerNuevo {

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label lblNamenew;

    @FXML
    private Label lblPasNew;

    @FXML
    private Label lblRolNew;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRol;
    SQLCommands cmd = new SQLCommands();

    @FXML
    void onClickBtnConfirmar(ActionEvent event) {
        User user = new User(txtName.getText(),txtPassword.getText(),txtRol.getText());
        cmd.insertUser(user);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/apibrawlstars/View/SceneAd.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
