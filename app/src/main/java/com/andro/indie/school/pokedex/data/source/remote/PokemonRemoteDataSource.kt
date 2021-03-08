package com.andro.indie.school.pokedex.data.source.remote

import com.andro.indie.school.pokedex.data.model.remote.response.PokemonResponse
import com.andro.indie.school.pokedex.data.services.PokedexService

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(offset: Int, limit: Int): PokemonResponse
    suspend fun loadMorePokemon(url: String): PokemonResponse

}

class PokemonRemoteDataSourceImpl(private val service: PokedexService) :
    PokemonRemoteDataSource {

    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonResponse {
        return service.getPokemonList(offset, limit)
    }

    override suspend fun loadMorePokemon(url: String): PokemonResponse {
        return service.loadMorePokemon(url)
    }
}