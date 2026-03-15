package com.example.escriturarapida.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Represents the primary application window for Escritura Rapida.
 * <p>
 * Extends {@link Stage} to encapsulate the configuration of the main window,
 * including loading the welcome view, setting the window title, applying
 * the application icon, and disabling resizing.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class GameStage extends Stage {

    /**
     * Constructs and displays the main application window.
     * <p>
     * Loads the welcome view FXML, configures the stage properties
     * (title, icon, non-resizable), and shows the window.
     * </p>
     *
     * @throws IOException if the welcome view FXML or icon resource cannot be loaded
     */
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/escriturarapida/welcome-view.fxml")
        );

        Parent root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Escritura Rapida");
        setResizable(false);
        getIcons().add(new Image(
                String.valueOf(getClass().getResource("/com/example/escriturarapida/favicon.png"))
        ));
        show();
    }
}
