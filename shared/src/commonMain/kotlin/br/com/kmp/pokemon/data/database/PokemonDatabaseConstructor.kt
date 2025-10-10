package br.com.kmp.pokemon.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PokemonDatabaseConstructor : RoomDatabaseConstructor<PokemonDatabase> {
    override fun initialize(): PokemonDatabase
}