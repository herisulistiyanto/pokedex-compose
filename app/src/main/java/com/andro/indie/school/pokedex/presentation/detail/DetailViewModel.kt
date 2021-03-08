package com.andro.indie.school.pokedex.presentation.detail

import androidx.lifecycle.viewModelScope
import com.andro.indie.school.core.base.BaseViewModel
import com.andro.indie.school.pokedex.domain.usecase.detail.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: GetPokemonDetailUseCase
) : BaseViewModel<DetailUiState>() {


    fun getPokemonDetail(pokemonId: String) {
        viewModelScope.launch {
        setUiState(DetailUiState.Loading)
            val param = GetPokemonDetailUseCase.RequestParam(pokemonId)
            val result = detailUseCase.execute(param)
            result.fold(
                { failure ->
                    setUiState(DetailUiState.Fail(failure))
                },
                { data ->
                    setUiState(DetailUiState.Success(data))
                }
            )
        }
    }

}