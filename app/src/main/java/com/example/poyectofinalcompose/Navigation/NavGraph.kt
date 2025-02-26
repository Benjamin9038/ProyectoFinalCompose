package com.example.poyectofinalcompose.Navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.poyectofinalcompose.ui.Screens.DetalleScreen
import com.example.poyectofinalcompose.ui.Screens.IMCScreen
import com.example.poyectofinalcompose.ui.screens.*

open class Screen(val route: String) {
    object Login : Screen("login")
    object User : Screen("user")
    object Pruebas : Screen("pruebas")
    object Detalle : Screen("detalle/{pruebaId}") {
        fun createRoute(pruebaId: String) = "detalle/$pruebaId"
    }
    object IMC : Screen("imc")
}

@Composable
fun NavGraph(navController: NavHostController) {
    var edad by rememberSaveable { mutableStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController) { edadIngresada ->
                edad = edadIngresada
            }
        }
        composable(Screen.Pruebas.route) {
            PruebasScreen(navController, edad)
        }
        composable(Screen.IMC.route) { IMCScreen(navController) }
        composable(Screen.Detalle.route) { backStackEntry ->
            val pruebaId = backStackEntry.arguments?.getString("pruebaId") ?: ""
            DetalleScreen(pruebaId, navController)
        }
    }
}