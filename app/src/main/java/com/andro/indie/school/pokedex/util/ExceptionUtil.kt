package com.andro.indie.school.pokedex.util

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import com.andro.indie.school.core.functional.toEitherLeft
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

object ExceptionUtil {

    fun Exception.mapException(): Either.Left<Failure> {
        val failure =  when(this) {
            is HttpException -> Failure.ServerError(this.message.orEmpty())
            is UnknownHostException,
            is SocketException,
            is SocketTimeoutException -> Failure.NetworkConnection(this.message.orEmpty())
            else -> Failure.FeatureFailure
        }
        return failure.toEitherLeft()
    }

}