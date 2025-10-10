package br.com.kmp.pokemon.domain.enums

enum class PokemonType(val text: String) {
    NORMAL("normal"),
    FIRE("fire"),
    WATER("water"),
    ELECTRIC("electric"),
    GRASS("grass"),
    ICE("ice"),
    FIGHTING("fighting"),
    POISON("poison"),
    GROUND("ground"),
    FLYING("flying"),
    PSYCHIC("psychic"),
    BUG("bug"),
    ROCK("rock"),
    GHOST("ghost"),
    DARK("dark"),
    DRAGON("dragon"),
    STEEL("steel"),
    FAIRY("fairy");

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