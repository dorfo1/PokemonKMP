package br.com.kmp.pokemon.details

import br.com.kmp.pokemon.core.viewmodel.BaseViewModel
import br.com.kmp.pokemon.domain.model.PokemonModel

class PokemonDetailsViewModel(
    private val pokemon: PokemonModel
) : BaseViewModel<PokemonDetailsState, PokemonDetailsAction, PokemonDetailsEvent>() {
    override val initialState: PokemonDetailsState
        get() = PokemonDetailsState()

    init {
        updateState { it.copy(pokemon = pokemon) }
    }

    override fun handleEvent(event: PokemonDetailsEvent) {}
}

data class PokemonDetailsState(
    val pokemon: PokemonModel? = null
)

sealed interface PokemonDetailsEvent {

}

sealed interface PokemonDetailsAction {

}

