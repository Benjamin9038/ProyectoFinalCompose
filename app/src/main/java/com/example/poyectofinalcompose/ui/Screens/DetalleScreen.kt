package com.example.poyectofinalcompose.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//Esto se pone por que me dice que me falta una dependencia, pero si la pongo me da error
//asi que es el mismo andorid estudio el que me da la opcion
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(pruebaId: String, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Detalle de la Prueba") }) // Usa Material 3
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) //Evita superposiciones con la barra superior
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Detalles de la prueba seleccionada: $pruebaId")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.popBackStack() }) { // Usa popBackStack para regresar
                Text("Volver")
            }
        }
    }
}
