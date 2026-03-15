package com.example.escriturarapida.controller.welcome;

/**
 * Interface that defines the required actions for the welcome screen controller.
 * <p>
 * Any class implementing this interface must provide a handler
 * to start the game when the player is ready.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public interface IWelcomeController {

    /**
     * Handles the action to start the game.
     * <p>
     * Loads the game view and transitions the player to the main game screen.
     * </p>
     *
     * @param event the action event triggered by the start button
     * @throws java.io.IOException if the game view FXML cannot be loaded
     */
    void onHandleStart(javafx.event.ActionEvent event) throws java.io.IOException;
}