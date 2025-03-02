package com.example.poyectofinalcompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun UserScreen(onContinue: (Int, String) -> Unit) {
    //Variables para almacenar los valores ingresados por el usuario
    var edad by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }

    //Selección de género sin persistencia de datos
    var masculino by remember { mutableStateOf(true) } // Por defecto masculino seleccionado
    var femenino by remember { mutableStateOf(false) }

    //Variables para el cálculo y visualización del IMC
    var showDialog by remember { mutableStateOf(false) }
    var imcResultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Datos del Usuario", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        //Campo para ingresar la edad
        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Permite solo números
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Campo para ingresar el peso en kg
        TextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Campo para ingresar la altura en cm
        TextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Selección de género
        Text(text = "Género", style = MaterialTheme.typography.bodyLarge)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = masculino, onCheckedChange = {
                masculino = it
                femenino = !it // Solo uno puede estar seleccionado a la vez
            })
            Text("Masculino")

            Spacer(modifier = Modifier.width(16.dp))

            Checkbox(checked = femenino, onCheckedChange = {
                femenino = it
                masculino = !it
            })
            Text("Femenino")
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Botón para calcular el IMC
        Button(
            onClick = {
                val pesoFloat = peso.toFloatOrNull()
                val alturaFloat = altura.toFloatOrNull()?.div(100) // Convertir cm a metros

                if (pesoFloat != null && alturaFloat != null && alturaFloat > 0) {
                    val imc = pesoFloat / (alturaFloat * alturaFloat) // Fórmula del IMC
                    val categoria = when {
                        imc < 18.5 -> "Bajo peso"
                        imc in 18.5..24.9 -> "Peso normal"
                        imc in 25.0..29.9 -> "Sobrepeso"
                        else -> "Obesidad"
                    }
                    imcResultado = "Tu IMC es: %.2f\nCategoría: $categoria".format(imc)
                    showDialog = true // Mostrar resultado en un diálogo
                } else {
                    imcResultado = "Por favor, introduce un peso y altura válidos."
                    showDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular IMC")
        }

        Spacer(modifier = Modifier.height(8.dp))

        //Botón para continuar a la siguiente pantalla sin persistencia de datos
        Button(
            onClick = {
                val edadInt = edad.toIntOrNull() ?: 0
                val genero = if (masculino) "Masculino" else "Femenino"
                onContinue(edadInt, genero) // Se envían los datos ingresados
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar")
        }
    }

    //Diálogo emergente para mostrar el resultado del IMC
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Resultado del IMC") },
            text = { Text(imcResultado) },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cerrar")
                }
            }
        )
    }
}
