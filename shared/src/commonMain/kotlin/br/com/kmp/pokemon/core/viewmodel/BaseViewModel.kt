package br.com.kmp.pokemon.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Action, Event> : ViewModel() {

    protected abstract val initialState: State

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> = _action.asSharedFlow()

    abstract fun handleEvent(event: Event)

    protected fun updateState(block: (state: State) -> State) {
        _state.update { block.invoke(it) }
    }

    fun onEvent(event: Event) {
        handleEvent(event)
    }

    fun sendAction(action: Action) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }
}