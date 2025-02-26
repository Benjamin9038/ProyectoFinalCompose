package com.example.poyectofinalcompose.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.poyectofinalcompose.navigation.Screen

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Bienvenido a EuroFit")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(Screen.User.route) }) {
            Text("Ir a Usuario")
        }
    }
}
