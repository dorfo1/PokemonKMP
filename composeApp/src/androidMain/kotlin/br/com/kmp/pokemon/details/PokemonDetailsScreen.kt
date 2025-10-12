package br.com.kmp.pokemon.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PokemonDetailsScreen(viewModel: PokemonDetailsViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
}