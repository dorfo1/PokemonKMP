package br.com.kmp.pokemon.data.database.converters

import androidx.room.TypeConverter
import br.com.kmp.pokemon.domain.enums.PokemonType

class PokemonTypeRoomConverter {

    @TypeConverter
    fun fromList(types: List<PokemonType>): String {
        return PokemonTypeConverter.fromList(types)
    }

    @TypeConverter
    fun toList(data: String): List<PokemonType> {
        return PokemonTypeConverter.toList(data)
    }
}