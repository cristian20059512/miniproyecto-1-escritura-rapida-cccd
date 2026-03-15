package com.example.escriturarapida.model.words;

/**
 * Interface that defines the required operations for word management.
 * <p>
 * Any class implementing this interface must provide a method to
 * generate words for the game and a method to validate player input.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public interface IWords {

    /**
     * Generates and returns the next word to be displayed to the player.
     *
     * @return the next word as a {@link String}
     */
    String generateRandom();

    /**
     * Validates whether the player's typed word matches the target word.
     * <p>
     * The comparison must be exact, considering case, spaces, and punctuation.
     * </p>
     *
     * @param word2 the word typed by the player
     * @param word1 the target word displayed on screen
     * @return {@code true} if the words match exactly, {@code false} otherwise
     */
    Boolean validateWord(String word2, String word1);
}