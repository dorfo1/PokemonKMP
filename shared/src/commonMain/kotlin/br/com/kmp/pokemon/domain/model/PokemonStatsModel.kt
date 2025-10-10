package br.com.kmp.pokemon.domain.model

import br.com.kmp.pokemon.domain.enums.PokemonStatsType

data class PokemonStatsModel(
    val type: PokemonStatsType,
    val baseStat: Int,
)