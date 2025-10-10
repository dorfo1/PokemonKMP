package br.com.kmp.pokemon.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.kmp.pokemon.data.dao.PokemonDao
import br.com.kmp.pokemon.data.database.converters.PokemonTypeRoomConverter
import br.com.kmp.pokemon.data.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,

)
@TypeConverters(PokemonTypeRoomConverter::class)
@ConstructedBy(PokemonDatabaseConstructor::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao


    companion object {
        const val DB_NAME = "pokemon.db"
    }
}