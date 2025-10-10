package br.com.kmp.pokemon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.kmp.pokemon.domain.enums.PokemonType

@Entity(tableName = "Pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val baseExperience: Int,
    val height: Int,
    val name: String,
    val weight: Int,
    val image: String,
    val thumbnail: String,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val types: List<PokemonType>,
)