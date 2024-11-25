package com.example.apibrawlstars.Controller;

import com.example.apibrawlstars.Model.SQLCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    }

}

