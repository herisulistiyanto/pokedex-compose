package com.andro.indie.school.pokedex.data.repository

import androidx.annotation.WorkerThread
import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.pokedex.data.source.remote.PokemonRemoteDataSource
import com.andro.indie.school.pokedex.domain.model.Pokemon
import com.andro.indie.school.pokedex.domain.repository.PokemonRepository
import com.andro.indie.school.pokedex.mapper.PokemonMapper
import com.andro.indie.school.pokedex.util.ExceptionUtil.mapException

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val pokemonMapper: PokemonMapper
) : PokemonRepository {

    @WorkerThread
    override suspend fun getPokemonList(offset: Int, limit: Int): Either<Failure, List<Pokemon>> {
        return try {
            val response = remoteDataSource.getPokemonList(offset, limit)
            val result = response.results.orEmpty()
            Either.Right(pokemonMapper.toListDomainModel(
                response.next.orEmpty() to result
            ))
        } catch (e: Exception) {
            e.mapException()
        }
    }

    override suspend fun loadMorePokemon(url: String): Either<Failure, List<Pokemon>> {
        return try {
            val response = remoteDataSource.loadMorePokemon(url)
            val result = response.results.orEmpty()
            Either.Right(pokemonMapper.toListDomainModel(
                response.next.orEmpty() to result
            ))
        } catch (e: Exception) {
            e.mapException()
        }
    }
}