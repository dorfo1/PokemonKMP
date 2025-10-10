package br.com.kmp.pokemon.data.database.converters

import br.com.kmp.pokemon.domain.enums.PokemonType
import kotlinx.serialization.json.Json

object PokemonTypeConverter {

    private val json = Json { ignoreUnknownKeys = true }

    fun fromList(types: List<PokemonType>): String {
        // salva apenas os nomes (text) em JSON
        val names = types.map { it.text }
        return json.encodeToString(names)
    }

    fun toList(data: String): List<PokemonType> {
        return try {
            val names = json.decodeFromString<List<String>>(data)
            names.map { PokemonType.fromData(it) }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}