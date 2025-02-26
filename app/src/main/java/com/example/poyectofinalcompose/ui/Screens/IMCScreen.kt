package com.example.poyectofinalcompose.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IMCScreen(navController: NavController) {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf(0.0) }
    var resultado by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Cálculo de IMC") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Evita que el contenido se sobreponga con la barra superior
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso (kg)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Altura (m)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val pesoNum = peso.toDoubleOrNull() ?: 0.0
                    val alturaNum = altura.toDoubleOrNull() ?: 1.0
                    imc = if (alturaNum > 0) pesoNum / (alturaNum * alturaNum) else 0.0
                    resultado = when {
                        imc < 18.5 -> "Bajo peso"
                        imc in 18.5..24.9 -> "Peso normal"
                        imc in 25.0..29.9 -> "Sobrepeso"
                        else -> "Obesidad"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular IMC")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (imc > 0.0) {
                Text(text = "IMC: ${"%.2f".format(imc)} - $resultado", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
