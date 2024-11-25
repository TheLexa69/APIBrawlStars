package com.example.apibrawlstars.Controller;

import com.example.apibrawlstars.Model.SQLCommands;
import com.example.apibrawlstars.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ControllerSelect {

    @FXML
    private TableColumn<?, ?> cmName;

    @FXML
    private TableColumn<?, ?> cmPassword;

    @FXML
    private TableColumn<?, ?> cmRol;

    @FXML
    private TableView<?> tblUsers;

    SQLCommands cmd = new SQLCommands();
   // private ArrayList<User> listUsers = cmd.getUsers();
    public void initialize() {
        cmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        cmRol.setCellValueFactory(new PropertyValueFactory<>("rol"));


        // tblUsers.setItems(cmd.getUsers());


    }

}

