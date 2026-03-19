package com.example.escriturarapida.controller.welcome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for the welcome screen.
 * <p>
 * Handles the initial screen of the application where the player
 * can start a new game session. Implements {@link IWelcomeController}
 * to define the required start action.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class WelcomeController implements IWelcomeController {

    /**
     * Handles the start button action.
     *
     * @param event the action event triggered by the start button
     * @throws IOException if the game view FXML cannot be loaded
     */
    @Override
    @FXML
    public void onHandleStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/escriturarapida/game-view.fxml")
        );
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Closes the application window using the given node.
     *
     * @param node any node belonging to the current scene
     */
    private void exit(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the exit button action.
     *
     * @param event the action event triggered by the exit button
     */
    @FXML
    public void onHandleExit(ActionEvent event) {
        exit((Node) event.getSource());
    }

    /**
     * Handles key press events on the welcome screen.
     * If Escape is pressed, closes the application.
     *
     * @param event the key event triggered by the player's keyboard input
     */
    @FXML
    public void onHandleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            exit((Node) event.getSource());
        }
    }
}