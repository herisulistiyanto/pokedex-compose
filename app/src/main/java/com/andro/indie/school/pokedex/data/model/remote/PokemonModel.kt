package com.andro.indie.school.pokedex.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@JsonClass(generateAdapter = true)
data class PokemonModel(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)
