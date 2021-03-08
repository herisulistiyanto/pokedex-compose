package com.andro.indie.school.pokedex.domain.model

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

data class PokemonStats(
    val baseStats: Int,
    val baseProcessStat: Float,
    val stat: Stat
)

data class Stat(val name: String)
