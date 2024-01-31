package com.example.prctica3aplicacindepokmon

import CreacionDePokemon.pantallaCreacionPokemon
import PantallaDetalles.pantallaDetalles
import PantallaPrincipal.pantallaInicio
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.prctica3aplicacindepokmon.ui.theme.Práctica3AplicaciónDePokémonTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Práctica3AplicaciónDePokémonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Crea el NavController
                    val navController = rememberNavController()

                    // Configuración del sistema de navegación
                    SetupNavigation(navController)
                }
            }
        }
    }
}
@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "pantallaInicio") {
        composable("pantallaInicio") { pantallaInicio(navController) }
        composable(
            "pantallaDetalles/{pokemonNombre}",
            arguments = listOf(navArgument("pokemonNombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonNombre = backStackEntry.arguments?.getString("pokemonNombre")
            pantallaDetalles(navController,pokemonNombre)

            }
        composable("pantallaCreacionPokemon/{region}") { backStackEntry ->
            val region = backStackEntry.arguments?.getString("region")
            pantallaCreacionPokemon(navController)
        }
        }
    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Práctica3AplicaciónDePokémonTheme {
        Greeting("Android")
    }
}