package com.example.poyectofinalcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.poyectofinalcompose.Navigation.NavGraph
import com.example.poyectofinalcompose.Baremos.Baremos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController)
        }
    }
}
