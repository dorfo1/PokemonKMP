package br.com.kmp.pokemon.domain.enums

import kotlinx.serialization.Serializable

@Serializable
enum class PokemonStatsType(val text: String) {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed");

    companion object {
        fun fromData(name: String): PokemonStatsType {
            try {
                return entries.first { it.text == name }
            } catch (ex: Exception) {
                throw IllegalArgumentException("Invalid type")
            }
        }
    }
}