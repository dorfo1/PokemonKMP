package br.com.kmp.pokemon.domain.enums

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
enum class PokemonType(val text: String, val color: Color) {
    NORMAL("normal", Color(0xFFA8A77A)),
    FIRE("fire", Color(0xFFEE8130)),
    WATER("water", Color(0xFF6390F0)),
    ELECTRIC("electric", Color(0xFFF7D02C)),
    GRASS("grass", Color(0xFF7AC74C)),
    ICE("ice", Color(0xFF96D9D6)),
    FIGHTING("fighting", Color(0xFFC22E28)),
    POISON("poison", Color(0xFFA33EA1)),
    GROUND("ground", Color(0xFFE2BF65)),
    FLYING("flying", Color(0xFFA98FF3)),
    PSYCHIC("psychic", Color(0xFFF95587)),
    BUG("bug", Color(0xFFA6B91A)),
    ROCK("rock", Color(0xFFB6A136)),
    GHOST("ghost", Color(0xFF735797)),
    DARK("dark", Color(0xFF705746)),
    DRAGON("dragon", Color(0xFF6F35FC)),
    STEEL("steel", Color(0xFFB7B7CE)),
    FAIRY("fairy", Color(0xFFD685AD));

    companion object {
        fun fromData(name: String): PokemonType {
            return try {
                entries.first { it.text == name }
            } catch (ex: Exception) {
                NORMAL
            }
        }
    }
}