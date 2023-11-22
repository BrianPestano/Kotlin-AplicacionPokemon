package PantallaPrincipal

import CreacionDePokemon.CreacionP
import androidx.compose.runtime.Composable
import InfoArray.infoArray
import androidx.compose.foundation.Image
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Typography
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.prctica3aplicacindepokmon.R
import com.example.prctica3aplicacindepokmon.ui.theme.Práctica3AplicaciónDePokémonTheme
import androidx.compose.material3.DropdownMenuItem as DropdownMenuItem1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaInicio(){
    var lista = ArrayList<infoArray>()
    lista.add(infoArray("Bulbasaur", R.drawable.bulbasaur, "20", "Kanto", 10))
    lista.add(infoArray("Charmander", R.drawable.charmander, "24", "Kanto", 12))
    lista.add(infoArray("Squirtle", R.drawable.squirtle, "26", "Kanto", 13))
    lista.add(infoArray("Chikorita", R.drawable.chikorita, "40", "Johto", 8))
    lista.add(infoArray("Cyndaquil", R.drawable.cyndaquil, "43", "Johto", 7))
    lista.add(infoArray("Totodile", R.drawable.totodile, "46", "Johto", 6))
    lista.add(infoArray("Treecko", R.drawable.treecko, "103", "Hoenn", 5))
    lista.add(infoArray("Torchic", R.drawable.torchic, "106", "Hoenn", 11))
    lista.add(infoArray("Mudkip", R.drawable.mudkip, "108", "Hoenn", 3))
    lista.add(infoArray("Turtwig", R.drawable.turtwig, "202", "Sinnoh", 1))
    lista.add(infoArray("Chimchar", R.drawable.chimchar, "203", "Sinnoh", 2))
    lista.add(infoArray("Piplup", R.drawable.piplup, "212", "Sinnoh", 4))
    lista.add(infoArray("Snivy", R.drawable.snivy, "14", "Teselia", 9))
    lista.add(infoArray("Tepig", R.drawable.tepig, "15", "Teselia", 10))
    lista.add(infoArray("Oshawott", R.drawable.oshawott, "13", "Teselia", 7))
    lista.add(infoArray("Chespin", R.drawable.chespin, "16", "Kalos", 4))
    lista.add(infoArray("Fennekin", R.drawable.fennekin, "10", "Kalos", 8))
    lista.add(infoArray("Froakie", R.drawable.froakie, "1", "Kalos", 11))
    lista.add(infoArray("Grookey", R.drawable.grookey, "12", "Alola", 2))
    lista.add(infoArray("Scorbunny", R.drawable.scorbunny, "11", "Alola", 12))
    lista.add(infoArray("Sobble", R.drawable.sobble, "10", "Alola", 3))


    var buscador by remember { mutableStateOf("") }
    var seleccionRegion by remember { mutableStateOf<String?>(null) }
    var menuDesplegado by remember { mutableStateOf(false) }

    val regiones = listOf("Kanto", "Johto", "Hoenn", "Sinnoh", "Teselia", "Kalos", "Alola")

    val imagenesPokemon = mapOf(
        "Kanto" to "ruta/imagen_kanto.png",
        "Johto" to "ruta/imagen_johto.png"
        // Agrega el resto de las regiones con sus rutas respectivas
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
       /* OutlinedTextField(
            value = buscador,
            onValueChange = {
                buscador = it
                // Lógica para filtrar la lista de regiones
                // Puedes implementar aquí la lógica de filtrado según lo que necesites
            },
            label = { Text("Buscar Región") },
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { menuDesplegado = true }
        ) {
            Text(
                text = seleccionRegion ?: "Seleccionar Región",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )

            DropdownMenu(
                expanded = menuDesplegado,
                onDismissRequest = { menuDesplegado = false }
            ) {

            }
        }*/
        //Falta hacer esto, que cuando el buscador este vacio salgan todos los pokemons
        SearchBar(query = buscador, onQueryChange = { buscador = it }, onSearch = {if(seleccionRegion==null){menuDesplegado=false}},
            active = menuDesplegado, onActiveChange ={menuDesplegado = !menuDesplegado} ) {
            // 1 - Filtrar en base a "buscador"
            regiones.forEach { region ->
                if(region.startsWith(buscador,ignoreCase = true)){
                    DropdownMenuItem(onClick = {
                        seleccionRegion = region
                        buscador = region
                        menuDesplegado = false
                    }, text = {Text(text = region)})
                }

            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            //2 - filtrar en base a "seleccionRegion"
            items(lista.filter { it.region.equals(seleccionRegion, ignoreCase = true) }) { pokemon ->
                RegionItem(
                    region = pokemon,
                    onClick = {
                        // mandar detalle pokemon
                    }
                )
            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAplicacion(){
    pantallaInicio()
}
@Composable
fun RegionItem(region: infoArray, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Muestra la imagen
            Image(
                painter = painterResource(id = region.imagenes),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )

            // Muestra la información del Pokémon
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = region.nombre, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Nivel: ${region.nivel}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Región: ${region.region}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Ruta: ${region.ruta}", style = MaterialTheme.typography.bodySmall)
            }
        }
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}