package com.andro.indie.school.core.functional

/**
 * Created by herisulistiyanto on 27/02/21.
 * KjokenKoddinger
 */

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L): Either<L, Nothing>()

    data class Right<out R>(val b: R): Either<Nothing, R>()

    val isRight: Boolean get() = this is Right<R>

    val isLeft: Boolean get() = this is Left<L>

    fun <L> left(a: L): Left<L> = Left(a)

    fun <R> right(b: R): Right<R> = Right(b)

    fun fold(fnL: (L) -> Any, fnR: (R) -> Any): Any {
        return when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }
    }
}

fun <L> L.toEitherLeft(): Either.Left<L> = Either.Left(this)

fun <R> R.toEitherRight(): Either.Right<R> = Either.Right(this)

fun <A, B, C> ((A) -> B).combineFn(fn: (B) -> C): (A) -> C {
    return { param: A ->
        fn(this(param))
    }
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> {
    return when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }
}

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> {
    return this.flatMap(fn.combineFn(::right))
}

fun <L, R> Either<L, R>.getOrElse(value: R): R {
    return when (this) {
        is Either.Left -> value
        is Either.Right -> b
    }
}

fun <L, R> Either<L, R>.onFailure(fn: (failure: L) -> Unit): Either<L, R> {
    return this.apply {
        if (this is Either.Left) fn(a)
    }
}

fun <L, R> Either<L, R>.onSuccess(fn: (success: R) -> Unit): Either<L, R> {
    return this.apply {
        if (this is Either.Right) fn(b)
    }
}
