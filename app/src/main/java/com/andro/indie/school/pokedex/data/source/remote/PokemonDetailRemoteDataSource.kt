package com.andro.indie.school.pokedex.data.source.remote

import com.andro.indie.school.pokedex.data.model.remote.response.PokemonDetailResponse
import com.andro.indie.school.pokedex.data.services.PokedexService

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

interface PokemonDetailRemoteDataSource {

    suspend fun getPokemonDetail(pokemonId: String): PokemonDetailResponse

}

class PokemonDetailRemoteDataSourceImpl(
    private val service: PokedexService
) : PokemonDetailRemoteDataSource {

    override suspend fun getPokemonDetail(pokemonId: String): PokemonDetailResponse {
        return service.getPokemonDetail(pokemonId)
    }
}