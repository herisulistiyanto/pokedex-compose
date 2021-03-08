package com.andro.indie.school.pokedex.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

@JsonClass(generateAdapter = true)
data class PokemonAbilityModel(
    @Json(name = "is_hidden")
    val is_hidden: Boolean? = false,
    @Json(name = "slot")
    val slot: Int? = 0,
    @Json(name = "ability")
    val ability: AbilityModel? = AbilityModel()
)

@JsonClass(generateAdapter = true)
data class AbilityModel(
    @Json(name = "name")
    val name: String? = ""
)
