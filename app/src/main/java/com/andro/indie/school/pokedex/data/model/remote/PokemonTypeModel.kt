package com.andro.indie.school.pokedex.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

@JsonClass(generateAdapter = true)
data class PokemonTypeModel(
    @Json(name ="slot")
    val slot: Int? = 0,
    @Json(name = "type")
    val type: TypeModel? = TypeModel()
)

@JsonClass(generateAdapter = true)
data class TypeModel(
    @Json(name ="name")
    val name: String? = ""
)
