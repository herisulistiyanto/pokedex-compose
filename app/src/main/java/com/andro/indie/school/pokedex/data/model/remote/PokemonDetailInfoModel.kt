package com.andro.indie.school.pokedex.data.model.remote

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

data class PokemonDetailInfoModel(
    val id: String,
    val name: String,
    val height: String,
    val weight: String,
    val abilities: List<PokemonAbilityModel>,
    val stats: List<PokemonStatsModel>,
    val types: List<PokemonTypeModel>
)