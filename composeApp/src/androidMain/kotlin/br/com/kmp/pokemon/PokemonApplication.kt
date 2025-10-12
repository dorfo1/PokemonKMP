package br.com.kmp.pokemon

import android.app.Application
import br.com.kmp.pokemon.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@PokemonApplication)
        }
    }
}