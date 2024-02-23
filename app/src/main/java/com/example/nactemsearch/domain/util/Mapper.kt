package com.example.nactemsearch.domain.util

interface Mapper<T, DomainModel> {

    fun mapToDomainModel(dto: T): DomainModel
}