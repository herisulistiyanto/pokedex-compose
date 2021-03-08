package com.andro.indie.school.pokedex.data.repository

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.core.functional.toEitherRight
import com.andro.indie.school.pokedex.data.model.remote.PokemonDetailInfoModel
import com.andro.indie.school.pokedex.data.source.remote.PokemonDetailRemoteDataSource
import com.andro.indie.school.pokedex.domain.model.PokemonDetailInfo
import com.andro.indie.school.pokedex.domain.repository.PokemonDetailRepository
import com.andro.indie.school.pokedex.mapper.PokemonDetailMapper
import com.andro.indie.school.pokedex.util.ExceptionUtil.mapException
import com.andro.indie.school.pokedex.util.toStringOrZero

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

class PokemonDetailRepositoryImpl(
    private val remoteDataSource: PokemonDetailRemoteDataSource,
    private val pokemonDetailMapper: PokemonDetailMapper
) : PokemonDetailRepository {

    override suspend fun getPokemonDetail(pokemonId: String): Either<Failure, PokemonDetailInfo> {
        return try {
            val response = remoteDataSource.getPokemonDetail(pokemonId)
            val result = PokemonDetailInfoModel(
                id = response.id.toStringOrZero(),
                name = response.name.orEmpty(),
                height = response.height.toStringOrZero(),
                weight = response.weight.toStringOrZero(),
                abilities = response.abilities.orEmpty(),
                stats = response.stats.orEmpty(),
                types = response.types.orEmpty()
            )
            pokemonDetailMapper.toDomainModel(result).toEitherRight()
        } catch (e: Exception) {
            e.mapException()
        }
    }
}