package com.example.poyectofinalcompose.Navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.poyectofinalcompose.ui.screens.LoginScreen
import com.example.poyectofinalcompose.ui.screens.PruebasScreen
import com.example.poyectofinalcompose.ui.screens.UserScreen
import com.example.poyectofinalcompose.ui.screens.DetalleScreen

open class Screen(val route: String) {
    object Login : Screen("login")
    object User : Screen("user")
    object Pruebas : Screen("pruebas")
    object Detalle : Screen("detalle/{pruebaId}") {
        fun createRoute(pruebaId: String) = "detalle/$pruebaId"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    var edad by rememberSaveable { mutableStateOf(0) }
    var altura by rememberSaveable { mutableStateOf(0f) }
    var peso by rememberSaveable { mutableStateOf(0f) }

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        // Pantalla de inicio de sesión
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        // Pantalla de ingreso de datos (edad, altura, peso)
        composable(Screen.User.route) {
            UserScreen(navController) { edadIngresada, alturaIngresada, pesoIngresado ->
                edad = edadIngresada
                altura = alturaIngresada
                peso = pesoIngresado
                navController.navigate(Screen.Pruebas.route) // Navegar a PruebasScreen
            }
        }

        // Pantalla de Pruebas filtradas por edad, ahora recibe peso y altura
        composable(Screen.Pruebas.route) {
            PruebasScreen(navController, edad, altura, peso)
        }

        // Recupera el ID de la prueba desde la navegación usando `backStackEntry`
        composable(Screen.Detalle.route) { backStackEntry ->
            val pruebaId = backStackEntry.arguments?.getString("pruebaId") ?: "" // Obtiene `pruebaId` enviado desde `PruebasScreen`
            DetalleScreen(pruebaId, navController) // Navega a `DetalleScreen` con el ID de la prueba seleccionada
        }
    }
}
