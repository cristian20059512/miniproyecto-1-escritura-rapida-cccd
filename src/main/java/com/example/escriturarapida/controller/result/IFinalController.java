package com.example.escriturarapida.controller.result;

/**
 * Interface that defines the required navigation actions for the final results controller.
 * <p>
 * Any class implementing this interface must provide handlers for
 * restarting the game and exiting to the welcome screen.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public interface IFinalController {

    /**
     * Handles the action to restart the game from the beginning.
     *
     * @param event the action event triggered by the restart button
     * @throws java.io.IOException if the game view FXML cannot be loaded
     */
    void onHandleRestart(javafx.event.ActionEvent event) throws java.io.IOException;

    /**
     * Handles the action to exit to the welcome screen.
     *
     * @param event the action event triggered by the exit button
     * @throws java.io.IOException if the welcome view FXML cannot be loaded
     */
    void onHandleMenu(javafx.event.ActionEvent event) throws java.io.IOException;
}