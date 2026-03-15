package com.example.escriturarapida.model.words;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the word pool used during the game.
 * <p>
 * Provides a shuffled list of Spanish words from which the game draws
 * one at a time without repetition. Once all words have been used,
 * the list is reshuffled and the cycle begins again.
 * Implements {@link IWords} to define word generation and validation behavior.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class Words implements IWords {

    /** The shuffled list of available words for the game. */
    private List<String> wordList;

    /** Index pointing to the next word to be returned from the list. */
    private int currentIndex;

    /**
     * Constructs a new {@code Words} instance.
     * <p>
     * Initializes the word pool with a predefined set of Spanish words,
     * shuffles the list randomly, and sets the starting index to zero.
     * </p>
     */
    public Words() {
        String[] wordsArray = new String[]{
                "Hola", "Carro", "Casa", "Perro", "Gato",
                "Mesa", "Silla", "Teclado", "Telefono", "Ventana",
                "Puerta", "Camino", "Ciudad", "Rio", "Montana",
                "Playa", "Arena", "Mar", "Barco", "Avion",
                "Tren", "Moto", "Bicicleta", "Calle", "Semaforo",
                "Parque", "Arbol", "Flor", "Hoja", "Libro",
                "Cuaderno", "Lapiz", "Borrador", "Escuela", "Profesor",
                "Alumno", "Tarea", "Examen", "Tiempo", "Reloj",
                "Minuto", "Segundo", "Dia", "Noche", "Manana",
                "Tarde", "Comida", "Agua", "Cafe", "Leche",
                "Pan", "Arroz", "Carne", "Pollo", "Pescado",
                "Fruta", "Manzana", "Banano", "Naranja", "Uva",
                "Computador", "Programa", "Codigo", "Java", "Clase",
                "Objeto", "Metodo", "Variable", "Numero", "Lista",
                "Arreglo", "Pantalla", "Internet", "Red", "Servidor",
                "Archivo", "Carpeta", "Sistema", "Juego", "Nivel",
                "Puntaje", "Rapido", "Lento", "Correcto", "Error",
                "Intento", "Victoria", "Derrota", "Usuario", "Jugador",
                "Inicio", "Final", "Texto", "Palabra", "Escritura",
                "Velocidad", "Practica", "Aprender", "Canalla", "Camisa"
        };
        wordList = new ArrayList<>(List.of(wordsArray));
        Collections.shuffle(wordList);
        currentIndex = 0;
    }

    /**
     * Returns the next word from the shuffled word pool.
     * <p>
     * Words are returned in shuffled order without repetition. When all words
     * have been used, the list is reshuffled and the index resets to zero.
     * </p>
     *
     * @return the next word as a {@link String}
     */
    @Override
    public String generateRandom() {
        if (currentIndex >= wordList.size()) {
            Collections.shuffle(wordList);
            currentIndex = 0;
        }
        return wordList.get(currentIndex++);
    }

    /**
     * Validates whether the player's input matches the target word exactly.
     * <p>
     * The comparison is case-sensitive and considers spaces and punctuation,
     * as required by the game rules.
     * </p>
     *
     * @param word2 the word typed by the player
     * @param word1 the target word displayed on screen
     * @return {@code true} if both words are exactly equal, {@code false} otherwise
     */
    @Override
    public Boolean validateWord(String word2, String word1) {
        return word2.equals(word1);
    }
}