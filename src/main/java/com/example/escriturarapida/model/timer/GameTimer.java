package com.example.escriturarapida.model.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.function.Consumer;
import javafx.scene.control.Label;

/**
 * Manages the countdown timer for each game level.
 * <p>
 * Uses a JavaFX {@link Timeline} to decrement the remaining time every second,
 * update the timer label with visual feedback, and trigger a callback when
 * the time reaches zero. Implements {@link IGameTimer} to define the required
 * timer operations.
 * </p>
 *
 * @author Cristian Camilo Criollo Diaz
 * @version 1.0
 */
public class GameTimer implements IGameTimer {

    /** The JavaFX timeline that drives the countdown. */
    private Timeline timeline;

    /** The number of seconds remaining in the current countdown. */
    private int tempRestante;

    /** The initial time in seconds when the timer was last started. */
    private int tempI;

    /** The label in the UI that displays the remaining time. */
    private final Label timeLabel;

    /** Optional callback invoked on every tick with the remaining time. */
    private Consumer<Integer> onTick;

    /** Callback invoked when the timer reaches zero. */
    private final Runnable onFinish;

    /**
     * Constructs a new {@code GameTimer} with the specified UI label and callbacks.
     *
     * @param timerLabel the {@link Label} that displays the countdown
     * @param onTick     a {@link Consumer} called each second with the remaining time;
     *                   can be a no-op if not needed
     * @param onFinish   a {@link Runnable} called when the timer reaches zero
     */
    public GameTimer(Label timerLabel, Consumer<Integer> onTick, Runnable onFinish) {
        this.timeLabel = timerLabel;
        this.onTick = onTick;
        this.onFinish = onFinish;
    }

    /**
     * Starts the countdown timer from the given initial time.
     * <p>
     * Stops any currently running timer before starting a new one.
     * The timer decrements every second, updates the UI, and calls
     * {@code onFinish} when it reaches zero.
     * </p>
     *
     * @param tiempoInicial the starting time in seconds
     */
    public void startTimer(int tiempoInicial) {
        stopTimer();
        this.tempI = tiempoInicial;
        tempRestante = tempI;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tempRestante--;
            updateUI();
            if (tempRestante <= 0) {
                stopTimer();
                onFinish.run();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Updates the timer label text and applies visual styles based on urgency.
     * <p>
     * When 10 or fewer seconds remain, the label alternates between red bold
     * and white normal style every second to create a blinking warning effect.
     * Above 10 seconds, the label is displayed in white normal style.
     * </p>
     */
    private void updateUI() {
        timeLabel.setText("Tiempo: " + tempRestante + "s");
        if (tempRestante <= 10) {
            if (tempRestante % 2 == 0) {
                timeLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: normal;");
            } else {
                timeLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            }
        } else {
            timeLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: normal;");
        }
    }

    /**
     * Returns the number of seconds remaining in the current countdown.
     *
     * @return the remaining time in seconds
     */
    public int getTiempoRestante() {
        return tempRestante;
    }

    /**
     * Stops the countdown timer if it is currently running.
     */
    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}
