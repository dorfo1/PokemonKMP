package br.com.kmp.pokemon.data.mapper

import br.com.kmp.pokemon.data.dto.PokemonDto
import br.com.kmp.pokemon.data.dto.StatEntryDto
import br.com.kmp.pokemon.data.dto.TypeEntryDto
import br.com.kmp.pokemon.data.entity.PokemonEntity
import br.com.kmp.pokemon.data.mapper.toPokemonTypesModel
import br.com.kmp.pokemon.domain.enums.PokemonStatsType
import br.com.kmp.pokemon.domain.enums.PokemonType
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.domain.model.PokemonStatsModel

fun PokemonDto.toModel(): PokemonModel = PokemonModel(
    id = id,
    baseExperience = baseExperience,
    height = height,
    name = name,
    weight = weight,
    image = sprites.other.officialArtwork.frontDefault,
    thumbnail = sprites.frontDefault,
    types = types.toPokemonTypesModel(),
    stats = stats.toPokemonStatsModel()
)

fun PokemonEntity.toModel(): PokemonModel = PokemonModel(
    id = id,
    baseExperience = baseExperience,
    height = height,
    name = name,
    weight = weight,
    image = image,
    thumbnail = thumbnail,
    types = types,
    stats = listOf(
        PokemonStatsModel(
            type = PokemonStatsType.HP,
            baseStat = hp
        ),
        PokemonStatsModel(
            type = PokemonStatsType.ATTACK,
            baseStat = attack
        ),
        PokemonStatsModel(
            type = PokemonStatsType.DEFENSE,
            baseStat = defense
        ),
        PokemonStatsModel(
            type = PokemonStatsType.SPECIAL_ATTACK,
            baseStat = specialAttack
        ),
        PokemonStatsModel(
            type = PokemonStatsType.SPECIAL_DEFENSE,
            baseStat = specialDefense
        ),
        PokemonStatsModel(
            type = PokemonStatsType.SPEED,
            baseStat = speed
        )
    )
)

fun PokemonModel.toEntity(): PokemonEntity = PokemonEntity(
    id = id,
    name = name,
    baseExperience = baseExperience,
    height = height,
    weight = weight,
    image = image,
    thumbnail = thumbnail,
    hp = stats.first { it.type == PokemonStatsType.HP }.baseStat,
    attack = stats.first { it.type == PokemonStatsType.ATTACK }.baseStat,
    defense = stats.first { it.type == PokemonStatsType.DEFENSE }.baseStat,
    specialAttack = stats.first { it.type == PokemonStatsType.SPECIAL_ATTACK }.baseStat,
    specialDefense = stats.first { it.type == PokemonStatsType.SPECIAL_DEFENSE }.baseStat,
    speed = stats.first { it.type == PokemonStatsType.SPEED }.baseStat,
    types = types
)

fun List<StatEntryDto>.toPokemonStatsModel(): List<PokemonStatsModel> {
    return this.map { dto ->
        PokemonStatsModel(
            type = PokemonStatsType.fromData(dto.stat.name),
            baseStat = dto.baseStat
        )
    }
}

fun List<TypeEntryDto>.toPokemonTypesModel(): List<PokemonType> {
    return this.map { dto ->
        PokemonType.fromData(dto.type.name)
    }
}