package br.com.kmp.pokemon.domain.usecases

import br.com.kmp.pokemon.data.mapper.toModel
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.domain.repository.PokemonRepository

class GetPokemonByIdUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetPokemonByIdUseCase {

    override suspend fun invoke(id: Int): Result<PokemonModel> {
        return try {
            val pokemon = pokemonRepository.getPokemonById(id).toModel()
            Result.success(pokemon)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}