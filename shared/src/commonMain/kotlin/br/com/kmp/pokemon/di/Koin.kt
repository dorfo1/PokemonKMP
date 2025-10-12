package br.com.kmp.pokemon.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(DataModule.dataDependencies + DomainModule.dependencies + PresentationModule.dependencies)
    }
}

fun initKoinIos() = initKoin()

