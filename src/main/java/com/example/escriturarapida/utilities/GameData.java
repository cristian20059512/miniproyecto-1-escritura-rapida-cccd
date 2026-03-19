package com.example.escriturarapida.utilities;

/**
 * Utility class that holds shared game state across scenes.
 * All fields are static, meaning there is only one copy in memory
 * accessible from any class without needing to create an instance.
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class GameData {

    /**
     * The level reached by the player when the game ended.
     * Reset to 0 when the player restarts.
     */
    public static int level = 0;

    /**
     * The remaining seconds on the timer when the game ended.
     * Reset to 0 when the player restarts.
     */
    public static int seconds = 0;
}
