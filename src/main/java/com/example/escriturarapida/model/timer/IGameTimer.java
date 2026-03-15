package com.example.escriturarapida.model.timer;

/**
 * Interface that defines the required operations for a game countdown timer.
 * <p>
 * Any class implementing this interface must be able to start and stop
 * a timer, and provide access to the remaining time.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public interface IGameTimer {

    /**
     * Starts the countdown timer from the specified initial time.
     *
     * @param tiempoInicial the starting time in seconds
     */
    void startTimer(int tiempoInicial);

    /**
     * Stops the countdown timer if it is currently running.
     */
    void stopTimer();

    /**
     * Returns the number of seconds remaining in the current countdown.
     *
     * @return the remaining time in seconds
     */
    int getTiempoRestante();
}