package com.example.apibrawlstars.Controller;

import com.example.apibrawlstars.Model.SQLCommands;
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

public class ControllerBorrar {

    @FXML
    private Button btnBorrar;

    @FXML
    private Label lblNameDelete;

    @FXML
    private TextField txtName;

    SQLCommands cmd = new SQLCommands();
    @FXML
    void onBtnBorrar(ActionEvent event) {
        String username = txtName.getText();
        cmd.deleteUserByName(username);
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

