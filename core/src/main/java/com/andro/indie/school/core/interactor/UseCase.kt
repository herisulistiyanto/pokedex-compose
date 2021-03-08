package com.andro.indie.school.core.interactor

import com.andro.indie.school.core.exception.Failure
import com.andro.indie.school.core.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Created by herisulistiyanto on 27/02/21.
 * KjokenKoddinger
 */

abstract class UseCase<out Type, in Params> : CoroutineScope where Type : Any {

    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val ioDispatcher = Dispatchers.IO

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher

    abstract suspend fun execute(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
//        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
//        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }

        launch(ioDispatcher) {
            ensureActive()
            val result = execute(params)
            withContext(mainDispatcher) {
                onResult(result)
            }
        }
    }

}