package com.andro.indie.school.pokedex.presentation.detail

import com.andro.indie.school.core.base.BaseUiState
import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.pokedex.domain.model.PokemonDetailInfo

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

sealed class DetailUiState : BaseUiState {
    object Uninitialized : DetailUiState()
    object Loading : DetailUiState()
    data class Success(val value: PokemonDetailInfo) : DetailUiState()
    data class Fail(val failure: Failure) : DetailUiState()
}
