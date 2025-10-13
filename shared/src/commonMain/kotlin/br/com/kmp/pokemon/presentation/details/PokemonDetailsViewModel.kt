package br.com.kmp.pokemon.presentation.details

import androidx.lifecycle.viewModelScope
import br.com.kmp.pokemon.core.viewmodel.BaseViewModel
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.domain.usecases.GetPokemonByIdUseCase
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    private val pokemon: Int
) : BaseViewModel<PokemonDetailsState, PokemonDetailsAction, PokemonDetailsEvent>() {

    override val initialState: PokemonDetailsState
        get() = PokemonDetailsState()

    init {
        onEvent(PokemonDetailsEvent.GetPokemon)
    }

    override fun handleEvent(event: PokemonDetailsEvent) {
        when (event) {
            PokemonDetailsEvent.GetPokemon -> getPokemon()
        }
    }

    private fun getPokemon() {
        viewModelScope.launch {
            getPokemonByIdUseCase(pokemon).onSuccess { pokemon ->
                updateState { it.copy(pokemon = pokemon) }
            }
        }
    }
}

data class PokemonDetailsState(val pokemon: PokemonModel? = null)

sealed interface PokemonDetailsEvent {
    object GetPokemon : PokemonDetailsEvent
}

sealed interface PokemonDetailsAction {}

