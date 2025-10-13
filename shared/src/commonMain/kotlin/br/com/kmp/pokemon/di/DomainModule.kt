package br.com.kmp.pokemon.di

import br.com.kmp.pokemon.domain.usecases.FetchPokemonsUseCase
import br.com.kmp.pokemon.domain.usecases.FetchPokemonsUseCaseImpl
import br.com.kmp.pokemon.domain.usecases.GetAllPokemonsUseCase
import br.com.kmp.pokemon.domain.usecases.GetAllPokemonsUseCaseImpl
import br.com.kmp.pokemon.domain.usecases.GetPokemonByIdUseCase
import br.com.kmp.pokemon.domain.usecases.GetPokemonByIdUseCaseImpl
import org.koin.dsl.module

object DomainModule {
    val dependencies = module {
        factory<FetchPokemonsUseCase> { FetchPokemonsUseCaseImpl(get()) }
        factory<GetAllPokemonsUseCase> { GetAllPokemonsUseCaseImpl(get()) }
        factory<GetPokemonByIdUseCase> { GetPokemonByIdUseCaseImpl(get()) }
    }
}