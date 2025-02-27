package com.example.poyectofinalcompose.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.poyectofinalcompose.Data.Model.Prueba
import com.example.poyectofinalcompose.Data.Repository.PruebaRepository
import com.example.poyectofinalcompose.Baremos.Baremos
import com.example.poyectofinalcompose.Navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PruebasScreen(navController: NavController, edad: Int, altura: Float, peso: Float) {
    val todasLasPruebas = remember { PruebaRepository().obtenerPruebas() }
    val pruebasDisponibles = remember(edad) {
        todasLasPruebas.filter { prueba ->
            Baremos.obtenerPruebasPorEdad(edad).contains(prueba.nombre)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pruebas para $edad años") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Habilita el scroll
        ) {
            if (pruebasDisponibles.isEmpty()) {
                Text("No hay pruebas disponibles para esta edad")
            } else {
                pruebasDisponibles.forEach { prueba ->
                    PruebaItem(prueba, navController)
                }
            }
        }
    }
}

@Composable
fun PruebaItem(prueba: Prueba, navController: NavController) {
    val context = LocalContext.current // Obtener el contexto de la aplicación

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(Screen.Detalle.createRoute(prueba.id.toString())) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imagen: Painter = painterResource(id = prueba.imagen)
            Image(
                painter = imagen,
                contentDescription = prueba.nombre,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = prueba.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = prueba.categoria, style = MaterialTheme.typography.bodySmall)

                TextButton(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(prueba.enlace))
                    context.startActivity(intent)
                }) {
                    Text("Aquí está un video explicativo de cómo hacerlo")
                }
            }
        }
    }
}
