package br.com.kmp.pokemon.data.api.ktor

import io.ktor.client.engine.HttpClientEngine

expect object HttpEngineCreator {
    fun createHttpEngine() : HttpClientEngine
}