package br.com.kmp.pokemon.domain.model

import br.com.kmp.pokemon.domain.enums.PokemonType

data class PokemonModel(
    val id: Int,
    val baseExperience: Int,
    val height: Int,
    val name: String,
    val weight: Int,
    val image: String,
    val thumbnail: String,
    val types: List<PokemonType>,
    val stats: List<PokemonStatsModel>
)