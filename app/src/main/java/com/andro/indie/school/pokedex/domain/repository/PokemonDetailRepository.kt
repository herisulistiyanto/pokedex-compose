package com.andro.indie.school.pokedex.domain.repository

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.pokedex.domain.model.PokemonDetailInfo

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

interface PokemonDetailRepository {
    suspend fun getPokemonDetail(pokemonId: String): Either<Failure, PokemonDetailInfo>
}