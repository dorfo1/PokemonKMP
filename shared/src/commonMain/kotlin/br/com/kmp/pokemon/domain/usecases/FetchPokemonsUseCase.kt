package br.com.kmp.pokemon.domain.usecases

interface FetchPokemonsUseCase {

    suspend operator fun invoke(): Result<Unit>
}