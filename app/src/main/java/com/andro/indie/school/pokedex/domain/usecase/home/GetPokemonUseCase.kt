package com.andro.indie.school.pokedex.domain.usecase.home

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.core.interactor.UseCase
import com.andro.indie.school.pokedex.domain.model.Pokemon
import com.andro.indie.school.pokedex.domain.repository.PokemonRepository

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

class GetPokemonUseCase(
    private val repository: PokemonRepository
) : UseCase<List<Pokemon>, GetPokemonUseCase.RequestParam>() {

    data class RequestParam(val page: Int)

    override suspend fun execute(params: RequestParam): Either<Failure, List<Pokemon>> {
        return repository.getPokemonList(
            offset = params.page * PAGING_SIZE,
            limit = PAGING_SIZE
        )
    }

    private companion object {
        const val PAGING_SIZE = 5
    }
}