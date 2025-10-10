package br.com.kmp.pokemon.data.api

import br.com.kmp.pokemon.data.dto.PokemonDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path

interface PokeApi {
    @GET("pokemon/{number}")
    suspend fun fetchPokemonByNumber(@Path("number") number: Int): PokemonDto
}