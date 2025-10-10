package br.com.kmp.pokemon.data.api.ktor

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual object HttpEngineCreator {
    actual fun createHttpEngine(): HttpClientEngine = OkHttp.create()
}