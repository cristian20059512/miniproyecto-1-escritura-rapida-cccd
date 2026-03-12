package com.example.escriturarapida.model.GameLogic;

public class GameLogic implements IGameLogic {
    private int points;
    int win = 45;

    public  GameLogic() {
        this.points = 1;
    }

    public void incrementPoints() {
        this.points++;
    }

    public boolean hasWon() {

        return this.points >= win;
    }

    public int getPoints() {
        return this.points;
    }

    public int getTiempoActual() {
        int reduccion = (points / 5) * 2;
        int tiempo = 20 - reduccion;
        return Math.max(tiempo, 2);
    }
}
