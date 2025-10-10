package br.com.kmp.pokemon.di

import br.com.kmp.pokemon.presentation.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

expect object PresentationModule {

    val dependencies : Module

}