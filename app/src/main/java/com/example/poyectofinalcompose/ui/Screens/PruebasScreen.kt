package com.example.poyectofinalcompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.poyectofinalcompose.Baremos.Baremos
import com.example.poyectofinalcompose.Navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PruebasScreen(navController: NavController, edad: Int) {
    val pruebasDisponibles = remember(edad) { Baremos.obtenerPruebasPorEdad(edad) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Pruebas para $edad años") }) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            if (pruebasDisponibles.isEmpty()) {
                Text("No hay pruebas disponibles para esta edad")
            } else {
                pruebasDisponibles.forEach { prueba ->
                    Text(prueba, modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                        navController.navigate(Screen.Detalle.createRoute(prueba))
                    })
                }
            }
        }
    }
}