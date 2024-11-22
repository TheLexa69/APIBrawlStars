package com.example.apibrawlstars;

import com.example.apibrawlstars.Model.DataApi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/SceneLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();

        DataApi dataApi = new DataApi();
        //dataApi.fetchAndInsertBrawlers();
        //dataApi.fetchAndInsertEvents();
    }

    public static void main(String[] args) {

        launch();

    }
}