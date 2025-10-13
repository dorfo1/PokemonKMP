package br.com.kmp.pokemon.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import br.com.kmp.pokemon.data.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemons: List<PokemonEntity>)

    @Transaction
    @Query("SELECT * FROM Pokemon")
    suspend fun getPokemons(): List<PokemonEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM Pokemon)")
    suspend fun hasPokemons(): Boolean

    @Query("SELECT * FROM Pokemon WHERE id = :id LIMIT 1")
    suspend fun getPokemonById(id: Int): PokemonEntity

}