package com.andro.indie.school.pokedex.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andro.indie.school.core.base.BaseViewModel
import com.andro.indie.school.pokedex.domain.model.Pokemon
import com.andro.indie.school.pokedex.domain.usecase.home.GetPokemonUseCase
import com.andro.indie.school.pokedex.domain.usecase.home.LoadMorePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val loadMorePokemonUseCase: LoadMorePokemonUseCase
) : BaseViewModel<HomeState>() {

    init {
        getPokemonList()
    }

    private val mutableListPokemon = MutableLiveData<List<Pokemon>>()

    private fun getPokemonList() {
        viewModelScope.launch {
            setUiState(HomeState.Loading)
            val param = GetPokemonUseCase.RequestParam(0)
            val result = getPokemonUseCase.execute(param)

            result.fold(
                { failure ->
                    setUiState(HomeState.Fail(failure))
                },
                { data ->
                    mutableListPokemon.postValue(data)
                    setUiState(HomeState.Success(data))
                }
            )
        }
    }

    fun loadMorePokemon(url: String) {
        val pokemonList = mutableListPokemon.value?.toMutableList() ?: mutableListOf()
        viewModelScope.launch {
            val param = LoadMorePokemonUseCase.RequestParam(url)
            val result = loadMorePokemonUseCase.execute(param)
            result.fold(
                { failure ->
                    setUiState(HomeState.Fail(failure))
                },
                { data ->
                    pokemonList.addAll(data)
                    mutableListPokemon.postValue(pokemonList)
                    setUiState((HomeState.Success(pokemonList)))
                }
            )
        }
    }

}