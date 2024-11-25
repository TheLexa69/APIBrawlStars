package com.example.apibrawlstars.Controller;


import com.example.apibrawlstars.Model.Battles;
import com.example.apibrawlstars.Model.DataApi;
import com.example.apibrawlstars.Model.SQLCommands;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerViewIndex {

    @FXML
    private Button brawlBox;

    @FXML
    private Label brawlFont;

    @FXML
    private CheckBox chkLogPlayerTag;

    @FXML
    private Menu idBrawlersIndex;

    @FXML
    private Menu idBtnClickSalir;

    @FXML
    private Menu idClubsIndex;

    @FXML
    private Menu idEventosIndex;

    @FXML
    private Menu idJugadoresIndex;

    @FXML
    private AnchorPane idPnlBrawlersInfo;

    @FXML
    private AnchorPane idPnlClubsInfo;

    @FXML
    private AnchorPane idPnlEventosInfo;

    @FXML
    private AnchorPane idPnlJugadoresIndex;

    @FXML
    private Label lbl3vs3Victories;

    @FXML
    private Label lblClubName;

    @FXML
    private Label lblDuoVictories;

    @FXML
    private Label lblHighestTrophies;

    @FXML
    private Label lblNamePlayer;

    @FXML
    private Label lblSoloVictories;

    @FXML
    private Label lblTagClub;

    @FXML
    private Label lblTagPlayer;

    @FXML
    private Label lblTrophies;

    @FXML
    private AnchorPane pnlLogin;

    @FXML
    private TextField txtBusquedaPlayerTag;

    DataApi dataApi = new DataApi();
    SQLCommands sql = new SQLCommands();

    @FXML
    void onBtnClickBrawlers(ActionEvent event) {

    }

    @FXML
    void onBtnClickBuscarJugadores(ActionEvent event) {
        /*idPnlJugadoresIndex.setVisible(true);
        idClubsIndex.setVisible(false);
        idBrawlersIndex.setVisible(false);
        idEventosIndex.setVisible(false);*/

        String playerTag = txtBusquedaPlayerTag.getText();
        boolean isLogChecked = chkLogPlayerTag.isSelected();

        System.out.println("PlayerTag: " + playerTag);
        System.out.println("LogChecked: " + isLogChecked);

        if (sql.existsPlayer(playerTag)) {
            // Si el jugador existe en la BDD
            System.out.println(playerTag);

            System.out.println("El jugador existe.");
            System.out.println(sql.getPlayer(playerTag));
            System.out.println("getClub()" + sql.getClub(playerTag));

            lblNamePlayer.setText(sql.getPlayer(playerTag).getName());
            lblTagPlayer.setText(sql.getPlayer(playerTag).getTag());


            lblTrophies.setText(String.valueOf(sql.getPlayer(playerTag).getTrophies()));
            lblHighestTrophies.setText(String.valueOf(sql.getPlayer(playerTag).getHighestTrophies()));
            lblSoloVictories.setText(String.valueOf(sql.getPlayer(playerTag).getSoloVictories()));
            lblDuoVictories.setText(String.valueOf(sql.getPlayer(playerTag).getDuoVictories()));
            lbl3vs3Victories.setText(String.valueOf(sql.getPlayer(playerTag).getThreeVsThreeVictories()));

            if (sql.getClub(playerTag) == null) {
                lblClubName.setText("No tiene club");
                lblTagClub.setText("No tiene club");
            } else {
                lblClubName.setText(sql.getClub(playerTag).getNombre());
                lblTagClub.setText(sql.getClub(playerTag).getTag());
            }


            if (isLogChecked) {
                dataApi.fetchAndInsertBattleLog(playerTag);

                // Obtiene los datos desde la base de datos
                ObservableList<Battles> battles = FXCollections.observableArrayList(sql.getBattles(playerTag));

                // Abre el diálogo modal
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/apibrawlstars/View/BattleLogDialog.fxml"));
                    Parent root = loader.load();

                    // Configura el controlador
                    BattleLogDialogController controller = loader.getController();
                    controller.setBattleLogs(battles);

                    // Crear la escena y mostrar el diálogo modal
                    Stage stage = new Stage();
                    stage.setTitle("Battle Logs");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(true);
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            //EL JUGADOR NO EXISTE EN LA BDD HAY QUE BUSCARLO EN LA API
            System.out.println("El jugador no existe.");
            try {
                dataApi.fetchAndInsertPlayer(playerTag); //SE BUSCA Y SE INSERTA EN LA BDD

                System.out.println(sql.getPlayer(playerTag));//Pillamos los datos del jugador de la BDD

                lblNamePlayer.setText(sql.getPlayer(playerTag).getName());
                lblTagPlayer.setText(sql.getPlayer(playerTag).getTag());
                lblTrophies.setText(String.valueOf(sql.getPlayer(playerTag).getTrophies()));
                lblHighestTrophies.setText(String.valueOf(sql.getPlayer(playerTag).getHighestTrophies()));
                lblSoloVictories.setText(String.valueOf(sql.getPlayer(playerTag).getSoloVictories()));
                lblDuoVictories.setText(String.valueOf(sql.getPlayer(playerTag).getDuoVictories()));
                lblClubName.setText(sql.getPlayer(playerTag).getClubTag());
                lbl3vs3Victories.setText(String.valueOf(sql.getPlayer(playerTag).getThreeVsThreeVictories()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onBtnClickEventos(ActionEvent event) {

    }

    @FXML
    void onBtnClickClubs(ActionEvent event) {
        idPnlJugadoresIndex.setVisible(false);
        idBrawlersIndex.setVisible(false);
        idClubsIndex.setVisible(true);
        idEventosIndex.setVisible(false);
    }

    @FXML
    void onBtnClickJugadores(ActionEvent event) {
        //AQUI SE ENSEÑA EL PANEL DE JUGADORES
        idPnlJugadoresIndex.setVisible(true);
        idBrawlersIndex.setVisible(false);
        idClubsIndex.setVisible(false);
        idEventosIndex.setVisible(false);

    }

    @FXML
    void onBtnClickSalir(ActionEvent event) {
        // Si es un Button
        if (event.getSource() instanceof Button) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else if (event.getSource() instanceof MenuItem) {
            // Si es un MenuItem
            Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            stage.close();
        }
    }

    public void initialize() {
        // TODO
        System.out.println("Inicializando...");
    }

    public void hide() {
        //pnlLogin.setVisible(false);
    }

}
