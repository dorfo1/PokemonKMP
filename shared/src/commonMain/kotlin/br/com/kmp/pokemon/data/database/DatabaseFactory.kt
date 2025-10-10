package br.com.kmp.pokemon.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<PokemonDatabase>
}