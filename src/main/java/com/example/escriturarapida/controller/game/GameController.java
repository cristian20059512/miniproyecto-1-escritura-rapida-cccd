package com.example.escriturarapida.controller.game;

import com.example.escriturarapida.controller.result.FinalController;
import com.example.escriturarapida.model.GameLogic.GameLogic;
import com.example.escriturarapida.model.timer.GameTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import com.example.escriturarapida.model.words.Words;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController implements IGameController {
    @FXML
    public Label timeLabel;
    @FXML
    private Label wordLabel;
    @FXML
    private TextField wordTextField;
    @FXML
    private Label validateLabel;
    @FXML
    private Label levelLabel;

    Words words = new Words();
    GameLogic session =new GameLogic();
    GameTimer gameTimer;
    private String word1;
    Boolean win_lose=false;


    @FXML
    public void initialize(){
        gameTimer = new GameTimer(timeLabel, tiempo -> { }, () -> {

                    javafx.application.Platform.runLater(() -> {
                        try {
                            overtime();
                        }
                        catch (IOException e) {e.printStackTrace();
                        }
                    });
                }
        );
        gameTimer.iniciar(session.getTiempoActual());
        word1 = words.generateRandom();
        wordLabel.setText(word1);
        levelLabel.setText("Level "+session.getPoints());
    }
    private void goToFinal(int levelesCompletados, String motivo,int TiempoRestante,boolean win_lose) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/escriturarapida/final-view.fxml")
        );
        Parent root = loader.load();


        FinalController finalController = loader.getController();
        finalController.setData(levelesCompletados, motivo,TiempoRestante,win_lose);

        Stage stage = (Stage) wordLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void nextTurn() throws IOException {
        if(session.hasWon()){
            win();
        }
        else {
            word1 = words.generateRandom();
            wordLabel.setText(word1);
            wordTextField.clear();
            gameTimer.iniciar(session.getTiempoActual());
            validateLabel.setText("");
            levelLabel.setText("Level "+session.getPoints());

        }

    }

    private void overtime() throws IOException {
        String finalI = wordTextField.getText();
        boolean valor = words.validateWord(finalI, word1);
        if (valor) {
            session.incrementPoints();
            validateLabel.setText("¡Correcto!");
            validateLabel.setStyle("-fx-text-fill: green;");

            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1)
            );
            pause.setOnFinished(e -> {
                try {
                    nextTurn();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pause.play();

        } else {
            validateLabel.setText("TIEMPO AGOTADO!");
            validateLabel.setStyle("-fx-text-fill: red;");

            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1)
            );
            pause.setOnFinished(e -> {
                try {
                    goToFinal(session.getPoints(), "Tiempo agotado", gameTimer.getTiempoRestante(),win_lose);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pause.play();

        }
    }

    private void win() throws IOException {
        win_lose=true;
        goToFinal(session.getPoints(), "     ¡Ganaste!\nFelicitaciones", gameTimer.getTiempoRestante(),win_lose);
    }

    @FXML
    public void onHandelValidate(ActionEvent event) throws IOException {
        String word2 = wordTextField.getText();
        boolean valor =words.validateWord(word2,word1);
        if(valor){
            validateLabel.setText("CORRECTO");
            validateLabel.setStyle("-fx-text-fill: green;");
            gameTimer.detener();
            session.incrementPoints();
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1)
            );
            pause.setOnFinished(e -> {
                try {
                    nextTurn();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pause.play();

        }
        else {
            validateLabel.setText("INCORRECTO");
            validateLabel.setStyle("-fx-text-fill: red;");
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1)
            );
            pause.setOnFinished(e -> {
                try {
                    goToFinal(session.getPoints(),"    PALABRA \n INCORRECTA", gameTimer.getTiempoRestante(),win_lose);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pause.play();

        }
    }

}
