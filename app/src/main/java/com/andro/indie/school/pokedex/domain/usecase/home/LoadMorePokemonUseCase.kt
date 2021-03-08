package com.andro.indie.school.pokedex.domain.usecase.home

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.core.interactor.UseCase
import com.andro.indie.school.pokedex.domain.model.Pokemon
import com.andro.indie.school.pokedex.domain.repository.PokemonRepository

/**
 * Created by herisulistiyanto on 08/03/21.
 * KjokenKoddinger
 */

class LoadMorePokemonUseCase(
    private val repository: PokemonRepository
) : UseCase<List<Pokemon>, LoadMorePokemonUseCase.RequestParam>() {

    data class RequestParam(val url: String)

    override suspend fun execute(params: RequestParam): Either<Failure, List<Pokemon>> {
        return repository.loadMorePokemon(params.url)
    }

}