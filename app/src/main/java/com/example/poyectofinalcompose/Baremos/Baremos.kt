package com.example.poyectofinalcompose.Baremos


object Baremos {

    fun obtenerPruebasPorEdad(edad: Int): List<String> {
        return when {
            edad < 12 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper") // Menores de 12 = 12 años
            edad in 12..13 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper")
            edad == 14 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper", "Velocidad 5x10")
            edad >= 15 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper", "Velocidad 5x10", "Lanzamiento de balón 3kg") // Mayores de 15 = 15 años
            else -> emptyList()
        }
    }
}
