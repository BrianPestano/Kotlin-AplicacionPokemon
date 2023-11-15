package PantallaPrincipal

import androidx.compose.runtime.Composable
import CreacionDePokemon.creacionP
import InfoArray.infoArray
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.prctica3aplicacindepokmon.R
import com.example.prctica3aplicacindepokmon.ui.theme.Práctica3AplicaciónDePokémonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaInicio(){
    var buscador by remember { mutableStateOf("") }
    var Seleccionregion by remember { mutableStateOf("") }

    val regiones = listOf("Kanto", "Johto", "Hoenn", "Sinnoh", "Unova", "Kalos", "Alola", "Galar")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Barra de búsqueda
        OutlinedTextField(
            value = buscador,
            onValueChange = {
                buscador = it
                // Lógica para filtrar la lista de regiones
                // Puedes implementar aquí la lógica de filtrado según lo que necesites
            },
            label = { Text("Buscar Región") },
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        // Lista de regiones
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(regiones) { region ->
                RegionItem(region = region, onClick = { Seleccionregion = region })
            }
        }

        // Mostrar detalles de los Pokémon de la región seleccionada
        if (Seleccionregion.isNotEmpty()) {
            creacionP()
        }
    }
}

@Composable
fun RegionItem(region: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(text = region, style = MaterialTheme.typography.subtitle1)
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}