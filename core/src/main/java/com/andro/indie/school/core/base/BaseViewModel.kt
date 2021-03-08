package com.andro.indie.school.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

abstract class BaseViewModel<T> : ViewModel() where T : BaseUiState {

    private val mutableUiState: MutableLiveData<T> = MutableLiveData<T>()

    fun uiState(): LiveData<T> = mutableUiState

    protected fun setUiState(newState: T) {
        mutableUiState.postValue(newState)
    }

}