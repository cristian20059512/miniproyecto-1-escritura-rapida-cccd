package com.example.escriturarapida.model.gameLogic;

import com.example.escriturarapida.utilities.GameData;

/**
 * Manages the core game logic for the Escritura Rapida game.
 * <p>
 * Tracks the player's current level (points), determines the win condition,
 * and calculates the time allowed per level based on progression difficulty.
 * Implements {@link IGameLogic} to define the required game state operations.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class GameLogic implements IGameLogic {

    /** The player's current level, starting at 1 or from saved data. */
    private int points;

    /** The level required to win the game. */
    int win = 45;

    /**
     * Constructs a new {@code GameLogic} instance.
     * If there is saved progress in {@link GameData}, resumes from that level.
     * Otherwise starts from level 1.
     */
    public GameLogic() {
        this.points = GameData.level > 0 ? GameData.level : 1;
    }

    /**
     * Increments the player's level by one after a correct answer.
     */
    public void incrementPoints() {
        this.points++;
    }

    /**
     * Checks whether the player has reached the winning level.
     *
     * @return {@code true} if the current level is greater than or equal to
     *         the winning threshold, {@code false} otherwise
     */
    public boolean hasWon() {
        return this.points >= win;
    }

    /**
     * Returns the player's current level.
     *
     * @return the current level number
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Calculates the time allowed for the current level in seconds.
     * <p>
     * Starts at 20 seconds and decreases by 2 seconds every 5 levels,
     * with a minimum floor of 2 seconds.
     * </p>
     *
     * @return the time in seconds allowed for the current level
     */
    public int getTiempoActual() {
        int reduce = (points / 5) * 2;
        int time = 20 - reduce;
        return Math.max(time, 2);
    }
}