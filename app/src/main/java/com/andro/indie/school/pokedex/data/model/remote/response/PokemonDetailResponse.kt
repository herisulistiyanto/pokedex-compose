package com.andro.indie.school.pokedex.data.model.remote.response
import com.andro.indie.school.pokedex.data.model.remote.PokemonAbilityModel
import com.andro.indie.school.pokedex.data.model.remote.PokemonStatsModel
import com.andro.indie.school.pokedex.data.model.remote.PokemonTypeModel
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @Json(name = "height")
    val height: Int? = 0,
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "is_default")
    val isDefault: Boolean? = false,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String? = "",
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "order")
    val order: Int? = 0,
    @Json(name = "weight")
    val weight: Int? = 0,
    @Json(name = "abilities")
    val abilities: List<PokemonAbilityModel>? = listOf(),
    @Json(name = "stats")
    val stats: List<PokemonStatsModel>? = listOf(),
    @Json(name = "types")
    val types: List<PokemonTypeModel>? = listOf()
)