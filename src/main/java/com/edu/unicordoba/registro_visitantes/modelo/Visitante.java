package com.edu.unicordoba.registro_visitantes.modelo;

import com.edu.unicordoba.registro_visitantes.util.TextoUtil;

public class Visitante {
    // ESTADO DE INSTANCIA (cada objeto tiene su propia copia)
    private final int id;
    private final String nombre;
    private final int edad;

    // ESTADO DE CLASE (una sola copia compartida para todos los objetos)
    private static int totalCreados;
    public static final int EDAD_MINIMA = 18;

    // BLOQUE STATIC (corre una sola vez al cargar la clase)
    static {
        totalCreados = 0;
    }

    // CONSTRUCTOR
    public Visitante(String nombre, int edad) {
        totalCreados++;                  // Se incrementa el contador de la clase (sin this)
        this.id = totalCreados;          // Asigna el ID usando el estado compartido
        this.nombre = TextoUtil.normalizarNombre(nombre); // Normaliza usando la utilidad estática
        this.edad = edad;
    }

    // MÉTODO DE INSTANCIA (requiere un objeto y usa 'this')
    public boolean esMayorDeEdad() {
        return this.edad >= EDAD_MINIMA;
    }

    // MÉTODO DE CLASE (static, se invoca sin crear objetos)
    public static int getTotalCreados() {
        return totalCreados;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}
