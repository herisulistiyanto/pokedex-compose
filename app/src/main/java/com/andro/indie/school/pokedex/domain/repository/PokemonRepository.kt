package com.andro.indie.school.pokedex.domain.repository

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.pokedex.domain.model.Pokemon
import com.andro.indie.school.pokedex.domain.model.PokemonDetailInfo

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

interface PokemonRepository {

    suspend fun getPokemonList(
        offset: Int,
        limit: Int
    ): Either<Failure, List<Pokemon>>

    suspend fun loadMorePokemon(url: String): Either<Failure, List<Pokemon>>

}