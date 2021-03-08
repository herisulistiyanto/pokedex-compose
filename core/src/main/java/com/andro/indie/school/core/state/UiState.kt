package com.andro.indie.school.core.state

import com.andro.indie.school.core.exception.Failure

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

sealed class UiState<out T>

object Uninitialized : UiState<Nothing>()
object Loading : UiState<Nothing>()

data class Success<T>(private val value: T) : UiState<T>() {
    operator fun invoke(): T = value
}

data class Fail(val value: Failure) : UiState<Failure>()
