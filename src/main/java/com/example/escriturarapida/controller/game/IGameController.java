package com.example.escriturarapida.controller.game;

/**
 * Interface that defines the required actions for the game controller.
 * <p>
 * Any class implementing this interface must provide initialization logic
 * and a method to handle word validation triggered by user interaction.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public interface IGameController {

    /**
     * Initializes the game view and sets up all required components.
     * <p>
     * This method is called automatically by JavaFX after the FXML is loaded.
     * It should configure the timer, load the first word, and set the initial
     * level display.
     * </p>
     */
    void initialize();

    /**
     * Handles the validation of the word typed by the player.
     * <p>
     * This method is triggered either by clicking the validate button
     * or by pressing the Enter key in the text field.
     * </p>
     *
     * @param event the action event triggered by the button or Enter key
     * @throws java.io.IOException if navigation to another view fails
     */
    void onHandelValidate(javafx.event.ActionEvent event) throws java.io.IOException;
}