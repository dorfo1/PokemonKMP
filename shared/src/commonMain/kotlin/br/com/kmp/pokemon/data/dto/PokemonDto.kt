package br.com.kmp.pokemon.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("base_experience")
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val weight: Int,
    val sprites: SpritesDto,
    val stats: List<StatEntryDto>,
    val types: List<TypeEntryDto>
)

@Serializable
data class SpritesDto(
    @SerialName("front_default")
    val frontDefault: String,
    val other: OtherSpritesDto
)

@Serializable
data class OtherSpritesDto(
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtworkDto
)

@Serializable
data class OfficialArtworkDto(
    @SerialName("front_default")
    val frontDefault: String
)

@Serializable
data class StatEntryDto(
    @SerialName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatDto
)

@Serializable
data class StatDto(
    val name: String,
    val url: String
)

@Serializable
data class TypeEntryDto(
    val slot: Int,
    val type: TypeDto
)

@Serializable
data class TypeDto(
    val name: String,
    val url: String
)