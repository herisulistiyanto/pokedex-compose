package com.andro.indie.school.pokedex.data.model.remote.response

import com.andro.indie.school.pokedex.data.model.remote.PokemonModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "count")
    val count: Int? = 0,
    @Json(name = "next")
    val next: String? = "",
    @Json(name = "previous")
    val previous: Any? = Any(),
    @Json(name = "results")
    val results: List<PokemonModel>? = listOf()
)
