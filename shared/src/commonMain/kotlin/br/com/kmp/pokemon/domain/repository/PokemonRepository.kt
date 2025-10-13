package br.com.kmp.pokemon.domain.repository

import br.com.kmp.pokemon.data.dto.PokemonDto
import br.com.kmp.pokemon.data.entity.PokemonEntity

interface PokemonRepository {

    suspend fun fetchPokemons(): List<PokemonDto>

    suspend fun getPokemons(): List<PokemonEntity>

    suspend fun getPokemonById(id : Int): PokemonEntity

    suspend fun savePokemons(pokemons: List<PokemonEntity>)

    suspend fun hasPokemons(): Boolean
}