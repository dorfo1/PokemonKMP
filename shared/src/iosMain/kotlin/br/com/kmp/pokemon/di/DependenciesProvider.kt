package br.com.kmp.pokemon.di

import br.com.kmp.pokemon.presentation.details.PokemonDetailsViewModel
import br.com.kmp.pokemon.presentation.home.HomeViewModel
import br.com.kmp.pokemon.presentation.splash.SplashViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

object DependenciesProvider : KoinComponent {

    fun provideHomeViewModel(): HomeViewModel = get()

    fun provideSplashViewModel(): SplashViewModel = get()

    fun providePokemonDetailsViewModel(id: Int): PokemonDetailsViewModel = get { parametersOf(id) }
}