package PantallaPrincipal

import androidx.compose.runtime.Composable
import InfoArray.infoArray
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.prctica3aplicacindepokmon.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaInicio(navController: NavHostController) {
    var lista by remember {
        mutableStateOf(
            mutableListOf(
                infoArray("Bulbasaur", R.drawable.bulbasaur, "20", "Kanto", 10),
                infoArray("Charmander", R.drawable.charmander, "24", "Kanto", 12),
                infoArray("Squirtle", R.drawable.squirtle, "26", "Kanto", 13),
                infoArray("Chikorita", R.drawable.chikorita, "40", "Johto", 8),
                infoArray("Cyndaquil", R.drawable.cyndaquil, "43", "Johto", 7),
                infoArray("Totodile", R.drawable.totodile, "46", "Johto", 6),
                infoArray("Treecko", R.drawable.treecko, "103", "Hoenn", 5),
                infoArray("Torchic", R.drawable.torchic, "106", "Hoenn", 11),
                infoArray("Mudkip", R.drawable.mudkip, "108", "Hoenn", 3),
                infoArray("Turtwig", R.drawable.turtwig, "202", "Sinnoh", 1),
                infoArray("Chimchar", R.drawable.chimchar, "203", "Sinnoh", 2),
                infoArray("Piplup", R.drawable.piplup, "212", "Sinnoh", 4),
                infoArray("Snivy", R.drawable.snivy, "14", "Teselia", 9),
                infoArray("Tepig", R.drawable.tepig, "15", "Teselia", 10),
                infoArray("Oshawott", R.drawable.oshawott, "13", "Teselia", 7),
                infoArray("Chespin", R.drawable.chespin, "16", "Kalos", 4),
                infoArray("Fennekin", R.drawable.fennekin, "10", "Kalos", 8),
                infoArray("Froakie", R.drawable.froakie, "1", "Kalos", 11),
                infoArray("Rowlet", R.drawable.rowlet, "16", "Alola", 4),
                infoArray("Litten", R.drawable.litten, "10", "Alola", 8),
                infoArray("Popplio", R.drawable.popplio, "1", "Alola", 11),
            )
        )
    }
    var buscador by remember { mutableStateOf("") }
    var seleccionRegion by remember { mutableStateOf<String?>(null) }
    var menuDesplegado by remember { mutableStateOf(false) }
    var pokemonSeleccionado by remember { mutableStateOf(mutableSetOf<String>()) }

    val regiones = listOf("Kanto", "Johto", "Hoenn", "Sinnoh", "Teselia", "Kalos", "Alola")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            query = buscador,
            onQueryChange = {
                buscador = it
                if (it.isEmpty()) {
                    menuDesplegado = false
                    seleccionRegion = null
                }
            },
            onSearch = {
                if (seleccionRegion == null) {
                    menuDesplegado = false
                }
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = !menuDesplegado }
        ) {
            regiones.forEach { region ->
                if (region.startsWith(buscador, ignoreCase = true)) {
                    DropdownMenuItem(
                        onClick = {
                            seleccionRegion = region
                            buscador = region
                            menuDesplegado = false
                        },
                        text = { Text(text = region) }
                    )
                }
            }
        }

        if (seleccionRegion != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista.filter { it.region.equals(seleccionRegion, ignoreCase = true) }) { pokemon ->
                    RegionItem(
                        region = pokemon,
                        isChecked = pokemon.nombre in pokemonSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                pokemonSeleccionado.add(pokemon.nombre)
                            } else {
                                pokemonSeleccionado.remove(pokemon.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${pokemon.nombre}")
                        }
                    )
                }
            }
        } else if (seleccionRegion == null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista) { pokemon ->
                    RegionItem(
                        region = pokemon,
                        isChecked = pokemon.nombre in pokemonSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                pokemonSeleccionado.add(pokemon.nombre)
                            } else {
                                pokemonSeleccionado.remove(pokemon.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${pokemon.nombre}")
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.navigate("pantallaCreacionPokemon/$seleccionRegion") {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }) {
                Text("Añadir")
            }

            Button(onClick = {
                lista.removeAll { it.nombre in pokemonSeleccionado }
                pokemonSeleccionado.clear()
            }) {
                Text("Borrar")
            }
        }
    }
}
@Composable
fun RegionItem(region: infoArray, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit, onClick: () -> Unit) {
    var checked by remember { mutableStateOf(isChecked) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Solo ejecuta el código onClick si se hace clic en la fila pero no en la casilla de verificación
                onClick()
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = region.imagenes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = region.nombre, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onCheckedChange(it)
                }
            )
        }
    }
}