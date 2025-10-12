package br.com.kmp.pokemon.presentation.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import br.com.kmp.pokemon.core.viewmodel.BaseViewModel
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.domain.usecases.GetAllPokemonsUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase,
) : BaseViewModel<HomeState, HomeAction, HomeEvent>() {

    override val initialState: HomeState
        get() = HomeState()

    init {
        onEvent(HomeEvent.GetPokemon)
    }

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.GetPokemon -> getPokemons()
            is HomeEvent.GoToDetails -> sendAction(HomeAction.OnGoToDetails(event.pokemon))
        }
    }

    private fun getPokemons() {
        viewModelScope.launch {
            getAllPokemonsUseCase.invoke()
                .onSuccess { data -> updateState { it.copy(pokemons = data) } }

        }
    }
}

@Stable
data class HomeState(
    val pokemons: List<PokemonModel> = emptyList()
)

sealed interface HomeAction {
    data class OnGoToDetails(val pokemon: PokemonModel) : HomeAction
}

sealed interface HomeEvent {
    data object GetPokemon : HomeEvent
    data class GoToDetails(val pokemon: PokemonModel) : HomeEvent
}