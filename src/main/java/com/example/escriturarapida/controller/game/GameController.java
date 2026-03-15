package com.example.escriturarapida.controller.game;

import com.example.escriturarapida.controller.result.FinalController;
import com.example.escriturarapida.model.gameLogic.GameLogic;
import com.example.escriturarapida.model.timer.GameTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import com.example.escriturarapida.model.words.Words;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for the main game view.
 * <p>
 * Manages the game flow including word display, input validation,
 * timer control, level progression, and navigation to the final screen.
 * Implements {@link IGameController} to define the required game actions.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class GameController implements IGameController {

    /** Label that displays the remaining time for the current level. */
    @FXML
    public Label timeLabel;

    /** Label that displays the current word or phrase to be typed. */
    @FXML
    private Label wordLabel;

    /** Text field where the player types their answer. */
    @FXML
    private TextField wordTextField;

    /** Label that shows feedback messages (correct, incorrect, time out). */
    @FXML
    private Label validateLabel;

    /** Label that displays the current level number. */
    @FXML
    private Label levelLabel;

    /** Provides random word generation and word validation logic. */
    Words words = new Words();

    /** Manages game session state including points, level, and win condition. */
    GameLogic session = new GameLogic();

    /** Controls the countdown timer for each level. */
    GameTimer gameTimer;

    /** The current word that the player must type correctly. */
    private String word1;

    /** Indicates whether the player won ({@code true}) or lost ({@code false}). */
    Boolean winLose = false;

    /**
     * Initializes the game controller after the FXML is loaded.
     * <p>
     * Sets up the game timer with its tick and finish callbacks,
     * starts the timer, generates the first word, and updates the level label.
     * </p>
     */
    @FXML
    public void initialize() {
        gameTimer = new GameTimer(timeLabel, tiempo -> { }, () -> {
            javafx.application.Platform.runLater(() -> {
                try {
                    overtime();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        gameTimer.startTimer(session.getTiempoActual());
        word1 = words.generateRandom();
        wordLabel.setText(word1);
        levelLabel.setText("Nivel " + session.getPoints());
    }

    /**
     * Navigates to the final results screen, passing the game outcome data.
     *
     * @param levelesCompletados the number of levels successfully completed
     * @param motivo             the reason the game ended (e.g., "Tiempo agotado", "¡Ganaste!")
     * @param tiempoRestante     the remaining time in seconds when the game ended
     * @param winLose            {@code true} if the player won, {@code false} otherwise
     * @throws IOException if the FXML file for the final view cannot be loaded
     */
    private void goToFinal(int levelesCompletados, String motivo, int tiempoRestante, boolean winLose) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/escriturarapida/final-view.fxml")
        );
        Parent root = loader.load();

        FinalController finalController = loader.getController();
        finalController.setData(levelesCompletados, motivo, tiempoRestante, winLose);

        Stage stage = (Stage) wordLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Advances the game to the next turn.
     * <p>
     * If the player has reached the winning condition, triggers the win flow.
     * Otherwise, loads a new random word, resets the input field, restarts
     * the timer with the current difficulty time, and updates the level label.
     * </p>
     *
     * @throws IOException if navigation to the final screen fails
     */
    private void nextTurn() throws IOException {
        if (session.hasWon()) {
            win();
        } else {
            word1 = words.generateRandom();
            wordLabel.setText(word1);
            wordTextField.clear();
            gameTimer.startTimer(session.getTiempoActual());
            validateLabel.setText("");
            levelLabel.setText("Nivel " + session.getPoints());
        }
    }

    /**
     * Handles the event triggered when the countdown timer reaches zero.
     * <p>
     * Validates whatever text the player has typed. If correct, increments
     * points and proceeds to the next turn. If incorrect or empty, navigates
     * to the final screen with a "Tiempo agotado" message.
     * </p>
     *
     * @throws IOException if navigation to the final screen fails
     */
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
            validateLabel.setText("   TIEMPO AGOTADO!");
            validateLabel.setStyle("-fx-text-fill: red;");
            gameTimer.stopTimer();

            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1)
            );
            pause.setOnFinished(e -> {
                try {
                    goToFinal(session.getPoints(), "Tiempo agotado", gameTimer.getTiempoRestante(), winLose);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pause.play();
        }
    }

    /**
     * Handles the win condition when the player completes all levels.
     * <p>
     * Sets {@code winLose} to {@code true} and navigates to the final screen
     * with a congratulatory message.
     * </p>
     *
     * @throws IOException if navigation to the final screen fails
     */
    private void win() throws IOException {
        winLose = true;
        goToFinal(session.getPoints(), "     ¡Ganaste!\nFelicitaciones", gameTimer.getTiempoRestante(), winLose);
    }

    /**
     * Handles the validate button action and Enter key press from the text field.
     * <p>
     * Reads the text entered by the player and compares it to the current word.
     * If correct, stops the timer, increments points, and proceeds to the next turn.
     * If incorrect, stops the timer and navigates to the final screen with
     * an "INCORRECTA" message.
     * </p>
     *
     * @param event the action event triggered by the button or Enter key
     * @throws IOException if navigation to the final screen fails
     */
    @FXML
    public void onHandelValidate(ActionEvent event) throws IOException {
        String word2 = wordTextField.getText();
        boolean valor = words.validateWord(word2, word1);
        if (valor) {
            validateLabel.setText("CORRECTO");
            validateLabel.setStyle("-fx-text-fill: green;");
            gameTimer.stopTimer();
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
        } else {
            validateLabel.setText("INCORRECTO");
            validateLabel.setStyle("-fx-text-fill: red;");
            gameTimer.stopTimer();

            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1)
            );
            pause.setOnFinished(e -> {
                try {
                    goToFinal(session.getPoints(), "    PALABRA \n INCORRECTA", gameTimer.getTiempoRestante(), winLose);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pause.play();
        }
    }
}
