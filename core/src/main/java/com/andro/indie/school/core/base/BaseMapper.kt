package com.andro.indie.school.core.base

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

interface BaseMapper<M, D> {
    fun toDomainModel(model: M): D
    fun toModel(domain: D): M
}