package com.example.poyectofinalcompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(pruebaId: String, navController: NavController) {
    var resultado by remember { mutableStateOf("") }
    var inputData by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle de la Prueba") }) // Usa TopAppBar en lugar de CenterAlignedTopAppBar
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Introduce los datos para la prueba: $pruebaId")

            Spacer(modifier = Modifier.height(16.dp))

            // Campo para ingresar datos
            OutlinedTextField(
                value = inputData,
                onValueChange = { inputData = it },
                label = { Text("Ingrese resultado") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para calcular la nota
            Button(onClick = {
                resultado = calcularNota(pruebaId, inputData)
            }) {
                Text("Calcular Nota")
            }

            // Mostrar resultado
            if (resultado.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(resultado, style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para volver atrás
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}

// Función para calcular la nota basada en la prueba
fun calcularNota(pruebaId: String, input: String): String {
    val valor = input.toIntOrNull() ?: return "Ingrese un número válido"

    return when (pruebaId) {
        "1" -> "Nota en Abdominales: ${valor * 2} puntos"
        "2" -> "Nota en Flexibilidad: ${valor * 3} puntos"
        "3" -> "Nota en Test de Cooper: ${valor / 2} puntos"
        "4" -> "Nota en Velocidad 5x10: ${valor * 5} puntos"
        "5" -> "Nota en Lanzamiento de balón: ${valor * 4} puntos"
        else -> "Prueba no reconocida"
    }
}