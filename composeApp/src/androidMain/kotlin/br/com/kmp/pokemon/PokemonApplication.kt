package br.com.kmp.pokemon

import android.app.Application
import br.com.kmp.pokemon.di.DataModule
import br.com.kmp.pokemon.di.DomainModule
import br.com.kmp.pokemon.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokemonApplication)
            modules(DataModule.dataDependencies + DomainModule.dependencies + PresentationModule.dependencies)
        }
    }
}