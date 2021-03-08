package com.andro.indie.school.pokedex.data.services

import com.andro.indie.school.pokedex.data.model.remote.response.PokemonDetailResponse
import com.andro.indie.school.pokedex.data.model.remote.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonResponse

    @GET
    suspend fun loadMorePokemon(@Url url: String): PokemonResponse

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonDetail(@Path("pokemonId") pokemonId: String): PokemonDetailResponse

}