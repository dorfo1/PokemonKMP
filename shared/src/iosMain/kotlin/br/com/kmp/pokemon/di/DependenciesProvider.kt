package br.com.kmp.pokemon.di

import br.com.kmp.pokemon.presentation.home.HomeViewModel
import br.com.kmp.pokemon.presentation.splash.SplashViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object DependenciesProvider : KoinComponent {

    fun provideHomeViewModel() : HomeViewModel = get()

    fun provideSplashViewModel() : SplashViewModel = get()
}