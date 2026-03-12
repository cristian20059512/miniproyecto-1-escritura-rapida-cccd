package com.example.escriturarapida.model.words;

import java.util.Random;

public class Words implements IWords {
    private String [] words;

    public Words(){
        words= new String[]{
                    "Hola", "Carro", "Casa", "Perro", "Gato",
                    "Mesa", "Silla", "Teclado", "Canalla", "Telefono",
                    "Ventana", "Puerta", "Camino", "Ciudad", "Rio",
                    "Montana", "Playa", "Arena", "Mar", "Barco",
                    "Avion", "Tren", "Moto", "Bicicleta", "Calle",
                    "Semaforo", "Parque", "Arbol", "Flor", "Hoja",
                    "Libro", "Cuaderno", "Lapiz", "Borrador", "Escuela",
                    "Profesor", "Alumno", "Camino", "Tarea", "Examen",
                    "Tiempo", "Reloj", "Minuto", "Segundo", "Dia",
                    "Noche", "Manana", "Tarde", "Comida", "Agua",
                    "Cafe", "Leche", "Pan", "Arroz", "Carne",
                    "Pollo", "Pescado", "Fruta", "Manzana", "Banano",
                    "Naranja", "Uva", "Computador", "Programa", "Codigo",
                    "Java", "Clase", "Objeto", "Metodo", "Variable",
                    "Numero", "Lista", "Arreglo", "Pantalla", "Internet",
                    "Red", "Servidor", "Archivo", "Carpeta", "Sistema",
                    "Juego", "Nivel", "Puntaje", "Rapido", "Lento",
                    "Correcto", "Error", "Intento", "Victoria", "Derrota",
                    "Usuario", "Jugador", "Inicio", "Final", "Texto",
                    "Palabra", "Escritura", "Velocidad", "Practica", "Aprender"
        };
    }


    @Override
    public String generateRandom() {
        Random random = new Random();
        int numero = random.nextInt(100);
        return words[numero];

    }

    @Override
    public Boolean validateWord(String word2, String word1) {
        return word2.equals(word1);
    }
}
