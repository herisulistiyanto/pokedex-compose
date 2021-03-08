package com.andro.indie.school.pokedex.util

/**
 * Created by herisulistiyanto on 07/03/21.
 * KjokenKoddinger
 */
 
fun Number?.toStringOrZero(): String {
    return (this ?: 0).toString()
}

fun Int?.orZero(): Int = this ?: 0