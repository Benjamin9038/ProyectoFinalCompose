package com.example.poyectofinalcompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.poyectofinalcompose.Navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//el onUserDataEntered sirve para mandar los datos ingresado de userScreen a NavGraph
fun UserScreen(navController: NavController, onUserDataEntered: (Int, Float, Float) -> Unit) {
    var edad by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showIMCDialog by remember { mutableStateOf(false) }
    var imc by remember { mutableStateOf(0.0) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Datos del Usuario") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Ingrese sus datos para continuar", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de edad (Obligatorio)
            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de altura (Opcional)
            OutlinedTextField(
                value = altura,
                onValueChange = { altura = it },
                label = { Text("Altura (m) - Opcional") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de peso (Opcional)
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Peso (kg) - Opcional") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón para calcular IMC (solo si ingresó altura y peso)
            Button(
                onClick = {
                    val pesoNum = peso.toDoubleOrNull() ?: 0.0
                    val alturaNum = altura.toDoubleOrNull() ?: 0.0

                    if (pesoNum > 0 && alturaNum > 0) {
                        imc = pesoNum / (alturaNum * alturaNum)
                        showIMCDialog = true
                    } else {
                        errorMessage = "Debe ingresar peso y altura para calcular el IMC"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = peso.isNotEmpty() && altura.isNotEmpty() // Solo se activa si se ingresaron datos
            ) {
                Text("Calcular IMC")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para continuar a PruebasScreen sin necesidad de peso/altura
            Button(
                onClick = {
                    val edadInt = edad.toIntOrNull()
                    val alturaFloat = altura.toFloatOrNull() ?: 0f
                    val pesoFloat = peso.toFloatOrNull() ?: 0f

                    if (edadInt != null) {
                        onUserDataEntered(edadInt, alturaFloat, pesoFloat)
                        navController.navigate(Screen.Pruebas.route) //  Navega a PruebasScreen
                    } else {
                        errorMessage = "Ingrese una edad válida"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continuar")
            }
        }
    }

    // AlertDialog para mostrar el IMC
    if (showIMCDialog) {
        AlertDialog(
            onDismissRequest = { showIMCDialog = false },//OnDismiss es para cerrar el dialogo
            title = { Text("Resultado del IMC") },
            text = { Text("Tu IMC es: ${"%.2f".format(imc)}") },
            confirmButton = {
                Button(onClick = { showIMCDialog = false }) {
                    Text("Aceptar")
                }
            }
        )
    }
}
