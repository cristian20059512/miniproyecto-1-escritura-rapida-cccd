package com.example.escriturarapida.model.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.function.Consumer;
import javafx.scene.control.Label;

public class GameTimer implements IGameTimer {
    private Timeline timeline;
    private int tempRestante;
    private int tempI;

    private final Label timeLabel;
    private Consumer<Integer> onTick;
    private final Runnable onFinish;

    public GameTimer(Label timerLabel, Consumer<Integer> onTick, Runnable onFinish) {
        this.timeLabel = timerLabel;
        this.onTick = onTick;
        this.onFinish = onFinish;
    }

    public void iniciar(int tiempoInicial) {
        detener();
        this.tempI = tiempoInicial;
        tempRestante = tempI;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tempRestante--;
            actualizarUI();
            if (tempRestante <= 0) {
                detener();
                onFinish.run();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void actualizarUI() {
        timeLabel.setText("Tiempo: " + tempRestante + "s");
        if (tempRestante <= 5) {
            timeLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else {
            timeLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: normal;");
        }
    }
    public int getTiempoRestante() {
        return tempRestante;
    }

    public void detener() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}



