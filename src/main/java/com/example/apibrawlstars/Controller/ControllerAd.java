package com.example.apibrawlstars.Controller;


import com.example.apibrawlstars.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAd {

    @FXML
    private Button btnAdminSalir;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnClicBorrarUsuPnl;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnGuardarLista;

    @FXML
    private Button btnInfoApi;

    @FXML
    private Button btnListaUsuarios;

    @FXML
    private Button btnNuevoUsuario;

    @FXML
    private ComboBox<?> cmbRolAdminScene;

    @FXML
    private ImageView imgAdministrator;

    @FXML
    private AnchorPane infoAPI;

    @FXML
    private Label lblAdminName;

    @FXML
    private Label lblContra;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblRol;

    @FXML
    private ListView<?> lstViewAdmin1;

    @FXML
    private AnchorPane pnlScrollPaneAdmin;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtNombre;

    private ObservableList<String> listaUsuarios = FXCollections.observableArrayList();


    @FXML
    void onBtnCancelar(ActionEvent event) {

    }

    @FXML
    void onBtnClickBorrarUsuario(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/apibrawlstars/View/SceneBorrar.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onBtnClickCerrarInfo(ActionEvent event) {

    }

    @FXML
    void onBtnConfirmar(ActionEvent event) {

    }

    @FXML
    void onBtnGuardarLista(ActionEvent event) {

    }

    @FXML
    void onBtnInfoApiAdminScene(ActionEvent event) {

    }

    @FXML
    void onBtnListaUsuAdminScene(ActionEvent event) {

    }

    @FXML
    void onBtnNuevoUsuAdminScene(ActionEvent event)  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/apibrawlstars/View/SceneNuevo.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void onBtnSalirAdmin(ActionEvent event) {

    }

    @FXML
    void onSelect(ActionEvent event) {

    }

}

