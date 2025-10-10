package br.com.kmp.pokemon.di

import androidx.room.RoomDatabase
import br.com.kmp.pokemon.data.database.DatabaseFactory
import br.com.kmp.pokemon.data.database.PokemonDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val corePlatformDependencies: Module = module {
    single<RoomDatabase.Builder<PokemonDatabase>> { DatabaseFactory(get()).create() }
}