package com.andro.indie.school.pokedex.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

@JsonClass(generateAdapter = true)
data class PokemonStatsModel(
    @Json(name ="base_stat")
    val baseStat: Int? = 0,
    @Json(name ="stat")
    val stat: StatModel? = StatModel()
)

@JsonClass(generateAdapter = true)
data class StatModel(
    @Json(name ="name")
    val name: String? = ""
)
