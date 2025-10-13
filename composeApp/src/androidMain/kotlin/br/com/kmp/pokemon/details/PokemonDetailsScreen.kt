package br.com.kmp.pokemon.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.kmp.pokemon.LocalNavController
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.presentation.details.PokemonDetailsViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(viewModel: PokemonDetailsViewModel) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()



    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalhes") }) }
    ) { paddingValues ->
        state.pokemon?.let { pokemon ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                TypesSection(modifier = Modifier.height(150.dp), pokemon = pokemon)
                PokemonDataSection(pokemon = pokemon)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonStatsSection(pokemon = pokemon)
            }
        }
    }
}


@Composable
private fun TypesSection(modifier: Modifier, pokemon: PokemonModel) {
    val color = remember(pokemon) { pokemon.types.first().color }


    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        ListItem(
            modifier = Modifier.fillMaxWidth(),
            leadingContent = {
                AsyncImage(model = pokemon.image, contentDescription = null)
            },
            headlineContent = {
                Text(
                    text = pokemon.name.capitalize(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            supportingContent = {
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    pokemon.types.forEach {
                        Text(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = color.copy(alpha = .5f))
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                            text = it.text.capitalize(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            },
            trailingContent = {
                Text(
                    text = pokemon.id.formatPokemonNumber(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        )
    }
}

@Composable
private fun PokemonDataSection(pokemon: PokemonModel) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(text = "Informações", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        PokemonDateItem(label = "Altura", data = pokemon.height.toString())
        Spacer(modifier = Modifier.height(4.dp))
        PokemonDateItem(label = "Peso", data = pokemon.weight.toString())
        Spacer(modifier = Modifier.height(4.dp))
        PokemonDateItem(label = "Experiência", data = pokemon.baseExperience.toString())
    }
}

@Composable
private fun PokemonStatsSection(pokemon: PokemonModel) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(text = "Status", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        pokemon.stats.forEach {
            PokemonStatBar(label = it.type.text, value = it.baseStat)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun PokemonStatBar(
    label: String,
    value: Int,
    maxValue: Int = 100, // valor máximo padrão
    color: Color = Color(0xFF78C850) // verde padrão (pode trocar conforme o tipo)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.width(70.dp),
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = value.toString(),
            modifier = Modifier.width(40.dp),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .height(6.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(3.dp))
                .background(Color(0xFFE0E0E0))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = value / maxValue.toFloat())
                    .clip(RoundedCornerShape(3.dp))
                    .background(color)
            )
        }
    }
}

@Composable
private fun PokemonDateItem(label: String, data: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.weight(3f),
            text = label,
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier.weight(7f),
            text = data,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

fun Int.formatPokemonNumber(): String {
    return "#${this.toString().padStart(3, '0')}"
}