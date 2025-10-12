package br.com.kmp.pokemon.core

import br.com.kmp.pokemon.core.viewmodel.BaseViewModel
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

fun <T> StateFlow<T>.watch(block: (T?) -> Unit): Closeable {
    val observer = FlowObserver<T>()
    observer.watch(this) { value -> block(value) }
    return object : Closeable {
        override fun close() {
            observer.cancel()
        }
    }
}

fun <T> SharedFlow<T>.watch(block: (T?) -> Unit): Closeable {
    val observer = FlowObserver<T>()
    observer.watch(this) { value -> block(value) }
    return object : Closeable {
        override fun close() {
            observer.cancel()
        }
    }
}

fun <State, Action, Event> BaseViewModel<State, Action, Event>.watchState(
    block: (State) -> Unit
): Closeable = state.watch { value ->
    value?.let { block.invoke(it) }
}

fun <State, Action, Event> BaseViewModel<State, Action, Event>.watchAction(
    block: (Action) -> Unit
): Closeable = action.watch { value ->
    value?.let { block.invoke(it) }
}