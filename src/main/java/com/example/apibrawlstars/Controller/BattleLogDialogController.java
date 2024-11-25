package com.example.apibrawlstars.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import com.example.apibrawlstars.Model.Battles;

public class BattleLogDialogController {

    @FXML
    private TableView<Battles> battleLogTable;
    @FXML
    private TableColumn<Battles, String> colBattleTime;
    @FXML
    private TableColumn<Battles, String> colMode;
    @FXML
    private TableColumn<Battles, Integer> colTrophyChange;
    @FXML
    private TableColumn<Battles, String> colResult;
    @FXML
    private TableColumn<Battles, Integer> colDuration;

    private ObservableList<Battles> battleLogs = FXCollections.observableArrayList();

    public void initialize() {
        // Configurar columnas
        colBattleTime.setCellValueFactory(new PropertyValueFactory<>("battleTime"));
        colMode.setCellValueFactory(new PropertyValueFactory<>("battleMode"));
        colTrophyChange.setCellValueFactory(new PropertyValueFactory<>("trophyChange"));
        colResult.setCellValueFactory(new PropertyValueFactory<>("result"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        // Asociar datos
        battleLogTable.setItems(battleLogs);
    }

    public void setBattleLogs(ObservableList<Battles> logs) {
        this.battleLogs.setAll(logs);
    }
}
