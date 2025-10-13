package br.com.kmp.pokemon.domain.usecases

import br.com.kmp.pokemon.domain.model.PokemonModel

interface GetPokemonByIdUseCase {

    suspend operator fun invoke(id: Int): Result<PokemonModel>
}