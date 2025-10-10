package br.com.kmp.pokemon.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.kmp.pokemon.data.api.PokeApi
import br.com.kmp.pokemon.data.api.createPokeApi
import br.com.kmp.pokemon.data.api.ktor.KtorfitCreator
import br.com.kmp.pokemon.data.dao.PokemonDao
import br.com.kmp.pokemon.data.database.PokemonDatabase
import br.com.kmp.pokemon.data.repository.PokemonRepositoryImpl
import br.com.kmp.pokemon.domain.repository.PokemonRepository
import de.jensklingenberg.ktorfit.Ktorfit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val corePlatformDependencies: Module

object DataModule {
    private val commonDependencies = module {
        single<Ktorfit> { KtorfitCreator.provideKtorfit() }
        single<PokemonDatabase> { getRoomDatabase(get()) }
        single<PokemonDao> { get<PokemonDatabase>().pokemonDao }

        factory<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
        single<PokeApi> { get<Ktorfit>().createPokeApi() }
    }

    val dataDependencies = listOf(commonDependencies, corePlatformDependencies)

    private fun getRoomDatabase(
        builder: RoomDatabase.Builder<PokemonDatabase>
    ): PokemonDatabase {
        return builder
            .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}