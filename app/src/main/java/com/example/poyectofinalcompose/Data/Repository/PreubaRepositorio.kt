package com.example.poyectofinalcompose.Data.Repository

import com.example.poyectofinalcompose.Data.Model.Prueba
import com.example.poyectofinalcompose.R // Importamos los recursos de imágenes

class PruebaRepository {
    private val pruebas = listOf(
        Prueba(1, "Abdominales en 30''", "Fuerza", "Ejercicio de resistencia abdominal", R.drawable.abdominales, "https://www.youtube.com/watch?v=FBh_7mQvkFc"),
        Prueba(2, "Flexibilidad", "Flexibilidad", "Medición de flexibilidad", R.drawable.flexibilidad, "https://www.youtube.com/shorts/REt3QYxilmk"),
        Prueba(3, "Test de Cooper", "Resistencia", "Correr la mayor distancia en 12 minutos", R.drawable.test_cooper, "https://www.youtube.com/watch?v=OKlLeImluA4"),
        Prueba(4, "Velocidad 5x10", "Velocidad", "Sprint de velocidad en tramos cortos", R.drawable.velocidad_5x10, "https://www.youtube.com/watch?v=J7DuQOoxe44"),
        Prueba(5, "Lanzamiento de balón 3kg", "Fuerza", "Lanzar un balón de 3kg lo más lejos posible", R.drawable.lanzamiento_balon, "https://www.youtube.com/watch?v=FqtCUCfzV5I")
    )

    fun obtenerPruebas(): List<Prueba> = pruebas
}