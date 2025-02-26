package com.example.poyectofinalcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.poyectofinalcompose.ui.Screens.DetalleScreen
import com.example.poyectofinalcompose.ui.Screens.IMCScreen
import com.example.poyectofinalcompose.ui.Screens.LoginScreen
import com.example.poyectofinalcompose.ui.Screens.PruebasScreen
import com.example.poyectofinalcompose.ui.Screens.UserScreen
import com.example.poyectofinalcompose.ui.Screens.*

    sealed class Screen(val route: String) {
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
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.User.route) { UserScreen(navController) }
        composable(Screen.Pruebas.route) { PruebasScreen(navController) }
        composable(Screen.Detalle.route) { backStackEntry ->
            val pruebaId = backStackEntry.arguments?.getString("pruebaId") ?: ""
            DetalleScreen(pruebaId)
        }
        composable(Screen.IMC.route) { IMCScreen(navController) }
    }
}