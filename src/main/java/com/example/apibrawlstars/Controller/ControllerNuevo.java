package com.example.apibrawlstars.Controller;


import com.example.apibrawlstars.Model.SQLCommands;
import com.example.apibrawlstars.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    }

}
