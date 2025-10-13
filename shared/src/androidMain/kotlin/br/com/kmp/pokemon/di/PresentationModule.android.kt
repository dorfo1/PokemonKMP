package br.com.kmp.pokemon.di

import br.com.kmp.pokemon.presentation.details.PokemonDetailsViewModel
import br.com.kmp.pokemon.presentation.home.HomeViewModel
import br.com.kmp.pokemon.presentation.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object PresentationModule {
    actual val dependencies: Module = module {
        viewModel { SplashViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { (pokemonId: Int) ->
            PokemonDetailsViewModel(
                pokemon = pokemonId,
                getPokemonByIdUseCase = get()
            )
        }
    }
}