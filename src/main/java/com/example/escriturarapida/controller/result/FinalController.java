package com.example.escriturarapida.controller.result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for the final results screen.
 * <p>
 * Displays the game outcome including levels completed, end reason,
 * remaining time, and win/loss status. Provides options to restart
 * the game or return to the welcome screen.
 * Implements {@link IFinalController} to define the required navigation actions.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class FinalController implements IFinalController {

    /** Label that displays the number of levels completed by the player. */
    @FXML
    private Label levelesLabel;

    /** Label that displays the reason the game ended (win or loss message). */
    @FXML
    private Label motivoLabel;

    /** Label that displays the remaining time when the game ended. */
    @FXML
    private Label temRLabel;

    /**
     * Populates the final screen with the game result data.
     * <p>
     * Sets the level count, end reason, and remaining time labels.
     * Colors the reason label green for a win or red for a loss.
     * Also requests focus on the root node so keyboard events can be received.
     * </p>
     *
     * @param niveles        the total number of levels completed
     * @param motivo         the reason the game ended
     * @param tiempoRestante the remaining time in seconds at game end
     * @param win_lose       {@code true} if the player won, {@code false} if lost
     */
    public void setData(int niveles, String motivo, int tiempoRestante, boolean win_lose) {
        levelesLabel.setText("Niveles completados: " + niveles);
        motivoLabel.setText(motivo);
        temRLabel.setText("Tu tiempo restante es de    " + tiempoRestante + "    s");
        if (win_lose) {
            motivoLabel.setStyle("-fx-text-fill: green;");
        } else {
            motivoLabel.setStyle("-fx-text-fill: red;");
        }
        motivoLabel.getParent().requestFocus();
    }

    /**
     * Handles the restart button action.
     * <p>
     * Loads the game view and resets the game from the beginning.
     * </p>
     *
     * @param event the action event triggered by the restart button
     * @throws IOException if the game view FXML cannot be loaded
     */
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

    /**
     * Navigates back to the welcome screen.
     * <p>
     * Shared navigation logic used by both the exit button and the Escape key handler.
     * </p>
     *
     * @throws IOException if the welcome view FXML cannot be loaded
     */
    private void goToWelcome() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/escriturarapida/welcome-view.fxml")
        );
        Parent root = loader.load();
        Stage stage = (Stage) motivoLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Handles the exit button action.
     * <p>
     * Delegates navigation to {@link #goToWelcome()}.
     * </p>
     *
     * @param event the action event triggered by the exit button
     * @throws IOException if the welcome view FXML cannot be loaded
     */
    @FXML
    public void onHandleExit(ActionEvent event) throws IOException {
        goToWelcome();
    }

    /**
     * Handles key press events on the final screen.
     * <p>
     * If the Escape key is pressed, navigates back to the welcome screen.
     * </p>
     *
     * @param event the key event triggered by the player's keyboard input
     * @throws IOException if the welcome view FXML cannot be loaded
     */
    @FXML
    public void onHandleKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            goToWelcome();
        }
    }
}