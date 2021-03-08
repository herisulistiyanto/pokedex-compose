package com.andro.indie.school.pokedex.domain.model

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

data class PokemonDetailInfo(
    val id: String,
    val name: String,
    val height: String,
    val weight: String,
    val imgUrl: String,
    val abilities: List<PokemonAbility>,
    val stats: List<PokemonStats>,
    val types: List<PokemonType>
)

data class PokemonAbility(
    val is_hidden: Boolean,
    val slot: Int,
    val ability: Ability
)

data class Ability(val name: String)

