package com.example.escriturarapida.model.gameLogic;

/**
 * Interface that defines the required operations for game logic management.
 * <p>
 * Any class implementing this interface must provide methods to
 * increment the player's score and check the win condition.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public interface IGameLogic {

    /**
     * Increments the player's level or score by one.
     */
    void incrementPoints();

    /**
     * Checks whether the player has met the win condition.
     *
     * @return {@code true} if the player has won, {@code false} otherwise
     */
    boolean hasWon();
}
