package br.com.kmp.pokemon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.domain.usecases.GetAllPokemonsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllPokemonsUseCase.invoke()
                .onSuccess { data -> _state.update { it.copy(pokemons = data) } }

        }
    }
}


data class HomeState(
    val pokemons : List<PokemonModel> = emptyList()
)