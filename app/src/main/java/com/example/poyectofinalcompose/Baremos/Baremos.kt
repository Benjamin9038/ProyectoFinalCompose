package com.example.poyectofinalcompose.Baremos

object Baremos {

    /**
     * Devuelve una lista de pruebas físicas según la edad del usuario.
     * @return Lista de pruebas correspondientes a la edad.
     */
    fun obtenerPruebasPorEdad(edad: Int): List<String> {
        return when {
            edad < 12 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper") // Para menores de 12 años
            edad in 12..13 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper")
            edad == 14 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper", "Velocidad 5x10")
            edad >= 15 -> listOf("Abdominales en 30''", "Flexibilidad", "Test de Cooper", "Velocidad 5x10", "Lanzamiento de balón 3kg") // Para mayores de 15 años
            else -> emptyList() // Si la edad no encaja en ningún rango, devuelve una lista vacía
        }
    }
}
