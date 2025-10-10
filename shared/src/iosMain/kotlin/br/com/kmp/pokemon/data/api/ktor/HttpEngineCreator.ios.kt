package br.com.kmp.pokemon.data.api.ktor

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual object HttpEngineCreator {
    actual fun createHttpEngine(): HttpClientEngine = Darwin.create()
}