package com.andro.indie.school.core.exception

/**
 * Created by herisulistiyanto on 27/02/21.
 * KjokenKoddinger
 */

sealed class Failure {
    data class NetworkConnection(val msg: String?) : Failure()
    data class ServerError(val msg: String?) : Failure()

    object FeatureFailure : Failure()

}
