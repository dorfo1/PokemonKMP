package br.com.kmp.pokemon.presentation.splash

import androidx.lifecycle.viewModelScope
import br.com.kmp.pokemon.core.viewmodel.BaseViewModel
import br.com.kmp.pokemon.domain.usecases.FetchPokemonsUseCase
import kotlinx.coroutines.launch

class SplashViewModel(
    private val fetchPokemonsUseCase: FetchPokemonsUseCase
) : BaseViewModel<SplashState, SplashAction, SplashEvent>() {

    override val initialState: SplashState
        get() = SplashState()

    init {
        handleEvent(SplashEvent.FetchPokemons)
    }

    override fun handleEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.FetchPokemons -> {
                updateState { it.copy(isLoading = true, isError = false) }
                viewModelScope.launch {
                    fetchPokemonsUseCase.invoke()
                        .onSuccess { sendAction(SplashAction.SuccessFetch) }
                        .onFailure { updateState { it.copy(isLoading = false, isError = true) } }
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

sealed interface SplashAction {
    data object SuccessFetch : SplashAction
}


