package com.andro.indie.school.pokedex.domain.usecase.detail

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.core.interactor.UseCase
import com.andro.indie.school.pokedex.domain.model.PokemonDetailInfo
import com.andro.indie.school.pokedex.domain.repository.PokemonDetailRepository

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

class GetPokemonDetailUseCase(
    private val detailRepository: PokemonDetailRepository
) : UseCase<PokemonDetailInfo, GetPokemonDetailUseCase.RequestParam>() {

    data class RequestParam(val pokemonId: String)

    override suspend fun execute(params: RequestParam): Either<Failure, PokemonDetailInfo> {
        return detailRepository.getPokemonDetail(pokemonId = params.pokemonId)
    }
}