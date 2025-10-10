package br.com.kmp.pokemon.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.kmp.pokemon.presentation.home.HomeViewModel
import coil.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pokemons") }) }
    ) { paddingValues ->
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(state.pokemons) { pokemon ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(80.dp),
                            model = pokemon.thumbnail,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(pokemon.name)
                    }
                }

            }

        }
    }
}