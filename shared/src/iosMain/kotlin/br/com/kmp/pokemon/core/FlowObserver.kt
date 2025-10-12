package br.com.kmp.pokemon.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FlowObserver<T>(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private var job: Job? = null

    fun watch(
        flow: kotlinx.coroutines.flow.Flow<T>,
        onChange: (T) -> Unit
    ) {
        job?.cancel()
        job = scope.launch {
            flow.collect { value -> onChange(value) }
        }
    }

    fun cancel() {
        job?.cancel()
    }
}