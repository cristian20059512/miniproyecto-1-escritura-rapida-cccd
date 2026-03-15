package com.example.escriturarapida.controller.welcome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
     * <p>
     * Loads the game view FXML and transitions the application
     * from the welcome screen to the main game screen.
     * </p>
     *
     * @param event the action event triggered by the start button
     * @throws IOException if the game view FXML cannot be loaded
     */
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
}

