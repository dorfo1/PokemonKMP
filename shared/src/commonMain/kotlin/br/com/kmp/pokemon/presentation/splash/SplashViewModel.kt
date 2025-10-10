package br.com.kmp.pokemon.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.kmp.pokemon.domain.usecases.FetchPokemonsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val fetchPokemonsUseCase: FetchPokemonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _actions = MutableSharedFlow<SplashActions>()
    val actions: SharedFlow<SplashActions> = _actions.asSharedFlow()

    init {
        onEvent(SplashEvent.FetchPokemons)
    }

    fun onEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.FetchPokemons -> {
                _state.update { it.copy(isLoading = true, isError = false) }
                viewModelScope.launch {
                    fetchPokemonsUseCase.invoke()
                        .onSuccess { _actions.emit(SplashActions.SuccessFetch) }
                        .onFailure { _state.update { it.copy(isLoading = false, isError = true) } }
                }
            }
        }
    }
}


data class SplashState(
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

sealed interface SplashEvent {
    data object FetchPokemons : SplashEvent
}

sealed interface SplashActions {
    data object SuccessFetch : SplashActions
}


