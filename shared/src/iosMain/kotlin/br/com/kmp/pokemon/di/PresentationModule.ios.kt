package br.com.kmp.pokemon.di

import br.com.kmp.pokemon.presentation.home.HomeViewModel
import br.com.kmp.pokemon.presentation.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual object PresentationModule {
    actual val dependencies: Module = module {
        factory { SplashViewModel(get()) }
        factory { HomeViewModel(get()) }
    }
}