package Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


val navController = rememberNavController()

NavHost(navController = navController, startDestination = "pantallaInicio") {
    composable("pantallaInicio") { pantallaInicio(navController) }
    composable("pantallaDetalles/{pokemonNombre}") { backStackEntry ->
        val pokemonNombre = backStackEntry.arguments?.getString("pokemonNombre")
        PantallaDetalles(pokemonNombre)
    }
}