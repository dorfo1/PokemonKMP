package br.com.kmp.pokemon.data.repository

import br.com.kmp.pokemon.data.api.PokeApi
import br.com.kmp.pokemon.data.dao.PokemonDao
import br.com.kmp.pokemon.data.dto.PokemonDto
import br.com.kmp.pokemon.data.entity.PokemonEntity
import br.com.kmp.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

internal class PokemonRepositoryImpl(
    private val pokeApi: PokeApi,
    private val pokemonDao: PokemonDao
) : PokemonRepository {
    override suspend fun fetchPokemons(): List<PokemonDto> = coroutineScope {
        val pokemons = (1..150).map { number ->
            async { pokeApi.fetchPokemonByNumber(number) }
        }.awaitAll()

        return@coroutineScope pokemons
    }

    override suspend fun savePokemons(pokemons: List<PokemonEntity>) {
        pokemonDao.insert(pokemons)
    }

    override suspend fun hasPokemons(): Boolean {
        return pokemonDao.hasPokemons()
    }

    override suspend fun getPokemons(): List<PokemonEntity> {
        return pokemonDao.getPokemons()
    }
}