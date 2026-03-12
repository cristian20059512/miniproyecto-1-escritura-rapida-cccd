package com.example.escriturarapida.controller.result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class FinalController implements IFinalController {

    @FXML
    private Label levelesLabel;

    @FXML
    private Label motivoLabel;
    @FXML
    private Label temRLabel;


    public void setData(int niveles, String motivo, int tiempoRestante, boolean win_lose) {
        levelesLabel.setText("Niveles completados: " + niveles);
        motivoLabel.setText(motivo);
        temRLabel.setText("Tu tiempo restante es de    " + tiempoRestante + "    s");
        if(win_lose){
            motivoLabel.setStyle("-fx-text-fill: green;");
        }
        else{
            motivoLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void onHandleRestart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/escriturarapida/game-view.fxml")
        );
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void onHandleExit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/escriturarapida/welcome-view.fxml")
        );
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }
}