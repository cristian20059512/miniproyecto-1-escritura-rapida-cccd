package com.example.escriturarapida.model.timer;

public interface IGameTimer {
    void iniciar(int tiempoInicial);
    void detener();
    int getTiempoRestante();
}