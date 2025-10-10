package br.com.kmp.pokemon.di

import br.com.kmp.pokemon.presentation.home.HomeViewModel
import br.com.kmp.pokemon.presentation.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


actual object PresentationModule {
    actual val dependencies: Module = module {
        viewModel { SplashViewModel(get()) }
        viewModel { HomeViewModel(get()) }
    }
}