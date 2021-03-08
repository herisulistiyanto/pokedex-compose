package com.andro.indie.school.pokedex.presentation.home

import com.andro.indie.school.core.base.BaseUiState
import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.pokedex.domain.model.Pokemon

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

sealed class HomeState : BaseUiState {
    object Uninitialized : HomeState()
    object Loading : HomeState()
    data class Success(val value: List<Pokemon>) : HomeState()
    data class Fail(val failure: Failure) : HomeState()
}