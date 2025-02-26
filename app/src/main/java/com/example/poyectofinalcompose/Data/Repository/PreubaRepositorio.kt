package com.example.poyectofinalcompose.Data.Repository

import com.example.poyectofinalcompose.Data.Model.Prueba

class PruebaRepository {
    private val pruebas = listOf(
        Prueba(1, "Salto de longitud", "Fuerza", "Consiste en saltar lo más lejos posible", "salto.png"),
        Prueba(2, "Carrera de 20m", "Velocidad", "Corre 20 metros lo más rápido posible", "carrera.png")
    )

    fun obtenerPruebas(): List<Prueba> {
        return pruebas
    }
}