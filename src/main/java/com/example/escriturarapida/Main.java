package com.example.escriturarapida;

import com.example.escriturarapida.view.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Entry point of the Escritura Rapida application.
 * <p>
 * Extends {@link Application} to launch the JavaFX runtime and
 * initialize the primary stage through {@link GameStage}.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by creating the main game window.
     * <p>
     * Instantiates {@link GameStage}, which handles all window configuration
     * and displays the welcome screen.
     * </p>
     *
     * @param primaryStage the primary stage provided by the JavaFX runtime (not used directly)
     * @throws IOException if the game stage cannot be initialized
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        new GameStage();
    }
}
