package br.com.kmp.pokemon.domain.usecases

import br.com.kmp.pokemon.domain.model.PokemonModel

interface GetAllPokemonsUseCase {

    suspend operator fun invoke(): Result<List<PokemonModel>>
}