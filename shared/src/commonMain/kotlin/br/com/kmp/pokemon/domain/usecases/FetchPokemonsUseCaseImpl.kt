package br.com.kmp.pokemon.domain.usecases

import br.com.kmp.pokemon.data.mapper.toEntity
import br.com.kmp.pokemon.data.mapper.toModel
import br.com.kmp.pokemon.domain.repository.PokemonRepository

internal class FetchPokemonsUseCaseImpl(
    private val repository: PokemonRepository
) : FetchPokemonsUseCase {

    override suspend fun invoke(): Result<Unit> {
        try {
            if (repository.hasPokemons()) {
                return Result.success(Unit)
            }

            val pokemons = repository.fetchPokemons().map { it.toModel() }

            repository.savePokemons(pokemons.map { it.toEntity() })

            return Result.success(Unit)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

}