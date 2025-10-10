package br.com.kmp.pokemon.data.api.ktor

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorfitCreator {

    fun provideKtorfit(): Ktorfit = Ktorfit.Builder()
        .httpClient(createHttpClient())
        .baseUrl("https://pokeapi.co/api/v2/")
        .build()

    private fun createHttpClient(): HttpClient {

        val client = HttpClient(HttpEngineCreator.createHttpEngine()) {
            HttpResponseValidator {

                validateResponse { response ->
                    println("response for: ${response.request.url} \n")
                    println("response for: ${response.bodyAsText()} \n")
                    println("response with status code: ${response.status.value} \n")
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

        }

        client.plugin(HttpSend).intercept { request ->
            println("Request URL: ${request.url}")
            execute(request)
        }

        return client
    }


}