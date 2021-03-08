package com.andro.indie.school.core.base

/**
 * Created by herisulistiyanto on 02/03/21.
 * KjokenKoddinger
 */

interface BaseCollectionMapper<M, D> : BaseMapper<M, D> {

    fun toListDomainModel(listModel: List<M>): List<D>

    fun toListModel(listDomainModel: List<D>): List<M>

}