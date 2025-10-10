package br.com.kmp.pokemon.domain.usecases

import br.com.kmp.pokemon.data.mapper.toModel
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.domain.repository.PokemonRepository

class GetAllPokemonsUseCaseImpl(
    private val repository: PokemonRepository
) : GetAllPokemonsUseCase {

    override suspend fun invoke(): Result<List<PokemonModel>> {
        try {
            val pokemons =  repository.getPokemons().map { it.toModel() }
            return Result.success(pokemons)
        } catch (ex: Exception) {
            return Result.failure(ex)
        }
    }

}